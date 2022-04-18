package Daos;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import Models.Tesoros;

public class TesorosDao {
    private static Map<String, Tesoros> contentProvider = new HashMap<>();
    
    private static TesorosDao instance = null;
	
	private TesorosDao() {
	    
	}
	
	public Map<String, Tesoros> getModel(){
	    return contentProvider;
	} 
	  
	public static TesorosDao getInstance() {
		if (instance==null)
			instance = new TesorosDao();
		return instance;
	}    
	
	public static ArrayList<Tesoros> getLista() {
		ArrayList<Tesoros> listOfValues = contentProvider.values().stream()
				.collect(Collectors.toCollection(ArrayList::new));
		return listOfValues;
	}
}