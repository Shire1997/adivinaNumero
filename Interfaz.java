//proyecto v2.0

import java.awt.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Interfaz extends JFrame {
    private JPanel contentPane;
    private JTextField numeroTF=new JTextField();
    //private JTextArea respuesta=new JTextArea();
    private JLabel instruccion=new JLabel("Adivina el numero del 1-100");
   private JLabel respuesta=new JLabel("Mucha suerte!!! :D");
    private JButton btn=new JButton("Adivinar");
    private JButton reinicio=new JButton("Reiniciar");
    private JLabel vidas=new JLabel("Vidas: <3 <3 <3 <3 <3");

    public Interfaz(){
        //iniciamos el panel
        contentPane=(JPanel) getContentPane();
        contentPane.setLayout(null);
        
        //agregamos el textfield del ingreso de datos y configuramos
        contentPane.add(numeroTF);
        numeroTF.setBounds(10,30,80,20);
        
        //se agrega y configura el botón para evaluar la respuesta
        contentPane.add(btn);
        btn.setBounds(100,30,80,20);
        
        //Se agrega y configura el texto de las instrucciones
        contentPane.add(instruccion);
        instruccion.setBounds(10,0,180,25);
        
        //Se agrega y configura el texto de las pistas y mensajes
        contentPane.add(respuesta);
        respuesta.setBounds(60,80,200,30);
        
        //Se agrega y configura el botón de reinicio
        contentPane.add(reinicio);
        reinicio.setBounds(185,30,90,20);
        
        //Se agrega y configuran las vidas del jugador
        contentPane.add(vidas);
        vidas.setBounds(10,50,120,25);

        //ActionListener del botón reiniciar
        reinicio.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    btn.setEnabled(true);
                    num=(int)(Math.random()*100)+1;
                    System.out.println("numero="+num);
                    contentPane.setBackground(Color.WHITE);
                    respuesta.setText("");
                    intento=0;
                    vidas.setText("Vidas: <3 <3 <3 <3 <3");
                }
            }
        );
        
      
            
        //ActionListener del botón que evalua la respuesta
        btn.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    Evaluar(numeroTF.getText());
                }  
            }
            
        );

        numeroTF.addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent event){
                if(!Character.isDigit(event.getKeyChar())){
                    event.consume();
                    instruccion.setText("Solo ingresa numeros del 1 al 100");
                }

                /* 
                && Integer.valueOf(numeroTF.getText())>=1 
                && Integer.valueOf(numeroTF.getText())<=100){*/
            }

            public void keyReleased(KeyEvent e){

            }
            public void keyPressed(KeyEvent e){

            }
        });

        initPantalla();
    }

    public void Evaluar(String valor){
        int numero=Integer.valueOf(valor);
        if(numero==num){
            respuesta.setText("HAZ ACERTADO!!!! :D");
            btn.setEnabled(false);
            contentPane.setBackground(Color.ORANGE);
            }else if((Math.abs(numero-num)>=50) && intento<5){
                respuesta.setText(numero+" muy frio");
                contentPane.setBackground(Color.BLUE);
                Vidas();
            }else if(Math.abs(numero-num)<50 && Math.abs(numero-num)>=40 && intento<5){
                respuesta.setText(numero+" frio");
                contentPane.setBackground(Color.CYAN);
                Vidas();
            }else if(Math.abs(numero-num)<40 && Math.abs(numero-num)>=30 && intento<5){
                respuesta.setText(numero+" estas tibio");
                contentPane.setBackground(Color.GREEN);
                Vidas();
            }else if(Math.abs(numero-num)<30 && Math.abs(numero-num)>=20 && intento<5){
                respuesta.setText(numero+", caliente");
                contentPane.setBackground(Color.YELLOW);
                Vidas();
            }else if(Math.abs(numero-num)<20 && intento<5){
                respuesta.setText(numero+", muy caliente");
                contentPane.setBackground(Color.RED);
                Vidas();
            }else if(numero!=num && intento<5){
                respuesta.setText("intentalo de nuevo");
                Vidas();
            }
    }

    public void Vidas(){
                    intento++;
                    if(intento==1){
                        vidas.setText("Vidas: <3 <3 <3 <3");
                    }else if(intento==2){
                        vidas.setText("Vidas: <3 <3 <3");
                    }else if(intento==3){
                        vidas.setText("Vidas: <3 <3");
                    }else if(intento==4){
                        vidas.setText("Vidas: <3");
                    }else if(intento==5){
                        vidas.setText("Vidas:");
                        respuesta.setText("superaste los 5 intentos :(");
                        btn.setEnabled(false);
                    }
                }

    private void initPantalla(){
        setTitle("Adivina el numero");
        setBounds(300,300,250,220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(300, 200);
        System.out.println("numero="+num);
    }

    public Random rnd=new Random();
   public int num=(int)(Math.random()*100)+1, intento=0;
    public static void main(String[] args){
        new Interfaz();
        
    }
    
}
