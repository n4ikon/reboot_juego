package principal.control;

import principal.Constantes;
import principal.graficos.SuperficieDeDibujo;
import principal.herramientas.CargadorRecuros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Raton extends MouseAdapter {
    private final Cursor cursor;

    private Point posicion;

    private boolean click;

    public Raton(final SuperficieDeDibujo sd) {
        Toolkit configuracion = Toolkit.getDefaultToolkit();

        BufferedImage icono = CargadorRecuros.cargarImagenCompatibleTranslucida(Constantes.RUTA_RATON);

        Constantes.ladoCursor = icono.getWidth();

        Point punta = new Point(16, 16);

        this.cursor = configuracion.createCustomCursor(icono, punta, "cursor por defecto");

        posicion = new Point();
        actualizarPosicion(sd);
        click = false;

    }

    public void actualizar(final SuperficieDeDibujo sd) {
        actualizarPosicion(sd);
    }

    public void dibujar(Graphics g) {

    }

    public Cursor obtenerCursor() {
        return this.cursor;
    }

    private void actualizarPosicion(final SuperficieDeDibujo sd) {

        final Point posicionInicial = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(posicionInicial, sd);
        posicion.setLocation(posicionInicial.getX(), posicionInicial.getY());
    }

    public Point obtenerPuntoPosicion() {
        return posicion;
    }

    public Rectangle obtenerrectanguloPosicion() {
        final Rectangle area = new Rectangle(posicion.x, posicion.y, 1, 1);
        return area;
    }

    public void mouseClicked(MouseEvent e) {
        if (!click) {

            click = true;
        }


    }

    public boolean obtenerClick() {
        return click;
    }

    public void reiniciarClick() {
        if (click) {
            click = false;
        }
    }
}
