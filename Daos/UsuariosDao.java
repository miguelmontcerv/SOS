package rest1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UsuariosDao {
	private Map<String, Usuarios> contentProvider = new HashMap<>();
	  
	private static UsuariosDao instance = null;
	
	private UsuariosDao() {
	    Usuarios usuario = new Usuarios();
	    usuario.setUsuario("Admin");
	    usuario.setId("1");
	    contentProvider.put("1", usuario);
	    
	    Usuarios usuario2 = new Usuarios();
	    usuario2.setUsuario("Miguel01");
	    usuario2.setId("2");
	    contentProvider.put("2", usuario2);
	  }
	
	public Map<String, Usuarios> getModel(){
	    return contentProvider;
	 }
	  
	  
	 public static UsuariosDao getInstance() {
		  if (instance==null)
			  instance = new UsuariosDao();
		return instance;
	}

	public ArrayList<Usuarios> getLista() {
		ArrayList<Usuarios> listOfValues = contentProvider.values().stream()
				.collect(Collectors.toCollection(ArrayList::new));
		return listOfValues;
	}

}
