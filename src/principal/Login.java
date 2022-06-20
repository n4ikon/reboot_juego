package principal;

import conexion.HttpHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static principal.graficos.Ventana.icono;

public class Login extends  JFrame  {
    private JPanel loginPanel;
    private JLabel nombre;
    private JTextField campoNombre;
    private JButton button1;
    private JLabel creado;

    public Login() {


        setSize(400,300);
        setContentPane(loginPanel);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String nombre = campoNombre.getText();
                HttpHelper.Post_JSON(nombre);
                dispose();



            }

        });
    }
 public  JTextField getNombre(){
        return campoNombre;
}
 }



