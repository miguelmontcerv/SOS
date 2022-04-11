package rest1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TesorosDao {
    private static Map<String, Tesoros> contentProvider = new HashMap<>();
	  
	private static TesorosDao instance = null;
	
	private TesorosDao() {
	    Tesoros tesoro = new Tesoros();
	    tesoro.setId("1");
        tesoro.setPista("Tesoro puesto por el Admin");
	    contentProvider.put("1", tesoro);

        Tesoros tesoro2 = new Tesoros();
	    tesoro2.setId("2");
        tesoro2.setPista("Otro tesoro puesto por el Admin");
	    contentProvider.put("2", tesoro2);
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
