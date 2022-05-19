package principal.control;

import principal.herramientas.CargadorRecuros;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Raton {
    private final Cursor cursor ;

    public Raton(){
        Toolkit configuracion = Toolkit.getDefaultToolkit();

        BufferedImage icono = CargadorRecuros.cargarImagenCompatibleTranslucida("recursos/imagenes/iconos/raton.png");

        Point punta = new Point(16,16);

        this.cursor = configuracion.createCustomCursor(icono , punta ,"cursor por defecto");

    }

    public Cursor obtenerCursor() {
        return this.cursor;
    }
}
