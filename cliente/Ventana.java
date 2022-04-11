/* Librerias para GUI */
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    
    JButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12,button13,button14;
    
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
    	        	System.out.println(btn1.getText());
    	        }
    	        if(btn1.getText() == "Actualizar Informacion"){
    	        	System.out.println(btn1.getText());     
    	        }
    	        if(btn1.getText() == "Eliminar Cuenta"){
    	        	System.out.println(btn1.getText());
    	        }
    	        if(btn1.getText() == "Listado de Usuarios"){                                        
    	        	listaUsuarios();
    	        }
    	        if(btn1.getText() == "Agregar/Editar Tesoro"){
    	        	System.out.println(btn1.getText());
    	        }
    	        if(btn1.getText() == "Eliminar Tesoro"){
    	        	System.out.println(btn1.getText());
    	        }
    	        if(btn1.getText() == "Consultar Tesoros"){
    	        	System.out.println(btn1.getText());
    	        }
    	        if(btn1.getText() == "Buscar Tesoro!"){
    	        	System.out.println(btn1.getText());
    	        }
    	        if(btn1.getText() == "Agregar Amigos"){
    	        	System.out.println(btn1.getText());
    	        }
    	        if(btn1.getText() == "Eliminar Amigo"){
    	        	System.out.println(btn1.getText());
    	        }
    	        if(btn1.getText() == "Consultar Amigo"){
    	        	System.out.println(btn1.getText());
    	        }
    	        if(btn1.getText() == "Tesoros Cercanos a ..."){
    	        	System.out.println(btn1.getText());
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
}