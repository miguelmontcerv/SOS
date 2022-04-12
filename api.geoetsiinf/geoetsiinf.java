package rest1;
import java.util.Iterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.client.Entity;

@Path("/usuarios")
public class geoetsiinf {

	 @Context
	  private UriInfo uriInfo;
	 @Context
	  Request request;
	 
	//Genera una lista de todos los usuario en la aplicacion
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	public Response getListaUsuarios() {
	    List<Usuarios> users = new ArrayList<Usuarios>();
	    users.addAll(UsuariosDao.getInstance().getModel().values());
	    
	    UsuariosList lista = new UsuariosList(users);
	    
	    Iterator<Usuarios> i  = lista.getL().iterator();
	    
	    String s = "";
	    while (i.hasNext()) {
	    	s = s + i.next().getUsuario() + "\n";
	    }
	     
	    return Response.ok(s).build();
	}

	//Metodo para obtener el path del proyecto
	@Path("Path")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	public Response consultarPath() {
		return Response.status(Response.Status.OK).entity(uriInfo.getAbsolutePath().toString()).build();
	 }
	 
	
	//Ver a un usuario en especifico, si lo encuentra regresa una instancia de él, sino regresa null
	@Path("{id_usuario}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Usuarios getUsuario(@PathParam("id_usuario") String id) {
		Response res;
		 
		Usuarios usuario;
		if(UsuariosDao.getInstance().getModel().containsKey(id)) {
			usuario = UsuariosDao.getInstance().getModel().get(id);
		    res = Response.ok(usuario).build();
		      
		    return usuario;
		}else{
			//throw new RuntimeException("Get: Tarea con id " + id +  " no encontrada");
		    res = Response.status(Response.Status.NOT_FOUND).build();
		    return null;
		}
	}
	 
	//Agregar a un usuario, el xml se le envia a travez del body
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
	public Response postUsuario(Usuarios userRequest) {
		String res;
		 
		if(userRequest == null) {
			res = "El body esta vacio"; 
			return Response.noContent().entity(res).build();
		}
		else{
			UsuariosDao.getInstance().getModel().put(userRequest.getId(), userRequest);
			res = "Se ha creado el user " + userRequest.getId();
			return  Response.status(Response.Status.CREATED).entity(res).header("Location",uriInfo.getAbsolutePath().toString()).build();
		}
	}

	//Se agrega un tesoro al usuario que se marca en el Path, la info del tesoro es mandada por el body
	@POST
	@Path("{id_usuario}/tesoros_encontrados")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response addTesoroHist(@PathParam("id_usuario") String id, Tesoros userRequest) {

		Usuarios usuario;
		if (UsuariosDao.getInstance().getModel().containsKey(id)) { //si el usuario exite
			usuario = UsuariosDao.getInstance().getModel().get(id);
			ArrayList<Tesoros> tesoros = usuario.getTesoros_encontrados();

			for (int i = 0; i < tesoros.size(); i++) {
				if (userRequest==null)
					return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
				if (userRequest.getId().equals("0"))
					return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
				if (tesoros.get(i).getId() == userRequest.getId()) 
					return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
				
			}
			UsuariosDao.getInstance().getModel().get(id).setTesoros_encontrados(userRequest);
			String res = "Se ha agregado el tesoro " + userRequest.getId();
			return Response.status(Response.Status.CREATED).entity(res).header("Location",uriInfo.getAbsolutePath().toString()).build();
		} else {
			// throw new RuntimeException("Get: Tarea con id " + id + " no encontrada");
			return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
		}

	}

	//Se agrega un tesoro al usuario que se marca en el Path, la info del tesoro es mandada por el body
	@POST
	@Path("{id_usuario}/tesoros_publicados")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response addTesoroPubli(@PathParam("id_usuario") String id, Tesoros userRequest) {

		Usuarios usuario;
		if (UsuariosDao.getInstance().getModel().containsKey(id)) { //si el usuario exite
			usuario = UsuariosDao.getInstance().getModel().get(id);
			ArrayList<Tesoros> tesoros = usuario.getTesoros_publicados();

			for (int i = 0; i < tesoros.size(); i++) {
				if (userRequest==null)
					return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
				if (userRequest.getId().equals("0"))
					return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
				if (tesoros.get(i).getId() == userRequest.getId()) 
					return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
				
			}
			UsuariosDao.getInstance().getModel().get(id).setTesoros_publicados(userRequest);
			String res = "Se ha agregado el tesoro " + userRequest.getId();
			return Response.status(Response.Status.CREATED).entity(res).header("Location",uriInfo.getAbsolutePath().toString()).build();
		} else {
			// throw new RuntimeException("Get: Tarea con id " + id + " no encontrada");
			return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
		}

	}

