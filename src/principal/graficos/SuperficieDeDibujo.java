package principal.graficos;

import principal.control.GestorControles;
import principal.control.Raton;
import principal.control.Teclado;
import principal.maquinaDeEstado.GestorDeEstado;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class SuperficieDeDibujo extends Canvas {

    private static final long serialVersionUID = -2303469561959410099L;

    private int ancho;
    private int alto;


    private Raton raton;

    public SuperficieDeDibujo(final int ancho, final int alto){
        this.ancho = ancho;
        this.alto = alto;


        this.raton = new Raton();


        setIgnoreRepaint(true);
        setCursor(raton.obtenerCursor());
        setPreferredSize(new Dimension(ancho,alto));
        addKeyListener(GestorControles.teclado);
        setFocusable(true);
        requestFocus();

    }
    public void dibujar (final GestorDeEstado ge){
        BufferStrategy buffer = getBufferStrategy();
        if (buffer== null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = buffer.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,ancho,alto);
        ge.dibujar(g);
        Toolkit.getDefaultToolkit().sync();

        g.dispose();

        buffer.show();
    }

    public int obtenerAncho(){
        return ancho;
    }
    public int obtenerAlto(){
        return alto;
    }
}
