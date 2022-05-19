package principal.control;

import principal.Constantes;
import principal.graficos.SuperficieDeDibujo;
import principal.herramientas.CargadorRecuros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

public class Raton extends MouseAdapter {
    private final Cursor cursor;

    private Point posicion;

    public Raton(final SuperficieDeDibujo sd) {
        Toolkit configuracion = Toolkit.getDefaultToolkit();

        BufferedImage icono = CargadorRecuros.cargarImagenCompatibleTranslucida(Constantes.RUTA_RATON);

        Point punta = new Point(16, 16);

        this.cursor = configuracion.createCustomCursor(icono, punta, "cursor por defecto");

        posicion = new Point();
        actualizarPosicion(sd);

    }

    public void actualizar(final SuperficieDeDibujo sd) {
        actualizarPosicion(sd);
    }

    public void dibujar(Graphics g) {
        g.drawString("rx : " + posicion.getX(), 10, 200);
        g.drawString("ry : " + posicion.getY(), 10, 220);
    }

    public Cursor obtenerCursor() {
        return this.cursor;
    }

    private void actualizarPosicion(final SuperficieDeDibujo sd) {

        final Point posicionInicial = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(posicionInicial, sd);
        posicion.setLocation(posicionInicial.getX(), posicionInicial.getY());
    }
}
