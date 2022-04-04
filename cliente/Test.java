import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;


public class Test {

	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();

		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(getBaseURI());

		//System.out.println(target.path("v1").path("usuarios").request().
				//accept(MediaType.TEXT_XML).get(String.class));
		
		
		//System.out.println(target.path("v1").path("usuarios").request().
			//	accept(MediaType.APPLICATION_XML).get(String.class));
				
		Usuarios m = target.path("v1").path("usuarios").request().
				accept(MediaType.APPLICATION_XML).get(Usuarios.class);

		System.out.println("El nombre del user es: "+m.getUsuario()+" y su id es: "+m.getId());

		System.out.println(target.path("v1").path("usuarios").request().accept(MediaType.APPLICATION_XML).get(String.class));	

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/api.geoetsiinf/").build();
	}
	
}