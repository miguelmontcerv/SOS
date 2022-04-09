import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import java.util.Scanner;

public class Ventana extends JFrame{
    
    //private Scanner in = new Scanner(System.in);
    JPanel panel1, panel2, panel3, panel4;
    
    JButton button1, button2, button3, buttonSALIR;
    
    JTextArea areaTexto, areaTexto2;
    
    JLabel label1, label2, label3, label4, label5;
    
    public Ventana(){
        setSize(1240,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("PROYECTO SOS");
        
        panel1 = new JPanel(); //Izquierda
        panel2 = new JPanel(); //Central
        panel3 = new JPanel(); //Derecha
        panel4 = new JPanel(); //Superior
        
        iniciarComponentes();
    }
    
    public void iniciarComponentes(){

        button1 = new JButton("Agregar un nuevo usuario");
        button1.setFocusPainted(false);
        button1.setMargin(new Insets(0, 0, 0, 0));        
        button1.setBorderPainted(false);
        button1.setOpaque(true);
        button1.setBackground(new Color(29,35,57));
        button1.setForeground(Color.white);
        
        button2 = new JButton("Agregar un nuevo tesoro");
        button2.setFocusPainted(false);
        button2.setMargin(new Insets(0, 0, 0, 0));        
        button2.setBorderPainted(false);
        button2.setOpaque(true);
        button2.setBackground(new Color(29,35,57));
        button2.setForeground(Color.white);
        
        button3 = new JButton("Editar la info de un usuario");
        button3.setFocusPainted(false);
        button3.setMargin(new Insets(0, 0, 0, 0));        
        button3.setBorderPainted(false);
        button3.setOpaque(true);
        button3.setBackground(new Color(29,35,57));
        button3.setForeground(Color.white);
        
        buttonSALIR = new JButton("Salir");
        buttonSALIR.setFocusPainted(false);
        buttonSALIR.setMargin(new Insets(0, 0, 0, 0));        
        buttonSALIR.setBorderPainted(false);
        buttonSALIR.setOpaque(true);
        buttonSALIR.setBackground(new Color(29,35,57));
        buttonSALIR.setForeground(Color.white);
        
      //Areas para registros de historial

        JTextArea areaTexto = new JTextArea();
        areaTexto.setBounds(2,2,319, 659);
        areaTexto.setText(">_");
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Roboto",Font.PLAIN,15));
        areaTexto.setBackground(new Color(168,218,220));
        areaTexto.setForeground(new Color(29,35,57));
        
        JTextArea areaTexto2 = new JTextArea();
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
        
        button1.setBounds(0,60,260,50); //Posicionx, posiciony, tamaño,tamaño        
        button2.setBounds(0,110,260,50); //Posicionx, posiciony, tamaño,tamaño                
        button3.setBounds(0,160,260,50); //Posicionx, posiciony, tamaño,tamaño

        buttonSALIR.setBounds(0,500,260,50); //Posicionx, posiciony, tamaño,tamaño
        
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

        panel1.add(buttonSALIR); 
        
        panel2.add(areaTexto);        
        panel3.add(areaTexto2);


        this.getContentPane().add(panel1);
        this.getContentPane().add(panel2);
        this.getContentPane().add(panel3);
        this.getContentPane().add(panel4);
    }

}