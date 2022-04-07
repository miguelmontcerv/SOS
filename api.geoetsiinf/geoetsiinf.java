package rest1;

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
	 
	 //Ver a un usuario en especifico
	 @Path("{id_usuario}")
	  @GET
	  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
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
	 @Consumes(MediaType.APPLICATION_XML)
	 public Response postUsuario(Usuarios userRequest) {
		 String res;
		 
		 if(userRequest == null) {
			 res = "El body esta vacio"; 
			 return Response.noContent().build();
		 }
		 else {
			 UsuariosDao.getInstance().getModel().put(userRequest.getId(), userRequest);
			 res = "Se ha creado el user " + userRequest.getId();
			 return  Response.status(Response.Status.CREATED).entity(res).header("Location",uriInfo.getAbsolutePath().toString()).build();
		 }
	 }
	 
	 
	  // Este método se invoca si se solicita TEXT_PLAIN
	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public Response saludoPlainText() {
		  String respuesta = "Servidor dado de alta correctamente";
		  return Response.status(Response.Status.ACCEPTED).entity(respuesta).header("Location", 
				  uriInfo.getAbsolutePath().toString()+"/otra").build();
	  }
	  
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
	public Response addTesoroHist(@PathParam("id_usuario") String id, Tesoros[] userRequest) {

		Usuarios usuario;
		if (UsuariosDao.getInstance().getModel().containsKey(id)) {
			usuario = UsuariosDao.getInstance().getModel().get(id);
			ArrayList<Tesoros> tesoros = usuario.getTesoros_encontrados();

			for (int i = 0; i < tesoros.size(); i++) {
				if (userRequest==null)
					return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
				if (userRequest[0].getId()==0)
					return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
				if (tesoros.get(i).getId() == userRequest[0].getId()) {
					return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
				}
			}
			UsuariosDao.getInstance().getModel().get(id).setTesoros_encontrados(userRequest[0]);
			return Response.status(Response.Status.CREATED).build();
		} else {
			// throw new RuntimeException("Get: Tarea con id " + id + " no encontrada");
			return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
		}

	}
	  
}
