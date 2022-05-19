package principal.sprites;

import java.awt.image.BufferedImage;

public class Sprites {
    private final BufferedImage imagen;

    private int ancho;
    private int alto;

    public Sprites(final BufferedImage imagen){
        this.imagen = imagen;

        ancho = imagen.getWidth();
        alto = imagen.getHeight();

    }

    public BufferedImage obtenerImagen() {
        return imagen;
    }

    public int obtenerAncho() {
        return ancho;
    }

    public int obteneralto() {
        return alto;
    }
}
