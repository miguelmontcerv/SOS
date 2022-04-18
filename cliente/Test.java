import Models.Usuarios;

public class Test {

	public static void main(String[] args) {
		
		Ventana ventana = new Ventana();                
        ventana.setVisible(true);
        
		
		Usuarios fernando = new Usuarios();
		
		fernando.setId("8");
		fernando.setUsuario("FernandoVIII");
		fernando.setNombre_completo("Fernadno VIII");
		fernando.setLocalidad("Madrid");
		
		/*Response response = target.path("v1").path("usuarios").request().accept(MediaType.APPLICATION_XML).post(Entity.xml(fernando),Response.class);
		
		System.out.println(response.getStatus());*/
	}	
}