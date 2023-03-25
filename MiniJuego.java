//version 4.0

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MiniJuego extends JFrame implements ActionListener {
    //Se declaran los componentes
    private JLabel titulo, ingresaNumeroLbl, resultado;
    private JTextField ingresaNumeroTF;
    private JButton adivinarBtn, reiniciarBtn;
    private int numeroDesconocido;
    private JPanel panel;
    private JLabel vidas;
    public int intento=0;

    public MiniJuego() {
        super("Juego de Adivinar Número");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Inicializar valores
        numeroDesconocido = (int) (Math.random() * 100) + 1;

        // Crear componentes
        titulo = new JLabel("Adivina el número entre 1 y 100");
        titulo.setHorizontalAlignment(JLabel.CENTER);

        ingresaNumeroLbl = new JLabel("Ingresa tu número:");
        ingresaNumeroTF = new JTextField(10);

        adivinarBtn = new JButton("Adivinar");
        adivinarBtn.addActionListener(this);

        //Se agrega y configuran las vidas del jugador
        vidas=new JLabel("Vidas: <3 <3 <3 <3 <3");
        vidas.setBounds(10,50,120,25);

        //se le asigna una etiqueta al boton Reiniciar y se le asigna un action listener
        reiniciarBtn = new JButton("Reiniciar");
        reiniciarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reiniciar juego
                numeroDesconocido = (int) (Math.random() * 100) + 1;
                resultado.setText("");
                ingresaNumeroTF.setText("");
                panel.setBackground(null);
                vidas.setText("Vidas: <3 <3 <3 <3 <3");
                intento=0;
                adivinarBtn.setEnabled(true);
                numeroDesconocido=(int)(Math.random()*100)+1;
                System.out.println("numero="+numeroDesconocido);
            }
        });

        resultado = new JLabel("");
        resultado.setHorizontalAlignment(JLabel.CENTER);

        panel = new JPanel(new BorderLayout());
        panel.add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new FlowLayout());
        formulario.add(ingresaNumeroLbl);
        formulario.add(ingresaNumeroTF);
        formulario.add(adivinarBtn);
        formulario.add(reiniciarBtn);
        formulario.add(vidas);
        panel.add(formulario, BorderLayout.CENTER);

        panel.add(resultado, BorderLayout.SOUTH);

        // Mostrar ventana
        add(panel);
        setVisible(true);

        System.out.println("numero="+numeroDesconocido);
    }

    //este metodo cambia el valor de las vidas dependiendo cuantos intentos se tienen
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
            
        }
    }

    //Se le asigna una accion al boton AdivinarBtn
    public void actionPerformed(ActionEvent e) {
        //Con el try justificamos que si se ingresa un caracter diferente a un numero se mostrará un error
        try {
            int numero = Integer.parseInt(ingresaNumeroTF.getText());
            int diferencia = Math.abs(numeroDesconocido - numero);
            if (numero == numeroDesconocido && intento<=4) { //si el numero es correcto y no se exceden los intentos saldrá un mensaje de que ganaste
                //y cambiará el color del panel a verde
                panel.setBackground(Color.GREEN);
                resultado.setText("LE ATINASTE!!! :D");
                adivinarBtn.setEnabled(false);
            } else if (diferencia >= 50 && intento<=4) { //si la diferencia del numero ingresado y el de adivinar es mayor a 50, se reduce un corazón en cada error y el panel es azul
                Vidas();
                panel.setBackground(Color.BLUE);
                resultado.setText("Estas congelandote");
            } else if (diferencia >= 40 && diferencia < 50 && intento<5) { //si la diferencia es entre 40 y 50, el panel será azul claro
                Vidas();
                panel.setBackground(Color.CYAN);
                resultado.setText("Estas frio");
            } else if (diferencia >= 30 && diferencia < 40 && intento<=4) {//si la diferencia es entre 30 y 40, el panel será amarillo
                Vidas();
                panel.setBackground(Color.YELLOW);
                resultado.setText("Estas tibio");
            } else if (diferencia > 20 && diferencia < 30 && intento<=4) {//si la diferencia es entre 20 y 30, el panel será naranja claro
                Vidas();
                panel.setBackground(new Color(255, 165, 0)); // naranja claro
                resultado.setText("Huele a carne asada");
            } else if (diferencia > 10 && diferencia <= 20 && intento<=4) {//si la diferencia es entre 10 y 20, el panel será naranja
                Vidas();
                panel.setBackground(Color.ORANGE);
                resultado.setText("Estas quemandote");
            } else if (diferencia >= 1 && diferencia <= 10 && intento<=4) {//si la diferencia es entre 1 y 10, el panel será rojo
                Vidas();
                panel.setBackground(Color.RED);
                resultado.setText("¿Quieres sandia, mi negro?");
            }else if(numero!=numeroDesconocido && intento==5){ //si pierdes todas las vidas se mostrará el mensaje "perdiste :("
                Vidas();
                resultado.setText("Perdiste :(");
                adivinarBtn.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panel, "Ingresa un número válido");
        }
    }

    public static void main(String[] args) {
        new MiniJuego();
    }
}
