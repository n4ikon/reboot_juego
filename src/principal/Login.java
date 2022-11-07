package principal;

import conexion.Conversion;
import conexion.HttpHelper;
import principal.maquinaDeEstado.EstadoJuego;
import principal.maquinaDeEstado.GestorDeEstado;
import principal.maquinaDeEstado.estados.menuDeJuego.MenuInventario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static conexion.HttpHelper.getRequest;

public class Login extends  JFrame implements EstadoJuego {
    private JPanel loginPanel;
    private JLabel nombre;
    private JTextField campoNombre;
    private JButton button1;
    private JLabel creado;
    private JPasswordField passwordField1;
    public static String nombreLogin ;
    public static String passwordLogin;

    public Login() {


        setSize(400,300);
        setContentPane(loginPanel);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);


        button1.addActionListener(new ActionListener() {



            public void actionPerformed(ActionEvent e) {

                setNombreLogin(campoNombre);
                setPasswordLogin(passwordField1);
                getRequest();


                dispose();



            }


        });

    }
    public void setNombreLogin(JTextField campoNombre){
        nombreLogin = campoNombre.getText();

    }
    public void setPasswordLogin(JPasswordField passwordField1){
        passwordLogin = String.valueOf(passwordField1.getPassword());

    }

    @Override
    public void actualizar() {

    }

    @Override
    public void dibujar(Graphics g) {

    }
}















