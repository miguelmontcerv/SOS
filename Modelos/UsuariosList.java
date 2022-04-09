package Models;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuariosList {
	private List<Usuarios> l;
	
	public UsuariosList(){
	    
	 }
	  
	public UsuariosList (List<Usuarios> l){
	   this.l = l;
	 }
	 
	public List<Usuarios> getL() {
	   return l;
	}
	  
	public void setL(List<Usuarios> l) {
	  this.l = l;
	}
}
