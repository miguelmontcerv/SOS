package Daos;
import Models.Usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UsuariosDao {
	private Map<String, Usuarios> contentProvider = new HashMap<>();
	  
	private static UsuariosDao instance = null;
	
	private UsuariosDao() {
	    Usuarios usuario = new Usuarios();
	    usuario.setUsuario("Admina");
	    usuario.setId("1");
	    usuario.setNombre_completo("Admina");
	    
	    usuario.setId_amigos("2");
	    usuario.setId_amigos("3");
	    usuario.setId_amigos("4");
	    
	    contentProvider.put("1", usuario);
	    
	    Usuarios usuario2 = new Usuarios();
	    
	    usuario2.setUsuario("Miguela01");
	    usuario2.setId("2");
	    usuario2.setNombre_completo("Miguela01");
	    
	    usuario2.setId_amigos("1");
	    usuario2.setId_amigos("3");
	    usuario2.setId_amigos("4");
	    
	    contentProvider.put("2", usuario2);
	    
	    Usuarios usuario3 = new Usuarios();
	    
	    usuario3.setUsuario("Fernando08");
	    usuario3.setId("3");
	    usuario3.setNombre_completo("Fernando Ochoa");
	    
	    usuario3.setId_amigos("1");
	    usuario3.setId_amigos("2");
	    usuario3.setId_amigos("4");
	    
	    contentProvider.put("3", usuario3);
	    
	    Usuarios usuario4 = new Usuarios();
	    
	    usuario4.setUsuario("Maria23");
	    usuario4.setId("4");
	    usuario4.setNombre_completo("Maria Bere");
	    
	    usuario4.setId_amigos("1");
	    usuario4.setId_amigos("2");
	    usuario4.setId_amigos("3");
	    
	    contentProvider.put("4", usuario4);
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
