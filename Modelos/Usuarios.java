package Models;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement()

public class Usuarios {
	private String id;
    private String usuario;
    private String nombre_completo;
    private String correo;
    private int edad;
    private String localidad;
    
    private ArrayList<String>  id_amigos= new ArrayList<String>(); 
    private ArrayList<Tesoros> tesoros_encontrados = new ArrayList<Tesoros>(); 
    private ArrayList<Tesoros> tesoros_publicados = new ArrayList<Tesoros>();
    
    @XmlElement(name="id_usuario")

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name="usuario")

    	public String getUsuario() {
        	return usuario;
    	}
    
    	public void setUsuario(String usuario) {
    		this.usuario = usuario;
    	}
    	
    	@XmlElement(name="nombre_completo")

        public String getNombre_completo() {
            return nombre_completo;
        }
        public void setNombre_completo(String nombre_completo) {
            this.nombre_completo = nombre_completo;
        }
    
    @XmlElement(name="correo")

        public String getCorreo() {
            return correo;
        }
        public void setCorreo(String correo) {
            this.correo = correo;
        }
        
        @XmlElement(name="edad")

        public int getEdad() {
            return edad;
        }
        public void setEdad(int edad) {
            this.edad = edad;
        }
    
    @XmlElement(name="localidad")

        public String getLocalidad() {
            return localidad;
        }
        public void setLocalidad(String localidad) {
            this.localidad = localidad;
        }
        
        @XmlElement(name="id_amigos")

        public ArrayList<String> getId_amigos() {
            return id_amigos;
        }

        public String getId_amigos(int index) {
            return id_amigos.get(index);
        }

        public void setId_amigos(String id_amigo) {
            id_amigos.add(id_amigo);
        }
        
   
   @XmlElement(name="tesoros_encontrados")
   
   public ArrayList<Tesoros> getTesoros_encontrados(){
		return tesoros_encontrados;
	}
	public Tesoros getTesoros_encontrados(int index) {
		return tesoros_encontrados.get(index);
	}
	public void setTesoros_encontrados(Tesoros tesoro) {
		tesoros_encontrados.add(tesoro);
   }

	@XmlElement(name="tesoros_publicados")
    
	public ArrayList<Tesoros> getTesoros_publicados(){
		return tesoros_publicados;
	}
	public Tesoros getTesoros_publicados(int index) {
		return tesoros_publicados.get(index);
	}
	public void setTesoros_publicados(Tesoros tesoro) {
		tesoros_publicados.add(tesoro);
    }
	
}