	//Agregar un amigo a un usuario, hace el cambio en ambas instancias
	@POST
	@Path("{id_usuario}/amigos")
	public Response addAmigo(@PathParam("id_usuario") String id, @QueryParam("id_amigos") String userRequest) {
		Usuarios usuarioA;
		Usuarios usuarioB;
		if (id.equals(userRequest)) //se verifica que un usuario no se pueda agregar a si mismo a sus amigos
			return Response.status(Response.Status.UNAUTHORIZED).build();
		if (UsuariosDao.getInstance().getModel().containsKey(id)
				&& UsuariosDao.getInstance().getModel().containsKey(userRequest)) { //solo si ambos usuario existen
			usuarioA = UsuariosDao.getInstance().getModel().get(id);
			usuarioB = UsuariosDao.getInstance().getModel().get(userRequest);

			for (int i = 0; i < usuarioA.getId_amigos().size(); i++) { //verifica que no sean amigos
				if (usuarioA.getId_amigos(i).equals(userRequest)) {
					return Response.status(Response.Status.UNAUTHORIZED).build();
				}
			}
			for (int i = 0; i < usuarioB.getId_amigos().size(); i++) { //verifica que no sean amigos
				if (usuarioB.getId_amigos(i).equals(id)) {
					return Response.status(Response.Status.UNAUTHORIZED).build();
				}
			}

			//ambos se agregan como amigos
			UsuariosDao.getInstance().getModel().get(id).setId_amigos(userRequest);
			UsuariosDao.getInstance().getModel().get(userRequest).setId_amigos(id);
			
			//se genera la respuesta
			String res = "Usuario  " + id + " ha agreado a amigos a " + userRequest;
			return Response.status(Response.Status.CREATED).entity(res)
					.header("Location", uriInfo.getAbsolutePath().toString()).build();

		} else{
			return Response.status(Response.Status.NOT_FOUND).build(); //si alguno de los dos users no existen
		}
	}

