/* Librerias para GUI */
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/* Librerias para comunicacion */
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;


public class Ventana extends JFrame{
    
    //private Scanner in = new Scanner(System.in);
    JPanel panel1, panel2, panel3, panel4;
    
    JButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12,button13,button14, button15;
    
    JTextArea areaTexto, areaTexto2;
    
    JLabel label1, label2, label3, label4, label5;
    
    /* Seccion Web */
    ClientConfig config;

	Client client;

	WebTarget target;
	
	///////////////
	
	Usuarios user;
    
    public Ventana(){
        setSize(1240,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("PROYECTO SOS");
        
        iniciarComponentes();
        
        iniciarComunicacion();
        
        escucharBotones();
    }
    
    public void iniciarComponentes(){
    	
    	panel1 = new JPanel(); //Izquierda
        panel2 = new JPanel(); //Central
        panel3 = new JPanel(); //Derecha
        panel4 = new JPanel(); //Superior

    	button1 = new JButton("Iniciar Sesion");
        button1.setFocusPainted(false);
        button1.setMargin(new Insets(0, 0, 0, 0));        
        button1.setBorderPainted(false);
        button1.setOpaque(true);
        button1.setBackground(new Color(29,35,57));
        button1.setForeground(Color.white);
        
        button2 = new JButton("Agregar un nuevo usuario");
        button2.setFocusPainted(false);
        button2.setMargin(new Insets(0, 0, 0, 0));        
        button2.setBorderPainted(false);
        button2.setOpaque(true);
        button2.setBackground(new Color(29,35,57));
        button2.setForeground(Color.white);
        
        button3 = new JButton("Consultar perfil");
        button3.setFocusPainted(false);
        button3.setMargin(new Insets(0, 0, 0, 0));        
        button3.setBorderPainted(false);
        button3.setOpaque(true);
        button3.setBackground(new Color(29,35,57));
        button3.setForeground(Color.white);
        
        button4 = new JButton("Actualizar Informacion");
        button4.setFocusPainted(false);
        button4.setMargin(new Insets(0, 0, 0, 0));        
        button4.setBorderPainted(false);
        button4.setOpaque(true);
        button4.setBackground(new Color(29,35,57));
        button4.setForeground(Color.white);
        
        button5 = new JButton("Eliminar Cuenta");
        button5.setFocusPainted(false);
        button5.setMargin(new Insets(0, 0, 0, 0));        
        button5.setBorderPainted(false);
        button5.setOpaque(true);
        button5.setBackground(new Color(29,35,57));
        button5.setForeground(Color.white);

        button6 = new JButton("Listado de Usuarios");
        button6.setFocusPainted(false);
        button6.setMargin(new Insets(0, 0, 0, 0));        
        button6.setBorderPainted(false);
        button6.setOpaque(true);
        button6.setBackground(new Color(29,35,57));
        button6.setForeground(Color.white);
        
        button7 = new JButton("Agregar/Editar Tesoro");
        button7.setFocusPainted(false);
        button7.setMargin(new Insets(0, 0, 0, 0));        
        button7.setBorderPainted(false);
        button7.setOpaque(true);
        button7.setBackground(new Color(29,35,57));
        button7.setForeground(Color.white);
        
        button8 = new JButton("Eliminar Tesoro");
        button8.setFocusPainted(false);
        button8.setMargin(new Insets(0, 0, 0, 0));        
        button8.setBorderPainted(false);
        button8.setOpaque(true);
        button8.setBackground(new Color(29,35,57));
        button8.setForeground(Color.white);
        
        button9 = new JButton("Consultar Tesoros");
        button9.setFocusPainted(false);
        button9.setMargin(new Insets(0, 0, 0, 0));        
        button9.setBorderPainted(false);
        button9.setOpaque(true);
        button9.setBackground(new Color(29,35,57));
        button9.setForeground(Color.white);
        
        button10 = new JButton("Buscar Tesoro!");
        button10.setFocusPainted(false);
        button10.setMargin(new Insets(0, 0, 0, 0));        
        button10.setBorderPainted(false);
        button10.setOpaque(true);
        button10.setBackground(new Color(29,35,57));
        button10.setForeground(Color.white);
        
        button11 = new JButton("Agregar Amigos");
        button11.setFocusPainted(false);
        button11.setMargin(new Insets(0, 0, 0, 0));        
        button11.setBorderPainted(false);
        button11.setOpaque(true);
        button11.setBackground(new Color(29,35,57));
        button11.setForeground(Color.white);
        
        button12 = new JButton("Eliminar Amigo");
        button12.setFocusPainted(false);
        button12.setMargin(new Insets(0, 0, 0, 0));        
        button12.setBorderPainted(false);
        button12.setOpaque(true);
        button12.setBackground(new Color(29,35,57));
        button12.setForeground(Color.white);
        
        button13 = new JButton("Consultar Amigo");
        button13.setFocusPainted(false);
        button13.setMargin(new Insets(0, 0, 0, 0));        
        button13.setBorderPainted(false);
        button13.setOpaque(true);
        button13.setBackground(new Color(29,35,57));
        button13.setForeground(Color.white);
        
        button14 = new JButton("Tesoros Cercanos a ...");
        button14.setFocusPainted(false);
        button14.setMargin(new Insets(0, 0, 0, 0));        
        button14.setBorderPainted(false);
        button14.setOpaque(true);
        button14.setBackground(new Color(29,35,57));
        button14.setForeground(Color.white);
        
        button15 = new JButton("Actualizar Tesoro");
        button15.setFocusPainted(false);
        button15.setMargin(new Insets(0, 0, 0, 0));        
        button15.setBorderPainted(false);
        button15.setOpaque(true);
        button15.setBackground(new Color(29,35,57));
        button15.setForeground(Color.white);
        
        
      //Areas para registros de historial

        areaTexto = new JTextArea();
        areaTexto.setBounds(2,2,319, 659);
        areaTexto.setText(">_");
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Roboto",Font.PLAIN,15));
        areaTexto.setBackground(new Color(168,218,220));
        areaTexto.setForeground(new Color(29,35,57));
        
        areaTexto2 = new JTextArea();
        areaTexto2.setBounds(30,180,600,480);
        areaTexto2.setText("Bienvenido a GeoETSIINF! ");
        areaTexto2.setEditable(false);
        areaTexto2.setFont(new Font("Roboto",Font.PLAIN,14));
        //areaTexto2.setBackground(new Color(168,218,220));        
        areaTexto2.setForeground(new Color(29,35,57));
        
        this.setLayout(null);
        panel1.setLayout(null);
        panel2.setLayout(null);
        panel3.setLayout(null);
        panel4.setLayout(null);
        
        /* Etiquetas */
        label1 = new JLabel("Menú",SwingConstants.CENTER);        
        label1.setFont(new Font("Roboto",Font.PLAIN,20));
        label1.setForeground(Color.white);

        label2 = new JLabel("P R O Y E C T O   R E S T f u l",SwingConstants.CENTER);        
        label2.setFont(new Font("Roboto",Font.PLAIN,20));
        label2.setForeground(Color.black);
        
        label3 = new JLabel("Universidad Politecnica de Madrid",SwingConstants.CENTER);        
        label3.setFont(new Font("Roboto",Font.PLAIN,20));
        label3.setForeground(Color.black);
                
        label4 = new JLabel("ETSI INFORMATICA",SwingConstants.CENTER);        
        label4.setFont(new Font("Roboto",Font.PLAIN,20));
        label4.setForeground(Color.black);
        
        label5 = new JLabel("Sistemas Orientados a Servicios",SwingConstants.CENTER);        
        label5.setFont(new Font("Roboto",Font.PLAIN,20));
        label5.setForeground(Color.black);


        panel1.setBackground(new Color(29,35,57));
        panel2.setBackground(new Color(168,218,220));
        panel3.setBackground(new Color(255,255,255));
        panel4.setBackground(new Color(45,123,157));
        
        panel1.setBounds(0, 0, 260, 720); //x,y                
        panel2.setBounds(260, 60, 320, 660);
        panel3.setBounds(580, 60, 660, 660);
        panel4.setBounds(260, 0, 980, 60);
        
        button1.setBounds(0,60,260,30); //Posicionx, posiciony, tamaño,tamaño        
        button2.setBounds(0,90,260,30); //Posicionx, posiciony, tamaño,tamaño                
        button3.setBounds(0,120,260,30); //Posicionx, posiciony, tamaño,tamaño
        button4.setBounds(0,150,260,30); //Posicionx, posiciony, tamaño,tamaño
        button5.setBounds(0,180,260,30);
        button6.setBounds(0,210,260,30);
        button7.setBounds(0,240,260,30);
        button8.setBounds(0,270,260,30);
        button9.setBounds(0,300,260,30);
        button10.setBounds(0,330,260,30);
        button11.setBounds(0,360,260,30);
        button12.setBounds(0,390,260,30);
        button13.setBounds(0,420,260,30);
        button14.setBounds(0,450,260,30);
        button15.setBounds(0,480,260,30);
        
        
        label1.setBounds(20,10,100,50); //Posicionx, posiciony, tamaño,tamaño        
        label2.setBounds(300,10,300,35); //Posicionx, posiciony, tamaño,tamaño        
        label3.setBounds(110,20,400,35); //Posicionx, posiciony, tamaño,tamaño        
        label4.setBounds(110,60,400,35); //Posicionx, posiciony, tamaño,tamaño        
        label5.setBounds(110,100,400,35); //Posicionx, posiciony, tamaño,tamaño        
        
        panel1.add(label1);                
        panel4.add(label2);
        panel3.add(label3);
        panel3.add(label4);
        panel3.add(label5);
        
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);
        panel1.add(button5);
        panel1.add(button6);
        panel1.add(button7);
        panel1.add(button8);
        panel1.add(button9);
        panel1.add(button10);
        panel1.add(button11);
        panel1.add(button12);
        panel1.add(button13);
        panel1.add(button14);
        panel1.add(button15);
        
        panel2.add(areaTexto);        
        panel3.add(areaTexto2);


        this.getContentPane().add(panel1);
        this.getContentPane().add(panel2);
        this.getContentPane().add(panel3);
        this.getContentPane().add(panel4);
        
    }

