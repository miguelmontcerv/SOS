package rest1;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/usuarios")
public class geoetsiinf {

	 @Context
	  private UriInfo uriInfo;
		
	  // Este método se invoca si se solicita TEXT_PLAIN
	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public Response saludoPlainText() {
		  String respuesta = "Hola Informatico";
		  return Response.status(Response.Status.ACCEPTED).entity(respuesta).header("Location", 
				  uriInfo.getAbsolutePath().toString()+"/otra").build();
	  }
	  
	  @GET
	  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		public Usuarios getXML() {
			Usuarios user = new Usuarios();
			user.setUsuario("Miguel01");
			user.setId(1);
			user.setNombre_completo("Miguel Angel Monteros");
			user.setCorreo("miguel@upm.es");
			user.setEdad(21);
			user.setLocalidad("Madrid");
			
			/*user.setId_amigos(12);
			user.setId_amigos(64);
			
			user.setNom_amigos("Ana");
			user.setNom_amigos("Lalo");*/
			return user;
		}
	  
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public String saludoXML() {
	    return "<?xml version=\"1.0\"?>" + "<hola>Hola Informatico" + "</hola>";
	  }
	  
	  @GET
	  @Path("saluda/{nombre}/{apellido}")
	  @Produces(MediaType.TEXT_HTML)
	  public String saludoHtml(@PathParam("nombre") String n, @PathParam("apellido") String a, 
			  @QueryParam("apellido2") String a2) {
	    return "<html>" + "<title>" + "Hola Informatico" + "</title>"
	        + "<body><h1>" + "Hola " + n +" "+ a + " "+ a2 + "</h1></body>" + "</html> ";
	  }
	
}