	//Elimina un amigo de un usuario, hace el cambio en ambas instancias
	@DELETE
	@Path("{id_usuario}/amigos")
	public Response deleteAmigo(@PathParam("id_usuario") String id, @QueryParam("id_amigos") String userRequest) {
		Usuarios usuarioA;
		Usuarios usuarioB;
		if (id.equals(userRequest))
			return Response.status(Response.Status.UNAUTHORIZED).build();
		if (UsuariosDao.getInstance().getModel().containsKey(id)
				&& UsuariosDao.getInstance().getModel().containsKey(userRequest)) {
			usuarioA = UsuariosDao.getInstance().getModel().get(id);
			usuarioB = UsuariosDao.getInstance().getModel().get(userRequest);

			for (int i = 0; i < usuarioA.getId_amigos().size() + 1; i++) {
				if (i == usuarioA.getId_amigos().size())
					return Response.status(Response.Status.UNAUTHORIZED).build(); //No se encuentra al amigo en la lista
				if (usuarioA.getId_amigos(i).equals(userRequest)) {
					UsuariosDao.getInstance().getModel().get(id).getId_amigos().remove(i); //Se encuentra y se elimina
				}
			}
			for (int i = 0; i < usuarioB.getId_amigos().size() + 1; i++) {
				if (i == usuarioB.getId_amigos().size())
					return Response.status(Response.Status.UNAUTHORIZED).build();
				if (usuarioB.getId_amigos(i).equals(id)) {
					UsuariosDao.getInstance().getModel().get(userRequest).getId_amigos().remove(i);
				}
			}

			//Se envia la respuesta
			String res = "Usuario  " + id + " ha borrado de amigos a " + userRequest;
			return Response.status(Response.Status.CREATED).entity(res)
					.header("Location", uriInfo.getAbsolutePath().toString()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	//Devuelve el listado de usuarios con filtros
	@GET
	@Path("{id_usuario}/amigos")
	@Produces(MediaType.APPLICATION_XML)
	public Response listaAmigos(@PathParam("id_usuario") String id, @QueryParam("nom_amigos") String nombr,
		@QueryParam("pag") int pag, @QueryParam("lim") int lim) {
			
		Usuarios usuario;
		ArrayList<Usuarios>  id_amigos= new ArrayList<Usuarios>(); 
		
		if (UsuariosDao.getInstance().getModel().containsKey(id)) {
			usuario = UsuariosDao.getInstance().getModel().get(id);
				
			ArrayList<String> amigos = usuario.getId_amigos(); //Se genera un arreglo con los id de los amigos de user
				
			if(pag>amigos.size() || lim > amigos.size()) {
				return Response.status(Response.Status.BAD_REQUEST).build();	
			}
				
			for(int i = pag; i <=  lim ;i++ ) {
				System.out.println(i +  "<=" +lim);
				Usuarios amigo = UsuariosDao.getInstance().getModel().get(amigos.get(i));
				System.out.println("id de amigos en la lista "+amigo.getId());
					
				if(amigo.getNombre_completo().contains(nombr))
					id_amigos.add(amigo);
				}
				
				if(id_amigos.size()==0)
					return Response.status(Response.Status.NOT_FOUND).build();
		
					UsuariosList salida = new UsuariosList(id_amigos);
				return Response.ok(salida).build();
		} else
			return Response.status(Response.Status.NOT_FOUND).build();		
		}


	//Filtro con fecha
	@GET
	@Path("/tesoros/fecha")
	@Produces(MediaType.APPLICATION_XML)
	public Response listaTesorosFecha(@QueryParam("id_user") String id, @QueryParam("fecha_lim") String fechaM,
			@QueryParam("pag") int pag, @QueryParam("lim") int lim, @QueryParam("dif") String dif,
			@QueryParam("tam") int tam, @QueryParam("terreno") String terreno) {
		try {
			Usuarios usuario;
			if (UsuariosDao.getInstance().getModel().containsKey(id)) {
				usuario = UsuariosDao.getInstance().getModel().get(id);
				ArrayList<Tesoros> Histtes = usuario.getTesoros_encontrados();
				Date fechaMax = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(fechaM);
				if(pag>Histtes.size()) {
					return Response.status(Response.Status.BAD_REQUEST).build();	
				}
				ArrayList<Tesoros> lista = new ArrayList<Tesoros>(); 
				for (int i = pag - 1; i < Histtes.size() && lim - 1 > Histtes.size(); i++) {
					Date fecha =  (Date) new SimpleDateFormat("yyyy-mm-dd").parse(Histtes.get(i).getFecha_encontrado());
					if(fecha.before(fechaMax)||fecha.equals(fechaMax)) {
						Tesoros tesoro =TesorosDao.getInstance().getModel().get(Histtes.get(i).getId());
						if(tesoro.getDificultad().equals(dif)&&tesoro.getTipo_terreno().equals(terreno)&&tesoro.getTam()==tam)
							lista.add(tesoro);
					}
				}
				if(lista.size()<=0)
					return Response.status(Response.Status.NOT_FOUND).build();
				TesorosList salida = new TesorosList(lista);
				return Response.ok(salida).build();
			}
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("No se pudieron convertir los Ã­ndices a nÃºmeros").build();
		} catch (ParseException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo leer la fecha").build();
		}
	}

	// Filtro sin fecha
	@GET
	@Path("/tesoros")
	@Produces(MediaType.APPLICATION_XML)
	public Response listaTesorosnoFecha(@QueryParam("id_user") String id, @QueryParam("pag") int pag,
			@QueryParam("lim") int lim, @QueryParam("dif") String dif, @QueryParam("tam") int tam,
			@QueryParam("terreno") String terreno) {
		try {
			Usuarios usuario;
			if (UsuariosDao.getInstance().getModel().containsKey(id)) {
				usuario = UsuariosDao.getInstance().getModel().get(id);
				ArrayList<Tesoros> Histtes = usuario.getTesoros_encontrados();

				if (pag > Histtes.size()) {
					return Response.status(Response.Status.BAD_REQUEST).build();
				}
				ArrayList<Tesoros> lista = new ArrayList<Tesoros>();
				for (int i = pag - 1; i < Histtes.size() && lim - 1 > Histtes.size(); i++) {

					Tesoros tesoro = TesorosDao.getInstance().getModel().get(Histtes.get(i).getId());
					if (tesoro.getDificultad().equals(dif) && tesoro.getTipo_terreno().equals(terreno)
							&& tesoro.getTam() == tam)
						lista.add(tesoro);
				}
				if (lista.size() <= 0)
					return Response.status(Response.Status.NOT_FOUND).build();
				TesorosList salida = new TesorosList(lista);
				return Response.ok(salida).build();
			}
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("No se pudieron convertir los Ã­ndices a nÃºmeros").build();
		}
	}

	// Filtro con fecha
	@GET
	@Path("/tesoros/cercaFe")
	@Produces(MediaType.APPLICATION_XML)
	public Response listaTesorosCercaFecha(@QueryParam("coor_x") float X, @QueryParam("coor_y") float Y,
			@QueryParam("fecha_lim") String fechaM, @QueryParam("pag") int pag, @QueryParam("lim") int lim,
			@QueryParam("dif") String dif, @QueryParam("tam") int tam, @QueryParam("terreno") String terreno) {
		try {
			ArrayList<Tesoros> TesorosDisp = TesorosDao.getLista();
			ArrayList<distanciaTesoro> aux = new ArrayList<distanciaTesoro>();
			for (int i = 0; i < TesorosDisp.size(); i++) {
				float a = TesorosDisp.get(i).getCoor_x() - X;
				float b = TesorosDisp.get(i).getCoor_y() - Y;
				distanciaTesoro salida = new distanciaTesoro(Math.sqrt((a * a) + (b * b)), TesorosDisp.get(i).getId());
				aux.add(salida);
			}
			Collections.sort(aux);
			Date fechaMax = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(fechaM);
			if (pag > aux.size()) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			ArrayList<Tesoros> lista = new ArrayList<Tesoros>();
			for (int i = pag - 1; i < aux.size() && lim - 1 > aux.size(); i++) {
				Date fecha = (Date) new SimpleDateFormat("yyyy-mm-dd")
						.parse(TesorosDao.getInstance().getModel().get(aux.get(i).getId()).getFecha_post());
				if (fecha.before(fechaMax) || fecha.equals(fechaMax)) {
					Tesoros tesoro = TesorosDao.getInstance().getModel().get(aux.get(i).getId());
					if (tesoro.getDificultad().equals(dif) && tesoro.getTipo_terreno().equals(terreno)
							&& tesoro.getTam() == tam)
						lista.add(tesoro);
				}
			}
			if (lista.size() <= 0)
				return Response.status(Response.Status.NOT_FOUND).build();
			TesorosList salida = new TesorosList(lista);
			return Response.ok(salida).build();
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("No se pudieron convertir los Ã­ndices a nÃºmeros").build();
		} catch (ParseException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo leer la fecha").build();
		}
	}

	// Filtro sin fecha
	@GET
	@Path("/tesoros/cerca")
	@Produces(MediaType.APPLICATION_XML)
	public Response listaTesorosCercaFecha(@QueryParam("coor_x") float X, @QueryParam("coor_y") float Y,
			@QueryParam("pag") int pag, @QueryParam("lim") int lim, @QueryParam("dif") String dif,
			@QueryParam("tam") int tam, @QueryParam("terreno") String terreno) {
		try {
			ArrayList<Tesoros> TesorosDisp = TesorosDao.getLista();
			ArrayList<distanciaTesoro> aux = new ArrayList<distanciaTesoro>();
			for (int i = 0; i < TesorosDisp.size(); i++) {
				float a = TesorosDisp.get(i).getCoor_x() - X;
				float b = TesorosDisp.get(i).getCoor_y() - Y;
				distanciaTesoro salida = new distanciaTesoro(Math.sqrt((a * a) + (b * b)), TesorosDisp.get(i).getId());
				aux.add(salida);
			}
			Collections.sort(aux);

			if (pag > aux.size()) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			ArrayList<Tesoros> lista = new ArrayList<Tesoros>();
			for (int i = pag - 1; i < aux.size() && lim - 1 > aux.size(); i++) {

				Tesoros tesoro = TesorosDao.getInstance().getModel().get(aux.get(i).getId());
				if (tesoro.getDificultad().equals(dif) && tesoro.getTipo_terreno().equals(terreno)
						&& tesoro.getTam() == tam)
					lista.add(tesoro);
			}
			if (lista.size() <= 0)
				return Response.status(Response.Status.NOT_FOUND).build();
			TesorosList salida = new TesorosList(lista);
			return Response.ok(salida).build();
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("No se pudieron convertir los Ã­ndices a nÃºmeros").build();
		}
	}
}
