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
	 
	 //Obtenemos una lista de los usuarios:
	 @GET
	 @Consumes(MediaType.TEXT_PLAIN)
	  public Response getTodosBrowser() {
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
	 
	 //Ver a un usuario en especifico
	 @Path("{id_usuario}")
	  @GET
	  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	  public Response getUsuario(@PathParam("id_usuario") String id) {
		  Response res;
		  Usuarios usuario;
		  if(UsuariosDao.getInstance().getModel().containsKey(id)) {
			  usuario = UsuariosDao.getInstance().getModel().get(id);
		      res = Response.ok(usuario).build();
		  }  else {
			  //throw new RuntimeException("Get: Tarea con id " + id +  " no encontrada");
		      res = Response.status(Response.Status.NOT_FOUND).build();
		  }
		  return res;
	  }
	 
	 //Agregar a un usuario definiendo su id a mano
	 @PUT
	 @Path("{id_usuario}")
	 @Consumes(MediaType.APPLICATION_XML)
	 public Response putUsuario(@PathParam("id_usuario") String id,@QueryParam("usuario") String user){
		 Usuarios us = new Usuarios();
		 Response res;
		 
		 if(UsuariosDao.getInstance().getModel().containsKey(id)) {
			 res = Response.noContent().build();
		 }
		 else {
			 us.setId(id);
			 us.setNombre_completo(user);
			 
			 UsuariosDao.getInstance().getModel().put(us.getId(), us);
			 res = Response.status(Response.Status.CREATED).entity("Se ha creado con exito un nuevo usuario").header("Location",uriInfo.getAbsolutePath().toString()).build();
		 }
		 return res;
	 }
	 
	 @POST
	 @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
	 public Response postUsuario(Usuarios userRequest) {
		 String res;
		 
		 if(userRequest == null) {
			 res = "El body esta vacio"; 
			 return Response.noContent().entity(res).build();
		 }
		 else {
			 UsuariosDao.getInstance().getModel().put(userRequest.getId(), userRequest);
			 res = "Se ha creado el user " + userRequest.getId();
			 return  Response.status(Response.Status.CREATED).entity(res).header("Location",uriInfo.getAbsolutePath().toString()).build();
		 }
	 }
	 
	 
	  // Este método se invoca si se solicita TEXT_PLAIN
	  /*@GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public Response saludoPlainText() {
		  String respuesta = "Servidor dado de alta correctamente";
		  return Response.status(Response.Status.ACCEPTED).entity(respuesta).header("Location", 
				  uriInfo.getAbsolutePath().toString()+"/otra").build();
	  }*/
	  
	  @GET
	  @Path("{nombre}/{id}")
	  @Produces(MediaType.APPLICATION_XML)
		public Usuarios getXML(@PathParam("nombre") String name, @PathParam("id") String id) {
			Usuarios user = new Usuarios();
			user.setUsuario(name);
			user.setId(id);
			user.setNombre_completo(name + " Angel Monteros");
			user.setCorreo(name+"@upm.es");
			user.setEdad(21);
			user.setLocalidad("Madrid");
			
			/*user.setId_amigos(12);
			user.setId_amigos(64);
			
			user.setNom_amigos("Ana");
			user.setNom_amigos("Lalo");*/
			return user;
		}
	  


	@PUT
	@Path("{id_usuario}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTesoroHist(@PathParam("id_usuario") String id, Tesoros userRequest) {

		Usuarios usuario;
		if (UsuariosDao.getInstance().getModel().containsKey(id)) {
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

	@POST
	@Path("{id_usuario}/amigos")
	public Response addAmigo(@PathParam("id_usuario") String id, @QueryParam("id_amigos") String userRequest) {
		Usuarios usuarioA;
		Usuarios usuarioB;
		if (id.equals(userRequest))
			return Response.status(Response.Status.UNAUTHORIZED).build();
		if (UsuariosDao.getInstance().getModel().containsKey(id)
				&& UsuariosDao.getInstance().getModel().containsKey(userRequest)) {
			usuarioA = UsuariosDao.getInstance().getModel().get(id);
			usuarioB = UsuariosDao.getInstance().getModel().get(userRequest);

			for (int i = 0; i < usuarioA.getId_amigos().size(); i++) {
				if (usuarioA.getId_amigos(i).equals(userRequest)) {
					return Response.status(Response.Status.UNAUTHORIZED).build();
				}
			}
			for (int i = 0; i < usuarioB.getId_amigos().size(); i++) {
				if (usuarioB.getId_amigos(i).equals(id)) {
					return Response.status(Response.Status.UNAUTHORIZED).build();
				}
			}
			UsuariosDao.getInstance().getModel().get(id).setId_amigos(userRequest);
			UsuariosDao.getInstance().getModel().get(userRequest).setId_amigos(id);
			String res = "Usuario  " + id + " ha agreado a amigos a " + userRequest;
			return Response.status(Response.Status.CREATED).entity(res)
					.header("Location", uriInfo.getAbsolutePath().toString()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

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
					return Response.status(Response.Status.UNAUTHORIZED).build();
				if (usuarioA.getId_amigos(i).equals(userRequest)) {
					UsuariosDao.getInstance().getModel().get(id).getId_amigos().remove(i);
				}
			}
			for (int i = 0; i < usuarioB.getId_amigos().size() + 1; i++) {
				if (i == usuarioB.getId_amigos().size())
					return Response.status(Response.Status.UNAUTHORIZED).build();
				if (usuarioB.getId_amigos(i).equals(id)) {
					UsuariosDao.getInstance().getModel().get(userRequest).getId_amigos().remove(i);
				}
			}
			String res = "Usuario  " + id + " ha borrado de amigos a " + userRequest;
			return Response.status(Response.Status.CREATED).entity(res)
					.header("Location", uriInfo.getAbsolutePath().toString()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("{id_usuario}/amigos")
	@Produces(MediaType.APPLICATION_XML)
	public Response listaAmigos(@PathParam("id_usuario") String id, @QueryParam("nom_amigos") String nombr,
			@QueryParam("pag") int pag, @QueryParam("lim") int lim) {
		Usuarios usuario;
		ArrayList<Usuarios>  id_amigos= new ArrayList<Usuarios>(); 
		if (UsuariosDao.getInstance().getModel().containsKey(id)) {
			usuario = UsuariosDao.getInstance().getModel().get(id);
			ArrayList<String> amigos = usuario.getId_amigos();
			if(pag>amigos.size()) {
				return Response.status(Response.Status.BAD_REQUEST).build();	
			}
			for(int i = pag-1; i<amigos.size()&&lim-1>id_amigos.size();i++ ) {
				Usuarios amigo = UsuariosDao.getInstance().getModel().get(amigos.get(i));
				if(amigo.getNombre_completo().contains(nombr))
					id_amigos.add(amigo);
			}
			if(id_amigos.size()==0)
				return Response.status(Response.Status.NOT_FOUND).build();
			return Response.ok(id_amigos).build();

		} else
			return Response.status(Response.Status.NOT_FOUND).build();		
	}
	//Filtro con fecha
	@GET
	@Path("/tesoros")
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
	@Path("/tesoros")
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
	@Path("/tesoros")
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