    public void iniciarComunicacion() {
    	config = new ClientConfig();

		client = ClientBuilder.newClient(config);

		target = client.target(getBaseURI());
		
		System.out.println(target.path("v1").path("usuarios").request().accept(MediaType.TEXT_PLAIN).get(String.class));
    	
		String s = "\n\nSe ha establecido conexion con "+ target.path("v1").path("usuarios").path("Path").request().accept(MediaType.TEXT_PLAIN).get(String.class) +"\nPuede comenzar a utilizar la aplicacion";
		
		areaTexto2.append(s);
		
    }
    
    private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/api.geoetsiinf/").build();
	}
    
    public void escucharBotones() {
    	ActionListener oyente = new ActionListener(){
    	    @Override
    	    public void actionPerformed(ActionEvent e){
    	        JButton btn1 = new JButton(); btn1 = (JButton) e.getSource();
    	        if(btn1.getText() == "Iniciar Sesion"){
    	        	IniciarSesion();      
    	        }
    	        if(btn1.getText() == "Agregar un nuevo usuario"){
    	        	crearUser(null);
    	        }
    	        if(btn1.getText() == "Consultar perfil"){
    	        	consultarPerfil();
    	        }
    	        if(btn1.getText() == "Actualizar Informacion"){
    	        	actualizarUser();  
    	        }
    	        if(btn1.getText() == "Eliminar Cuenta"){
    	        	eliminarCuenta();
    	        }
    	        if(btn1.getText() == "Listado de Usuarios"){                                        
    	        	listaUsuarios();
    	        }
    	        if(btn1.getText() == "Agregar/Editar Tesoro"){
    	        	agregarEditarTesoro();
    	        }
    	        if(btn1.getText() == "Eliminar Tesoro"){
    	        	eliminarTesoroPublicado();
    	        }
    	        if(btn1.getText() == "Consultar Tesoros"){
    	        	consultarTesoros();
    	        }
    	        if(btn1.getText() == "Buscar Tesoro!"){
    	        	agregarTesoroEncontrado();
    	        }
    	        if(btn1.getText() == "Agregar Amigos"){
    	        	agregarAmigo();
    	        }
    	        if(btn1.getText() == "Eliminar Amigo"){
    	        	eliminarAmigo();
    	        }
    	        if(btn1.getText() == "Consultar Amigo"){
    	        	consultarAmigos();
    	        }
    	        if(btn1.getText() == "Tesoros Cercanos a ..."){
    	        	System.out.println(btn1.getText());
    	        }
    	        if(btn1.getText() == "Actualizar Tesoro"){
    	        	actualizarTesoro();
    	        }
    	    }
    	};
    	
    	button1.addActionListener(oyente);
    	button2.addActionListener(oyente);
    	button3.addActionListener(oyente);
    	button4.addActionListener(oyente);
    	button5.addActionListener(oyente);
    	button6.addActionListener(oyente);
    	button7.addActionListener(oyente);
    	button8.addActionListener(oyente);
    	button9.addActionListener(oyente);
    	button10.addActionListener(oyente);
    	button11.addActionListener(oyente);
    	button12.addActionListener(oyente);
    	button13.addActionListener(oyente);
    	button14.addActionListener(oyente);
    	button15.addActionListener(oyente);
    	
    }

    public void IniciarSesion(){
    	//Verificamos sin un usuario esta en la pagina
    	String id_temp = JOptionPane.showInputDialog(null,"Ingrese el Id del usuario con el que desea registrarse: ");
    	user = target.path("v1").path("usuarios").path(id_temp).request().accept(MediaType.APPLICATION_XML).get(Usuarios.class);
    	
    	if(user == null) {
    		String res = JOptionPane.showInputDialog(null,"Usuario no encontrado, desea crear un usuario con el id "+id_temp+"? (s/n)");
    		if(res.charAt(0) == 's')
    			crearUser(id_temp);
    		else if(res.charAt(0) != 'n')
    			JOptionPane.showMessageDialog(null,"Opcion no valida");
    	}
    		
    	else 
    		JOptionPane.showMessageDialog(null,"Bienvenido usuario con user "+user.getId()+" y nombre es "+user.getUsuario()+" ha iniciado sesion correctamente");
    }
    
    public void crearUser(String id) {
	   Usuarios usTemporal = new Usuarios();
	   String res;
	   
	   if (id != null)
		   usTemporal.setId(id);
	   else
		   usTemporal.setId(JOptionPane.showInputDialog(null,"Agrege el id del usuario"));
	   
	   //pedir el resto de datos del usuario
	   usTemporal.setUsuario(JOptionPane.showInputDialog(null,"Agrege el nombre de usuario"));
	   usTemporal.setNombre_completo(JOptionPane.showInputDialog(null,"Agrega el nombre completo de usuario"));
	   
	   Response response = target.path("v1").path("usuarios").request().accept(MediaType.APPLICATION_XML).post(Entity.xml(usTemporal),Response.class);
	   
	   
	   if(response.getStatus() == 201) {
		   JOptionPane.showMessageDialog(null,"El usuario con el id "+ usTemporal.getId() + " se ha creado correctamente");
		   res = JOptionPane.showInputDialog(null,"Quiere iniciar sesion como el usuario "+usTemporal.getUsuario()+"? (s/n)");
		   
		   if(res.charAt(0) =='s') {
			   user = usTemporal;
			   usTemporal = null;
			   JOptionPane.showMessageDialog(null,"Bienvenido usuario con user "+user.getId()+" y nombre es "+user.getUsuario()+" ha iniciado sesion correctamente");
		   }
   		   else if(res.charAt(0) != 'n')
   			   JOptionPane.showMessageDialog(null,"Opcion no valida");
	   }
	   else {
		   JOptionPane.showMessageDialog(null,"No ha sido posible crear el usuario, problema: "+response.getStatus()+".-"+response.getEntity());
		   areaTexto.append("No ha sido posible crear el usuario, problema:\n"+response.getStatus()+"\n.-"+response.getEntity());
	   }
		   
		   
   }

    public void listaUsuarios() {
    	JOptionPane.showMessageDialog(null,target.path("v1").path("usuarios").request().accept(MediaType.TEXT_PLAIN).get(String.class));
    	
    }
    
    public void consultarPerfil() {
    	String info = "";
    	String res = JOptionPane.showInputDialog(null,"Quiere consultar su propio perfil? (s/n)");
    	
    	if(res.charAt(0) =='s') {
    		info = info + "El id es: " +user.getId()+", el nombre de usuario es: "+user.getUsuario()
    		+"\nSu nombre completo es: "+user.getNombre_completo()+", su correo es: "+user.getCorreo()+
    		"\nSu edad es: "+user.getEdad()+" y su localidad es: "+user.getLocalidad();
    		
    		JOptionPane.showMessageDialog(null,info);
    	}
    	else if(res.charAt(0) =='n') {
    		String id_temp = JOptionPane.showInputDialog(null,"Ingrese el Id del usuario que desea ver su perfil: ");
        	Usuarios user_temp = target.path("v1").path("usuarios").path(id_temp).request().accept(MediaType.APPLICATION_XML).get(Usuarios.class);
        	
        	if(user_temp != null){
        		info = info + "El id es: " +user_temp.getId()+", el nombre de usuario es: "+user_temp.getUsuario()
        		+"\nSu nombre completo es: "+user_temp.getNombre_completo()+", su correo es: "+user_temp.getCorreo()+
        		"\nSu edad es: "+user_temp.getEdad()+" y su localidad es: "+user_temp.getLocalidad();
        	
        		JOptionPane.showMessageDialog(null,info);
        	}
        	else JOptionPane.showMessageDialog(null,"El usuario con id "+id_temp+" no fue encontrado");
    	}
    	else JOptionPane.showMessageDialog(null,"Opcion no valida");
    }

    public void agregarEditarTesoro() {
    	//construimos un objeto de la clase Tesoros y dejamos que el user lo llene, despues se lo mandamos al server
    	
    	Tesoros tesoro = new Tesoros();
    	
    	tesoro.setId(JOptionPane.showInputDialog(null,"Indique el id del tesoro"));
    	JOptionPane.showMessageDialog(null,"Se agregara el tesoro "+tesoro.getId()+" al usuario "+user.getUsuario());
    	tesoro.setId_user(user.getId());
    	tesoro.setPista(JOptionPane.showInputDialog(null,"Indique la pista del tesoro"));
    	
    	tesoro.setCoor_x(Float.parseFloat(JOptionPane.showInputDialog(null,"Indique la coordenada en x")));
    	tesoro.setCoor_y(Float.parseFloat(JOptionPane.showInputDialog(null,"Indique la coordenada en y")));
    	
    	tesoro.setDificultad(JOptionPane.showInputDialog(null,"Indique la dificultad del tesoro"));
    	tesoro.setTipo_terreno(JOptionPane.showInputDialog(null,"Indique el tipo de terreno del tesoro"));
    	tesoro.setTam(Integer.parseInt(JOptionPane.showInputDialog(null,"Indique el tamaño del tesoro")));
    	
    	tesoro.setFecha_encontrado("2022-05-01");
    	tesoro.setFecha_post("2022-04-11");
    	tesoro.setFecha_update(null);
    	tesoro.setEstado("Sin Encontrar");
    	tesoro.setId_encontrado(null);
    	
    	Response response = target.path("v1").path("usuarios").path(user.getId()).path("tesoros_publicados").request().accept(MediaType.APPLICATION_XML).post(Entity.xml(tesoro),Response.class);
 	   
 	   
 	   if(response.getStatus() == 201) {
 		  JOptionPane.showMessageDialog(null,"Se ha agregado el tesoro "+tesoro.getId()+" al usuario "+user.getUsuario());
 	   }
 	   else{
		   JOptionPane.showMessageDialog(null,"No ha sido posible crear el tesoro, problema: "+response.getStatus()+".-"+response.getEntity());
		   areaTexto.append("No ha sido posible crear el usuario, problema:\n"+response.getStatus()+"\n.-"+response.getEntity());
	   }
    	
    }

    public void agregarAmigo(){
    	String id_amigo = JOptionPane.showInputDialog(null,"Indique el id del amigo que desea agregar");
    	
    	
    	Response response = target.path("v1").path("usuarios").path(user.getId()).path("amigos").queryParam("id_amigos", id_amigo).request().post(null,Response.class);
    	
    	if(response.getStatus() == 201) 
 		   JOptionPane.showMessageDialog(null,"Ahora es amigo del usuario con id: "+id_amigo);
    	else{
 		   JOptionPane.showMessageDialog(null,"No ha sido posible agregar a "+id_amigo+" como amigo, problema: "+response.getStatus()+".-"+response.getEntity());
 		   areaTexto.append("No ha sido posible agregarlo como amigo, problema:\n"+response.getStatus()+"\n.-"+response.getEntity());
 	   }
    }

    public void eliminarAmigo() {
    	String id_amigo = JOptionPane.showInputDialog(null,"Indique el id del amigo que desea eliminar");
    	
    	Response response = target.path("v1").path("usuarios").path(user.getId()).path("amigos").queryParam("id_amigos", id_amigo).request().delete();
    	
    	if(response.getStatus() == 201) 
 		   JOptionPane.showMessageDialog(null,"Se ha eliminado el amigo con el usuario con id: "+id_amigo);
    	else{
 		   JOptionPane.showMessageDialog(null,"No ha sido posible eliminar a "+id_amigo+" como amigo, problema: "+response.getStatus()+".-"+response.getEntity());
 		   areaTexto.append("No ha sido posible eliminar, problema:\n"+response.getStatus()+"\n.-"+response.getEntity());
 	   }
    }
   
   public void consultarAmigos() {
	UsuariosList salida = new UsuariosList();
	String res = JOptionPane.showInputDialog(null,"Quiere filtrar a sus amigos por nombre y paginacion? (s/n)");
	Response respuesta;
	
   	if(res.charAt(0) =='s') {
	   String patron = JOptionPane.showInputDialog(null,"Ingrese el patron de busqueda del nombre: ");
	   String pag = JOptionPane.showInputDialog(null,"Ingrese el limite inferior: ");
	   String lim = JOptionPane.showInputDialog(null,"Ingrese el limite superior: ");
	   
	   respuesta = target.path("v1").path("usuarios").path(user.getId()).path("amigos").queryParam("nom_amigos", patron).queryParam("pag", pag).queryParam("lim", lim).request().accept(MediaType.APPLICATION_XML).get();
	   
	   if(respuesta.getStatus() != 200) {
		   JOptionPane.showMessageDialog(null,"No es posible consultar a este usuario");
		   return;
	   }
   	
	  salida = target.path("v1").path("usuarios").path(user.getId()).path("amigos").queryParam("nom_amigos", patron).queryParam("pag", pag).queryParam("lim", lim).request().accept(MediaType.APPLICATION_XML).get(UsuariosList.class);
   	}   
   	else if(res.charAt(0) =='n') {
   		respuesta = target.path("v1").path("usuarios").path(user.getId()).path("amigos").path("SP").request().accept(MediaType.APPLICATION_XML).get();
   		
   		if(respuesta.getStatus() != 200) {
 		   JOptionPane.showMessageDialog(null,"No es posible consultar a este usuario");
 		   return;
 	   }
   		
   		salida = target.path("v1").path("usuarios").path(user.getId()).path("amigos").path("SP").request().accept(MediaType.APPLICATION_XML).get(UsuariosList.class);
   	}
	   
		   
	   Iterator<Usuarios> i  = salida.getL().iterator();
	    
	    String s = "";
	    while (i.hasNext()) {
	    	s = s + i.next().getUsuario() + "\n";
	    }
	    
	    JOptionPane.showMessageDialog(null,s);
	   
   }

   public void eliminarTesoroPublicado() {
	   String id_tesoro = JOptionPane.showInputDialog(null,"Indique el id del tesoro publicado que desea eliminar");
	   
	   Response response = target.path("v1").path("usuarios").path(user.getId()).path("tesoros").path(id_tesoro).request().delete();
	   
	   if(response.getStatus() == 200) 
 		   JOptionPane.showMessageDialog(null,"Se ha eliminado el tesoro con el usuario con id: "+id_tesoro);
    	else{
 		   JOptionPane.showMessageDialog(null,"No ha sido posible eliminar el tesoro "+id_tesoro+", problema: "+response.getStatus()+".-"+response.getEntity());
 		   areaTexto.append("No ha sido posible eliminar, problema:\n"+response.getStatus()+"\n.-"+response.getEntity());
 	   }
   }
   
   public void consultarTesoros() {
	   
	   //Parametros de la busqueda
	   String fecha, pag, lim, dif, tam, terreno;
	   Response respuesta;
	   Tesoros tesoroAx;
	   TesorosList salida;
	   
	   Iterator<Tesoros> i;
	   String s;
	   
	   String opc = JOptionPane.showInputDialog(null,"Ingrese una de las siguientes opciones: \n1.Todos los tesoros\n2.Mis tesoros publicados\n3.Mis tesoros encontrados");
	   switch(opc) {
	   case "1":
		   String impr;
		   impr =  target.path("v1").path("usuarios").path("tesoros").request().accept(MediaType.APPLICATION_XML).get(String.class);
		   JOptionPane.showMessageDialog(null,impr);
		   
		   break;
		   
	   case "2":
		   		fecha = JOptionPane.showInputDialog(null,"Ingresa la fecha limite de la busqueda en el siguiente formato yyyy-mm-dd: ");
		   		pag = JOptionPane.showInputDialog(null,"Ingresa el tesoro a partir del cual se iniciara la busqueda: ");
		   		lim = JOptionPane.showInputDialog(null,"Ingresa el tesoro a partir del cual se terminara la busqueda: ");
		   		dif = JOptionPane.showInputDialog(null,"Ingresa la dificulta del tesoro: ");
		   		tam = JOptionPane.showInputDialog(null,"Ingresa el tamanio del tesoro: ");
		   		terreno = JOptionPane.showInputDialog(null,"Ingresa el terreno del tesoro: ");
		   		
		   		respuesta = target.path("v1").path("usuarios").path(user.getId()).path("tesoros_publicados").queryParam("fecha_lim", fecha).queryParam("pag", pag).queryParam("lim", lim).queryParam("dif", dif).queryParam("tam", tam).queryParam("terreno", terreno).request().accept(MediaType.APPLICATION_XML).get();
		 	   
		 	   if(respuesta.getStatus() != 200) {
		 		   JOptionPane.showMessageDialog(null,"No es posible consultar a este tesoro "+respuesta.getStatus()+" "+respuesta.getEntity());
		 		   return;
		 	   }
		    	
		 	  salida =  target.path("v1").path("usuarios").path(user.getId()).path("tesoros_publicados").queryParam("fecha_lim", fecha).queryParam("pag", pag).queryParam("lim", lim).queryParam("dif", dif).queryParam("tam", tam).queryParam("terreno", terreno).request().accept(MediaType.APPLICATION_XML).get(TesorosList.class);
		   	
		 	 i  = salida.getL().iterator();
			    
			    s = "";
			    while (i.hasNext()) {
			    	tesoroAx = i.next(); 
			    	s = s + "Id: "+tesoroAx.getId()+", fecha: "+tesoroAx.getFecha_post()+", tamaño: "+tesoroAx.getTam()+", terreno: "+tesoroAx.getTipo_terreno() + "\n";
			    }
			    
			    JOptionPane.showMessageDialog(null,s);
		 	  
		   break;
	   
	   case "3":
		   fecha = JOptionPane.showInputDialog(null,"Ingresa la fecha limite de la busqueda en el siguiente formato yyyy-mm-dd: ");
	   		pag = JOptionPane.showInputDialog(null,"Ingresa el tesoro a partir del cual se iniciara la busqueda: ");
	   		lim = JOptionPane.showInputDialog(null,"Ingresa el tesoro a partir del cual se terminara la busqueda: ");
	   		dif = JOptionPane.showInputDialog(null,"Ingresa la dificulta del tesoro: ");
	   		tam = JOptionPane.showInputDialog(null,"Ingresa el tamanio del tesoro: ");
	   		terreno = JOptionPane.showInputDialog(null,"Ingresa el terreno del tesoro: ");
	   		
	   		respuesta = target.path("v1").path("usuarios").path(user.getId()).path("tesoros_encontrados").queryParam("fecha_lim", fecha).queryParam("pag", pag).queryParam("lim", lim).queryParam("dif", dif).queryParam("tam", tam).queryParam("terreno", terreno).request().accept(MediaType.APPLICATION_XML).get();
	 	   
	 	   if(respuesta.getStatus() != 200) {
	 		   JOptionPane.showMessageDialog(null,"No es posible consultar a este tesoro "+respuesta.getStatus()+" "+respuesta.getEntity());
	 		   return;
	 	   }
	    	
	 	  salida = target.path("v1").path("usuarios").path(user.getId()).path("tesoros_encontrados").queryParam("fecha_lim", fecha).queryParam("pag", pag).queryParam("lim", lim).queryParam("dif", dif).queryParam("tam", tam).queryParam("terreno", terreno).request().accept(MediaType.APPLICATION_XML).get(TesorosList.class);
	   	
	 	  i  = salida.getL().iterator();
		    
		  s = "";
		    while (i.hasNext()) {
		    	tesoroAx = i.next(); 
		    	s = s + "Id: "+tesoroAx.getId()+", fecha: "+tesoroAx.getFecha_post()+", tamaño: "+tesoroAx.getTam()+", terreno: "+tesoroAx.getTipo_terreno() + "\n";
		    }
		    
		    JOptionPane.showMessageDialog(null,s);
		   break;
		   
		default: JOptionPane.showMessageDialog(null,"Opcion no valida");
	   }
   }
   
   public void actualizarUser() {
	   String info = "";
	   Usuarios user_temp = target.path("v1").path("usuarios").path(user.getId()).request().accept(MediaType.APPLICATION_XML).get(Usuarios.class);
   	
   	if(user_temp != null){
   		info = info + "El id es: " +user_temp.getId()+", el nombre de usuario es: "+user_temp.getUsuario()
   		+"\nSu nombre completo es: "+user_temp.getNombre_completo()+", su correo es: "+user_temp.getCorreo()+
   		"\nSu edad es: "+user_temp.getEdad()+" y su localidad es: "+user_temp.getLocalidad() + "\n\nSe le pediran todos los campos nuevamente, si quiere dejarlos con el valor actual, solo no escriba nada";
   	
   		JOptionPane.showMessageDialog(null,info);
   		
   		user_temp.setId(JOptionPane.showInputDialog(null,"Ingresa el id del usuario: "));
   		user_temp.setNombre_completo(JOptionPane.showInputDialog(null,"Ingresa el nombre completo del usuario: "));
   		user_temp.setCorreo(JOptionPane.showInputDialog(null,"Ingresa el correo del usuario: "));
   		user_temp.setEdad(Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa la edad del usuario: ")));
   		user_temp.setLocalidad(JOptionPane.showInputDialog(null,"Ingresa la localidad del usuario: "));
   		
   		Response respu = target.path("v1").path("usuarios").path(user.getId()).request().accept(MediaType.APPLICATION_XML).put(Entity.xml(user_temp),Response.class);;
   		
   		if(respu.getStatus() == 200) {
   			info = "El id es: " +user_temp.getId()+", el nombre de usuario es: "+user_temp.getUsuario()
   	   		+"\nSu nombre completo es: "+user_temp.getNombre_completo()+", su correo es: "+user_temp.getCorreo()+
   	   		"\nSu edad es: "+user_temp.getEdad()+" y su localidad es: "+user_temp.getLocalidad() + "\n\nSe le pediran todos los campos nuevamente, si quiere dejarlos con el valor actual, solo no escriba nada";
   			
   			JOptionPane.showMessageDialog(null,info);
   			
   		} else if(respu.getStatus() == 404) {
   			JOptionPane.showMessageDialog(null,"El usuario no se encontro");
   		}
   		
   	}
   	else JOptionPane.showMessageDialog(null,"El usuario no fue encontrado");
   }
   
   public void eliminarCuenta() {
	   Response respu = target.path("v1").path("usuarios").path(user.getId()).request().delete();
	  
	   if(respu.getStatus() == 200) {
		   JOptionPane.showMessageDialog(null,"Ha eliminado su cuenta, inicie sesion nuevamente con una cuenta distinta o cree un nuevo usuario");
	   }else if(respu.getStatus() == 404) {
  			JOptionPane.showMessageDialog(null,"El usuario no se encontro");
  		}
   }
   
   public void actualizarTesoro() {
	   String info = "";
	   
	   String id_tesoro = JOptionPane.showInputDialog(null,"Ingresa el id del tesoro que desea eliminar");
	   
	   Tesoros tesoro_temp = target.path("v1").path("usuarios").path("tesoros").queryParam("id_tesoro", id_tesoro).request().accept(MediaType.APPLICATION_XML).get(Tesoros.class);
   	
   	if(tesoro_temp != null){
   		info = info + "El id es: " +tesoro_temp.getId()+", el nombre de usuario que lo publico es: "+ tesoro_temp.getId_user()
   		+ "\nLa pista es: "+ tesoro_temp.getPista() + ", las coordenadas en x, y son: ("+tesoro_temp.getCoor_x()+","+tesoro_temp.getCoor_y()+"), "+
   		"\nSu estado es: "+tesoro_temp.getEstado()+", su dificultad es: "+tesoro_temp.getDificultad()+", su tamaño es:"+tesoro_temp.getTam()+" y el tipo de terreno es: "+tesoro_temp.getTipo_terreno()
   		+ "\n\nSe le pediran todos los campos nuevamente, si quiere dejarlos con el valor actual, solo no escriba nada";
   		
   		JOptionPane.showMessageDialog(null,info);
   		
   		tesoro_temp.setFecha_update("2022-11-11");
   		
   		tesoro_temp.setPista(JOptionPane.showInputDialog(null,"Ingresa la pista del tesoro: "));
   		tesoro_temp.setCoor_x(Float.parseFloat(JOptionPane.showInputDialog(null,"Ingresa la coordenada en x del tesoro: ")));
   		tesoro_temp.setCoor_y(Float.parseFloat(JOptionPane.showInputDialog(null,"Ingresa la coordenada en y del tesoro: ")));
   		tesoro_temp.setEstado(JOptionPane.showInputDialog(null,"Ingresa el estado del tesoro: "));
   		tesoro_temp.setDificultad(JOptionPane.showInputDialog(null,"Ingresa la dificultad del tesoro: "));
   		tesoro_temp.setTipo_terreno(JOptionPane.showInputDialog(null,"Ingresa el tipo de terreno del tesoro: "));
   		tesoro_temp.setTam(Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa el tamaño del tesoro: ")));
   		
   		Response respu = target.path("v1").path("usuarios").path("tesoros").path(id_tesoro).request().accept(MediaType.APPLICATION_XML).put(Entity.xml(tesoro_temp),Response.class);
   		
   		if(respu.getStatus() == 200) {
   			
   			info = "El id es: " +tesoro_temp.getId()+", el nombre de usuario que lo publico es: "+ tesoro_temp.getId_user()
   	   		+ "\nLa pista es: "+ tesoro_temp.getPista() + ", las coordenadas en x, y son: ("+tesoro_temp.getCoor_x()+","+tesoro_temp.getCoor_y()+"), "+
   	   		"\nSu estado es: "+tesoro_temp.getEstado()+", su dificultad es: "+tesoro_temp.getDificultad()+", su tamaño es:"+tesoro_temp.getTam()+" y el tipo de terreno es: "+tesoro_temp.getTipo_terreno();
   			
   			JOptionPane.showMessageDialog(null,info);
   			
   		} else if(respu.getStatus() == 404) {
   			JOptionPane.showMessageDialog(null,"El Tesoro no se encontro");
   		}
   		
   	}
   	else JOptionPane.showMessageDialog(null,"El tesoro no fue encontrado");
   }
   
   public void agregarTesoroEncontrado() {
	   String id_tesoro = JOptionPane.showInputDialog(null,"Ingresa el id del tesoro que deseas buscar");
	   String s = "";
	   
	   Tesoros tesoro_temp = target.path("v1").path("usuarios").path(user.getId()).path("tesoros_encontrados").path(id_tesoro).request().accept(MediaType.APPLICATION_XML).post(null,Tesoros.class);
 	   
	   
 	   
 	   if(tesoro_temp != null) {
 		   s = "El tesoro tiene la siguiente informacion:\n"  + "El id es: " +tesoro_temp.getId()+", el nombre de usuario que lo publico es: "+ tesoro_temp.getId_user()
 	   		+ "\nLa pista es: "+ tesoro_temp.getPista() + ", las coordenadas en x, y son: ("+tesoro_temp.getCoor_x()+","+tesoro_temp.getCoor_y()+"), "+
 	   		"\nSu estado es: "+tesoro_temp.getEstado()+", su dificultad es: "+tesoro_temp.getDificultad()+", su tamaño es:"+tesoro_temp.getTam()+" y el tipo de terreno es: "+tesoro_temp.getTipo_terreno()+
 	   		"\n\nLO HAS ENCONTRADO! Se agregara a tu historial de tesoros encontrados, felicidades!";
 		   
 		  JOptionPane.showMessageDialog(null,s);
 	   }
 	   else{
		   JOptionPane.showMessageDialog(null,"No ha sido posible agregar el tesoro ya que no se encontro");
		   areaTexto.append("No ha sido posible agregar el tesoro ya que no se encontro");
	   }
   }
   
}