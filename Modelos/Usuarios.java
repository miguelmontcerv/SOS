package rest1;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()

public class Usuarios {
	private int id;
    private String usuario;
    private String nombre_completo;
    private String correo;
    private int edad;
    private String localidad;

    private int [] id_amigos;
    private String [] nom_amigos;
    
    @XmlElement(name="id_usuario")

    public int getId() {
        return id;
    }
    public void setId(int id) {
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

        public int [] getId_amigos() {
            return id_amigos;
        }

        public int getId_amigos(int index) {
            return id_amigos[index];
        }

        public void setId_amigos(int id_amigo) {
            id_amigos[id_amigos.length] = id_amigo;
        }
        
   @XmlElement(name="nom_amigos")

        public String [] getNom_amigos() {
            return nom_amigos;
        }

        public String getNom_amigos(int index) {
            return nom_amigos[index];
        }

        public void setNom_amigos(String nom_amigo) {
            nom_amigos[nom_amigos.length] = nom_amigo;
        }
}
