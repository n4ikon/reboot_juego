package principal.graficos;

import principal.Constantes;
import principal.GestorPrincipal;
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

    public SuperficieDeDibujo(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto = alto;


        this.raton = new Raton(this);


        setIgnoreRepaint(true);
        setCursor(raton.obtenerCursor());
        setPreferredSize(new Dimension(ancho, alto));
        addKeyListener(GestorControles.teclado);
        setFocusable(true);
        requestFocus();

    }

    public void actualizar() {
        raton.actualizar(this);
    }

    public void dibujar(final GestorDeEstado ge) {
        BufferStrategy buffer = getBufferStrategy();
        if (buffer == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
        g.setFont(Constantes.FUENTE_PIXEL);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constantes.ANCHO_PANTALLA, Constantes.ALTO_PANTALLA);

        if (Constantes.FACTOR_ESCALADO_X != 1.0 || Constantes.FACTOR_ESCALADO_Y != 1.0) {
            g.scale(Constantes.FACTOR_ESCALADO_X, Constantes.FACTOR_ESCALADO_Y);
        }

        ge.dibujar(g);


        g.drawString("FPS : " + GestorPrincipal.obtenerFps(), 20, 100);
        g.drawString("APS : " + GestorPrincipal.obtenerAps(), 20, 110);

        raton.dibujar(g);

        Toolkit.getDefaultToolkit().sync();


        g.dispose();

        buffer.show();
    }

    public int obtenerAncho() {
        return ancho;
    }

    public int obtenerAlto() {
        return alto;
    }
}
