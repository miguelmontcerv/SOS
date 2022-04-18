package rest1;
import java.text.ParseException;
import Models.Usuarios;
import Models.UsuariosList;
import Models.TesorosList;
import java.util.Iterator;
import Models.Tesoros;
import Models.distanciaTesoro;
import Daos.UsuariosDao;
import Daos.TesorosDao;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.Collections;
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
	
		@GET
		@Path("f")
		@Consumes(MediaType.TEXT_PLAIN)
		public Response getListaUsuariosFiltrados(@QueryParam("nombre") String nombre) {
			
	    List<Usuarios> users = new ArrayList<Usuarios>();
	    users.addAll(UsuariosDao.getInstance().getModel().values());
	    Usuarios aux; 
	    
	    UsuariosList lista = new UsuariosList(users);
	    
	    Iterator<Usuarios> i  = lista.getL().iterator();
	    
	    String s = "";
	    while (i.hasNext()) {
	    	aux = i.next();
	    	
	    	if(aux.getUsuario().contains(nombre))
	    		s = s + aux.getUsuario() + "\n";
	    }
	    if(s.equals(""))
	    	return Response.status(Response.Status.NOT_FOUND).entity("No hay tesoros con "+nombre+" en el nombre de usuario").build();
	    else return Response.ok(s).build();
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
		  }  else {
			  //throw new RuntimeException("Get: Tarea con id " + id +  " no encontrada");
		      res = Response.status(Response.Status.NOT_FOUND).build();
		      return null;
		  }
	  }
	
	 //Actualizar la informacion de un usuario en especifico, si lo encuentra regresa una instancia de èl, sino null
	 @PUT
	 @Path("{id_usuario}")
	 @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	  public Usuarios updateUsuario(@PathParam("id_usuario") String id, Usuarios userRequest) {
		 Response res;
		 
		  Usuarios usuario;
		  if(UsuariosDao.getInstance().getModel().containsKey(id)) {
			  usuario = UsuariosDao.getInstance().getModel().get(id);
			  
			  if(!userRequest.getId().equals(""))
				  UsuariosDao.getInstance().getModel().get(id).setId(userRequest.getId());
			  if(!userRequest.getNombre_completo().equals(""))
				  UsuariosDao.getInstance().getModel().get(id).setNombre_completo(userRequest.getNombre_completo());
			  if(!userRequest.getCorreo().equals(""))
				  UsuariosDao.getInstance().getModel().get(id).setCorreo(userRequest.getCorreo());
			  if(userRequest.getEdad() != 0)
				  UsuariosDao.getInstance().getModel().get(id).setEdad(userRequest.getEdad());
			  if(!userRequest.getLocalidad().equals(""))
				  UsuariosDao.getInstance().getModel().get(id).setLocalidad(userRequest.getLocalidad());
			  
		      res = Response.ok().build();
		      
		      usuario = UsuariosDao.getInstance().getModel().get(id);
		      
		      return usuario;
		  }  else {
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
		 else {
			 UsuariosDao.getInstance().getModel().put(userRequest.getId(), userRequest);
			 res = "Se ha creado el user " + userRequest.getId();
			 return  Response.status(Response.Status.CREATED).entity(res).header("Location",uriInfo.getAbsolutePath().toString()).build();
		 }
	 }
	 
	//Se agrega un tesoro encontrado al usuario que se marca en el Path, la info del tesoro es mandada por el body
	 @POST
	 @Path("{id_usuario}/tesoros_encontrados/{id_tesoro}")
	 @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	 public Response addTesoroEncontrado(@PathParam("id_usuario") String id,@PathParam("id_tesoro") String id_tesoro) {
		 
			Tesoros tesoro;
			Usuarios usuario;
			
			if (UsuariosDao.getInstance().getModel().containsKey(id) && TesorosDao.getInstance().getModel().containsKey(id_tesoro)) { //si el usuario exite
				
				tesoro = TesorosDao.getInstance().getModel().get(id_tesoro);
				usuario = UsuariosDao.getInstance().getModel().get(id);
			
				UsuariosDao.getInstance().getModel().get(id).setTesoros_encontrados(tesoro);
				TesorosDao.getInstance().getModel().get(id_tesoro).setId_encontrado(id);
				
				String res = "Se ha agregado el tesoro " + tesoro.getId();
				return Response.status(Response.Status.CREATED).entity(tesoro).header("Location",uriInfo.getAbsolutePath().toString()).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity(null).build();
			}
			
	 }
	 
	//Se agrega un tesoro publicado al usuario que se marca en el Path, la info del tesoro es mandada por el body
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
			TesorosDao.getInstance().getModel().put(userRequest.getId(), userRequest);
			
			String res = "El usuario "+id+" ha agregado el tesoro " + TesorosDao.getInstance().getModel().get(userRequest.getId()).getId();
			System.out.println(res);
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
				return Response.status(Response.Status.UNAUTHORIZED).entity("Eres tu mismo, no eres tu propio amigo").build();
			if (UsuariosDao.getInstance().getModel().containsKey(id)
					&& UsuariosDao.getInstance().getModel().containsKey(userRequest)) {
				usuarioA = UsuariosDao.getInstance().getModel().get(id);
				usuarioB = UsuariosDao.getInstance().getModel().get(userRequest);
				
				for (int i = 0; i < usuarioA.getId_amigos().size() + 1; i++) {
					if (i == usuarioA.getId_amigos().size())
						return Response.status(Response.Status.UNAUTHORIZED).entity("No se encuentra el amigo").build(); //No se encuentra al amigo en la lista
					if (usuarioA.getId_amigos(i).equals(userRequest)) {
						UsuariosDao.getInstance().getModel().get(id).getId_amigos().remove(i); //Se encuentra y se elimina
						break;
					}
				}
				for (int i = 0; i < usuarioB.getId_amigos().size() + 1; i++) {
					if (i == usuarioB.getId_amigos().size())
						return Response.status(Response.Status.UNAUTHORIZED).entity("No te encuentras en el amigo").build();
					if (usuarioB.getId_amigos(i).equals(id)) {
						UsuariosDao.getInstance().getModel().get(userRequest).getId_amigos().remove(i);
						break;
					}
				}
				
				//Se envia la respuesta
				String res = "Usuario  " + id + " ha borrado de amigos a " + userRequest;
				return Response.status(Response.Status.CREATED).entity(res)
						.header("Location", uriInfo.getAbsolutePath().toString()).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("No se encuentra el usuario principal").build();
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
					return Response.status(Response.Status.BAD_REQUEST).entity("La paginacion o el limite se salen de los parametros").build();	
				}
				
				for(int i = pag-1; i <  lim ;i++ ) {
					Usuarios amigo = UsuariosDao.getInstance().getModel().get(amigos.get(i));
					
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
		
	
		//Devuelve el listado de usuarios sin filtros
		@GET
		@Path("{id_usuario}/amigos/SP")
		@Produces(MediaType.APPLICATION_XML)
		public Response listaAmigosSP(@PathParam("id_usuario") String id) {
					
			Usuarios usuario;
			ArrayList<Usuarios>  id_amigos= new ArrayList<Usuarios>(); 
					
			if (UsuariosDao.getInstance().getModel().containsKey(id)) {
				usuario = UsuariosDao.getInstance().getModel().get(id);
						
				ArrayList<String> amigos = usuario.getId_amigos(); //Se genera un arreglo con los id de los amigos de user
						
				for(int i = 0; i <  amigos.size() ;i++ ) {
					Usuarios amigo = UsuariosDao.getInstance().getModel().get(amigos.get(i));
					System.out.println("id de amigos en la lista "+amigo.getId());
							
					id_amigos.add(amigo);
				}
						
				if(id_amigos.size()==0)
					return Response.status(Response.Status.NOT_FOUND).build();
				
				UsuariosList salida = new UsuariosList(id_amigos);
				return Response.ok(salida).build();
			
			} else
				return Response.status(Response.Status.NOT_FOUND).build();		
		}

		//Muestra el id de todos los tesoros en la aplicacion y el id de las parsonas que los publicaron
		@GET
		@Path("tesoros")
		@Consumes(MediaType.APPLICATION_XML)
		public Response listaTesoros(@QueryParam("id_tesoro") String id){
			List<Tesoros> tesoros = new ArrayList<Tesoros>();
		    tesoros.addAll(TesorosDao.getInstance().getModel().values());
		    
		    Tesoros auxiliar, retorno = null;
		    TesorosList lista = new TesorosList(tesoros);
		    
		    Iterator<Tesoros> i  = lista.getL().iterator();
		    
		    String s = "";
		    while (i.hasNext()) {
		    	auxiliar = i.next();

		    	s = s + "El id del usuario: "+auxiliar.getId_user()+", el id del tesoro: "+ auxiliar.getId()+", fecha publicacion: "+auxiliar.getFecha_post()+", fecha encontrado: "+auxiliar.getFecha_encontrado()+", tamaño: "+auxiliar.getTam()+", pista"+auxiliar.getPista()+", dificultad: "+auxiliar.getDificultad() + "\n";
		    	
		    	//Con id
		    	if(auxiliar.getId().equals(id))
		    		retorno = auxiliar;
		    }
		    
		    if(id != null) return Response.ok(retorno).build();
		     
		    if(s.equals(""))
		    	return Response.ok("No se encuentraron tesoros").build();
		    else
		    	return Response.ok(s).build();
		}
	
		//Lista de tesoros publicados con fecha
		@GET
		@Path("{id_user}/tesoros_publicados")
		@Consumes(MediaType.APPLICATION_XML)
		public Response listaTesorosPublicados(@PathParam("id_user") String id, @QueryParam("fecha_lim") String fechaM,
				@QueryParam("pag") int pag, @QueryParam("lim") int lim, @QueryParam("dif") String dif,
				@QueryParam("tam") int tam, @QueryParam("terreno") String terreno) throws Exception  {
			
			Date fechaMax = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(fechaM);
			List<Tesoros> tesoros = new ArrayList<Tesoros>();
			tesoros.addAll(TesorosDao.getInstance().getModel().values());
			Tesoros auxiliar;
			TesorosList lista = new TesorosList(tesoros);
			ArrayList<Tesoros> listaT = new ArrayList<Tesoros>();
			Iterator<Tesoros> i  = lista.getL().iterator();
			int contador = 1, contSolicitado = pag;
			
			if(pag> tesoros.size() || lim > tesoros.size() || pag < 1) { //Verificamos que la paginacion sea correcta
				return Response.status(Response.Status.BAD_REQUEST).entity("La paginacion o el limite esta incorrecto, verifica").build();	
			}
		
			while (i.hasNext()) {
				auxiliar = i.next();

				Date fecha =  (Date) new SimpleDateFormat("yyyy-mm-dd").parse(auxiliar.getFecha_post());
				
				if(fecha.before(fechaMax)||fecha.equals(fechaMax)) {
					if(auxiliar.getDificultad().equals(dif)&&auxiliar.getTipo_terreno().equals(terreno)&&auxiliar.getTam()==tam)
						if(auxiliar.getId_user().equals(id)) {
							if(contador >= pag && contSolicitado <= lim) {
								listaT.add(auxiliar);
								System.out.println(contador +">="+ pag +"&&"+ contador +"<="+lim);
								contSolicitado++;
							}
						}
							
				}
			contador++;
			}
			
			if(listaT.size()<=0)
				return Response.status(Response.Status.NOT_FOUND).entity("No se encontro tesoros en el usuario "+id).build();
			
			TesorosList salida = new TesorosList(listaT);
			return Response.ok(salida).build();
					
		}

		//Lista de tesoros encontrados con fecha
		@GET
		@Path("{id_user}/tesoros_encontrados")
		@Produces(MediaType.APPLICATION_XML)
		public Response listaTesorosEncontrados(@PathParam("id_user") String id, @QueryParam("fecha_lim") String fechaM,
				@QueryParam("pag") int pag, @QueryParam("lim") int lim, @QueryParam("dif") String dif,
				@QueryParam("tam") int tam, @QueryParam("terreno") String terreno) throws Exception  {
			
			Date fechaMax = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(fechaM);
			List<Tesoros> tesoros = new ArrayList<Tesoros>();
			tesoros.addAll(TesorosDao.getInstance().getModel().values());
			Tesoros auxiliar;
			TesorosList lista = new TesorosList(tesoros);
			ArrayList<Tesoros> listaT = new ArrayList<Tesoros>();
			Iterator<Tesoros> i  = lista.getL().iterator();
			int contador = 1, contSolicitado = pag;
			
			if(pag> tesoros.size() || lim > tesoros.size() || pag < 1) { //Verificamos que la paginacion sea correcta
				return Response.status(Response.Status.BAD_REQUEST).entity("La paginacion o el limite esta incorrecto, verifica").build();	
			}
		
			while (i.hasNext()) {
				auxiliar = i.next();

				Date fecha =  (Date) new SimpleDateFormat("yyyy-mm-dd").parse(auxiliar.getFecha_post());
				
				if(fecha.before(fechaMax)||fecha.equals(fechaMax)) {
					if(auxiliar.getDificultad().equals(dif)&&auxiliar.getTipo_terreno().equals(terreno)&&auxiliar.getTam()==tam)
						if(auxiliar.getId_encontrado().equals(id)) {
							if(contador >= pag && contSolicitado <= lim) {
								listaT.add(auxiliar);
								System.out.println(contador +">="+ pag +"&&"+ contador +"<="+lim);
								contSolicitado++;
							}
						}
							
				}
			contador++;
			}
			
			if(listaT.size()<=0)
				return Response.status(Response.Status.NOT_FOUND).entity("No se encontro tesoros en el usuario "+id).build();
			
			TesorosList salida = new TesorosList(listaT);
			return Response.ok(salida).build();
					
		}
		
		@DELETE
		@Path("{id_user}/tesoros/{id_tesoro}")
		public Response eliminarTesoro(@PathParam("id_user") String id,@PathParam("id_tesoro") String id_tesoro){
			Usuarios usuario;
			Tesoros tesoro;
			
			if (UsuariosDao.getInstance().getModel().containsKey(id) && TesorosDao.getInstance().getModel().containsKey(id_tesoro)) {
				usuario = UsuariosDao.getInstance().getModel().get(id);
				tesoro = TesorosDao.getInstance().getModel().get(id_tesoro);
				
				if(tesoro.getId_user().equals(usuario.getId())){
					for (int i = 0; i < usuario.getTesoros_publicados().size() + 1; i++) {
						if(usuario.getTesoros_publicados(i).getId().equals(tesoro.getId())) {
							TesorosDao.getInstance().getModel().remove(id_tesoro); //Se encuentra y se elimina
							System.out.println("La iteracion a eliminar es: "+i);
							UsuariosDao.getInstance().getModel().get(id).getTesoros_publicados().remove(i); //Se encuentra y se elimina
							return Response.ok().build();
						}
					}	
				} else return Response.status(Response.Status.BAD_REQUEST).entity("No es el dueño del tesoro, no lo puede eliminar").build();
		} else return Response.status(Response.Status.NOT_FOUND).entity("No se encontro el user o el tesoro").build();
	
			return Response.status(Response.Status.NOT_FOUND).entity("No se encontro el tesoro").build();
	}

		@DELETE
		@Path("{id_user}")
		public Response eliminarCuenta(@PathParam("id_user") String id){
			
			if (UsuariosDao.getInstance().getModel().containsKey(id)) {
				
				UsuariosDao.getInstance().getModel().remove(id); //Se encuentra y se elimina
				return Response.ok().build();

			} else return Response.status(Response.Status.NOT_FOUND).entity("No se encontro el user o el tesoro").build();
	}

		//Actualizar la informacion de un usuario en especifico, si lo encuentra regresa una instancia de èl, sino null
	@PUT
	@Path("tesoros/{id_tesoro}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		  public Tesoros updateTesoro(@PathParam("id_tesoro") String id, Tesoros userRequest) {
			 Response res;
			 
			  Tesoros tesoro;
			  
			  System.out.println("Se buscara el tesoro :" +id);
			  
			  if(TesorosDao.getInstance().getModel().containsKey(id)) {
				  System.out.println("Se encontro el tesoro :" +id);
				  tesoro = TesorosDao.getInstance().getModel().get(id);
				  
				  if(!userRequest.getFecha_update().equals(""))
					  TesorosDao.getInstance().getModel().get(id).setFecha_update(userRequest.getFecha_update());
				  if(userRequest.getCoor_x() != 0)
					  TesorosDao.getInstance().getModel().get(id).setCoor_x(userRequest.getCoor_x());
				  if(userRequest.getCoor_y() != 0)
					  TesorosDao.getInstance().getModel().get(id).setCoor_y(userRequest.getCoor_y());
				  if(!userRequest.getEstado().equals(""))
					  TesorosDao.getInstance().getModel().get(id).setEstado(userRequest.getEstado());
				  if(!userRequest.getDificultad().equals(""))
					  TesorosDao.getInstance().getModel().get(id).setDificultad(userRequest.getDificultad());
				  if(!userRequest.getTipo_terreno().equals(""))
					  TesorosDao.getInstance().getModel().get(id).setTipo_terreno(userRequest.getTipo_terreno());
				  if(userRequest.getTam() != 0)
					  TesorosDao.getInstance().getModel().get(id).setTam(userRequest.getTam());
				  if(!userRequest.getPista().equals(""))
					  TesorosDao.getInstance().getModel().get(id).setPista(userRequest.getPista());
				  
			      res = Response.ok().build();
			      
			      tesoro = TesorosDao.getInstance().getModel().get(id);
			      
			      return tesoro;
			  }  else {
				  System.out.println("No se encuentra el tesoro :" +id);
			      res = Response.status(Response.Status.NOT_FOUND).build();
			      return null;
			  }
		  }	
	
	// Filtro de tesoros cercanos a unas coordenadas con fecha
		@GET
		@Path("/tesoros/cerca_de")
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
					return Response.status(Response.Status.NOT_FOUND).entity("No hay tesoros con esas caracteristicas: "+fechaM+" "+" "+pag+":"+lim+" "+dif+" "+tam+" "+terreno).build();
				TesorosList salida = new TesorosList(lista);
				return Response.ok(salida).build();
			} catch (NumberFormatException e) {
				return Response.status(Response.Status.BAD_REQUEST)
						.entity("No se pudieron convertir los Ã­ndices a nÃºmeros").build();
			} catch (ParseException e) {
				return Response.status(Response.Status.BAD_REQUEST).entity("No se pudo leer la fecha").build();
			}
		}
		
}
