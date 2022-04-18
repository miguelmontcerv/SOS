package Models;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TesorosList {
	private List<Tesoros> l;

	public TesorosList(){   
		 }

	public TesorosList (List<Tesoros> l){
		   this.l = l;
		 }
	public List<Tesoros> getL() {
		return l;
	}

	public void setL(List<Tesoros> l) {
		this.l = l;
	}

}