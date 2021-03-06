package Models;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement()
public class Tesoros{
    private String id;
    private String id_user;
    private String pista;
    private float coor_x;
    private float coor_y;
    private String estado;
    private String fecha_post;
    private String fecha_update;
    private String fecha_encontrado;
    private String id_encontrado;
    private String dificultad;
    private int tam;
    private String tipo_terreno;

    @XmlElement(name="id_tesoro")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name="id_user")
    public String getId_user() {
        return id_user;
    }
    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
    
    @XmlElement(name="pista")
    public String getPista() {
        return pista;
    }
    public void setPista(String pista) {
        this.pista = pista;
    }

    @XmlElement(name="coor_x")
    public float getCoor_x() {
        return coor_x;
    }
    public void setCoor_x(float coor_x) {
        this.coor_x = coor_x;
    }
    
    @XmlElement(name="coor_y")
    public float getCoor_y() {
        return coor_y;
    }
    public void setCoor_y(float coor_y) {
        this.coor_y = coor_y;
    }

    @XmlElement(name="estado")
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @XmlElement(name="fecha_post")
    public String getFecha_post() {
        return fecha_post;
    }
    public void setFecha_post(String fecha_post) {
        this.fecha_post = fecha_post;
    }

    @XmlElement(name="fecha_update")
    public String getFecha_update() {
        return fecha_update;
    }
    public void setFecha_update(String fecha_upString) {
        this.fecha_update = fecha_upString;
    }
    
    @XmlElement(name="fecha_encontrado")
    public String getFecha_encontrado() {
        return fecha_encontrado;
    }
    public void setFecha_encontrado(String fecha_encontrado) {
        this.fecha_encontrado = fecha_encontrado;
    }
    
    @XmlElement(name="id_encontrado")
    public String getId_encontrado() {
        return id_encontrado;
    }
    public void setId_encontrado(String id_encontrado) {
        this.id_encontrado = id_encontrado;
    }

@XmlElement(name="dificultad")
    public String getDificultad() {
        return dificultad;
    }
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
    
    @XmlElement(name="tam")
    public int getTam() {
        return tam;
    }
    public void setTam(int tam) {
        this.tam = tam;
    }

@XmlElement(name="tipo_terreno")
    public String getTipo_terreno() {
        return tipo_terreno;
    }
    public void setTipo_terreno(String tipo_terreno) {
        this.tipo_terreno = tipo_terreno;
    }
    
}