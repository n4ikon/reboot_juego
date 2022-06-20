package principal.graficos;

import principal.herramientas.CargadorRecuros;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Ventana extends JFrame {

    private static final long serialVersionUID = 5979421777239930009L;

    public static String titulo;
    public static ImageIcon icono = null;


    public Ventana(final String titulo, final SuperficieDeDibujo sd) {
        this.titulo = titulo;
        BufferedImage imagen = CargadorRecuros.cargarImagenCompatibleOpaca("recursos/imagenes/iconos/icono.png");
        this.icono = new ImageIcon(imagen);

        configurarVentana(sd);
    }

    private void configurarVentana(final SuperficieDeDibujo sd) {
        setTitle(titulo);
        setIconImage(icono.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        add(sd, BorderLayout.CENTER);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
