package principal.mapas;

import principal.Constantes;
import principal.herramientas.CargadorRecuros;
import principal.sprites.HojaSprites;
import principal.sprites.Sprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mapa {
    private String[] partes;

    private final int ancho;
    private final int alto;

    private final Point posicionInicial;

    private final Point puntoSalida;

    private Rectangle zonaSalida;

    private String siguienteMapa;

    private final Sprites[] paleta;
    private final boolean[] colisiones;

    public ArrayList<Rectangle> areasColision = new ArrayList<Rectangle>();

    private final int[] sprite;

    private final int MARGEN_X = Constantes.ANCHO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
    private final int MARGEN_Y = Constantes.ALTO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;

    public Mapa(final String ruta) {
        String contenido = CargadorRecuros.leerArchivoTexto(ruta);
        partes = contenido.split("\\*");
        ancho = Integer.parseInt(partes[0]);
        alto = Integer.parseInt(partes[1]);

        String hojasUtilizadas = partes[2];
        String[] hojasSeparadas = hojasUtilizadas.split(",");

        String paletaEntera = partes[3];
        String[] partesPaleta = paletaEntera.split("#");

        paleta = asignarSprite(partesPaleta, hojasSeparadas);


        String colisionesEnteras = partes[4];
        colisiones = extraerColisiones(colisionesEnteras);


        String spritesEnteros = partes[5];
        String[] cadenasSprites = spritesEnteros.split(" ");

        sprite = extraerSprite(cadenasSprites);

        String posicion = partes[6];
        String[] posiciones = posicion.split("-");

        posicionInicial = new Point();
        posicionInicial.x = Integer.parseInt(posiciones[0]) * Constantes.LADO_SPRITE;
        posicionInicial.y = Integer.parseInt(posiciones[1]) * Constantes.LADO_SPRITE;

        String salidas = partes[7];
        String[] datosSalida = salidas.split("-");
        puntoSalida = new Point();
        puntoSalida.x = Integer.parseInt(datosSalida[0]);
        puntoSalida.y = Integer.parseInt(datosSalida[1]);
        siguienteMapa = datosSalida[2];

        zonaSalida = new Rectangle();


    }

    private Sprites[] asignarSprite(final String[] partesPaleta, final String[] hojasSparadas) {
        Sprites[] paleta = new Sprites[partesPaleta.length];
        HojaSprites hoja = new HojaSprites("recursos/imagenes/hojasSprite/" + hojasSparadas[0] + ".png", 32, false);
        for (int i = 0; i < partesPaleta.length; i++) {
            String spriteTemporal = partesPaleta[i];
            String[] partesSprite = spriteTemporal.split("-");

            int indicePaleta = Integer.parseInt(partesSprite[0]);
            int indiceSpriteHoja = Integer.parseInt(partesSprite[2]);

            paleta[indicePaleta] = hoja.obtenerSprite(indiceSpriteHoja);

        }


        return paleta;

    }


    private boolean[] extraerColisiones(final String cadenaColisiones) {

        boolean[] colisiones = new boolean[cadenaColisiones.length()];
        for (int i = 0; i < cadenaColisiones.length(); i++) {
            if (cadenaColisiones.charAt(i) == '0') {
                colisiones[i] = false;
            } else {
                colisiones[i] = true;
            }

        }
        return colisiones;
    }

    private int[] extraerSprite(final String[] cadenasSprites) {
        ArrayList<Integer> sprite = new ArrayList<Integer>();

        for (int i = 0; i < cadenasSprites.length; i++) {
            if (cadenasSprites[i].length() == 2) {
                sprite.add(Integer.parseInt(cadenasSprites[i]));
            } else {
                String uno = "";
                String dos = "";

                String error = cadenasSprites[i];

                uno += error.charAt(0);
                uno += error.charAt(1);

                dos += error.charAt(2);
                dos += error.charAt(3);

                sprite.add(Integer.parseInt(uno));
                sprite.add(Integer.parseInt(dos));
            }
        }
        int[] vectorSprites = new int[sprite.size()];
        for (int i = 0; i < sprite.size(); i++) {
            vectorSprites[i] = sprite.get(i);
        }

        return vectorSprites;

    }

    public void actualizar(final int posicionX, final int posiciionY) {
        actualizarAreasColisicion(posicionX, posiciionY);
        actualizarZonaSalida(posicionX, posiciionY);

    }

    private void actualizarAreasColisicion(final int posicionX, final int posicionY) {
        if (!areasColision.isEmpty()) {
            areasColision.clear();
        }
        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {

                int puntoX = x * Constantes.LADO_SPRITE - posicionX + MARGEN_X;
                int puntoY = y * Constantes.LADO_SPRITE - posicionY + MARGEN_Y;

                if (colisiones[x + y * this.ancho]) {
                    final Rectangle r = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
                    areasColision.add(r);
                }

            }
        }

    }

    private void actualizarZonaSalida(final int posicionX, final int posicionY) {

        int puntoX = ((int) puntoSalida.getX()) * Constantes.LADO_SPRITE - posicionX + MARGEN_X;
        int puntoY = ((int) puntoSalida.getY()) * Constantes.LADO_SPRITE - posicionY + MARGEN_Y;

        zonaSalida = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

    }

    public void dibujar(Graphics g, int posicionX, int posicionY) {


        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {
                BufferedImage imagen = paleta[sprite[x + y * this.ancho]].obtenerImagen();

                int puntoX = x * Constantes.LADO_SPRITE - posicionX + MARGEN_X;
                int puntoY = y * Constantes.LADO_SPRITE - posicionY + MARGEN_Y;

                g.drawImage(imagen, puntoX, puntoY, null);

            }

        }

    }

    public Rectangle obtenerBordes(final int posicionX, final int posicionY, final int anchoJugador, final int altoJugador) {
        int x = MARGEN_X - posicionX + anchoJugador;
        int y = MARGEN_Y - posicionY + altoJugador;
        int ancho = this.ancho * Constantes.LADO_SPRITE - anchoJugador * 2;
        int alto = this.alto * Constantes.LADO_SPRITE - altoJugador * 2;

        return new Rectangle(x, y, ancho, alto);

    }

    public Point obtenerPosicionInicial() {
        return posicionInicial;
    }

    public Point obtenerPuntoSalida() {
        return puntoSalida;
    }

    public String obtenerSiguienteMapa() {
        return siguienteMapa;
    }

    public Rectangle obtenerZonaSalida() {
        return zonaSalida;
    }
}
