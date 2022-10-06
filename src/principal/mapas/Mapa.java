package principal.mapas;

import principal.Constantes;
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.herramientas.CargadorRecuros;
import principal.herramientas.DibujoDebug;

import principal.inventario.ContenedorObjetos;
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

    public final ArrayList<Rectangle> areasColision = new ArrayList<Rectangle>();
    public ArrayList<ContenedorObjetos> objetosMapa;


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

        if (partes.length > 8 ){System.out.println(partes.length);
            String informacionObjetos = partes [8];
        objetosMapa = asignarObjetos(informacionObjetos);
        }



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
   private ArrayList<ContenedorObjetos> asignarObjetos(final String informacionObjetos){
        final ArrayList<ContenedorObjetos> objetos = new ArrayList<ContenedorObjetos>();
        String[] contenedoresObjetos = informacionObjetos.split("#");
        for (String contenedorIndividual:contenedoresObjetos){
        final ArrayList<Integer> idObjetos = new ArrayList<Integer>();
            final ArrayList<Integer> cantidadObjetos = new ArrayList<Integer>();
            final String[] divisionInformacionObjetos = contenedorIndividual.split(":");
            final String[] coordenadas = divisionInformacionObjetos[0].split(",");

            final Point posicionContenedor = new Point(Integer.parseInt(coordenadas[0]),Integer.parseInt(coordenadas[1]));
            final String[] objetosCantidades = divisionInformacionObjetos[1].split("/");
            for ( String objetoActual : objetosCantidades ){
                final String[] datosObjetoActual = objetoActual.split("-");
                idObjetos.add(Integer.parseInt(datosObjetoActual[0]));
                cantidadObjetos.add(Integer.parseInt(datosObjetoActual[1]));
            }
            final int[] idObjetosArray = new int[idObjetos.size()];
            final int[] cantidadObjetosArray = new int[cantidadObjetos.size()];
            for (int i = 0; i < idObjetosArray.length; i++) {
                idObjetosArray[i] = idObjetos.get(i);
                cantidadObjetosArray[i] = cantidadObjetos.get(i);
            }
            final ContenedorObjetos contenedor = new ContenedorObjetos(posicionContenedor,idObjetosArray,cantidadObjetosArray);
            objetos.add(contenedor);
        }
        return objetos;
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

    public void actualizar() {
        actualizarAreasColisicion();
        actualizarZonaSalida();
        actualizarRecorgerObjeto();

    }

    private void actualizarAreasColisicion() {
        if (!areasColision.isEmpty()) {
            areasColision.clear();
        }
        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {

                int puntoX = x * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionXInt() + MARGEN_X;
                int puntoY = y * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionYInt() + MARGEN_Y;

                if (colisiones[x + y * this.ancho]) {
                    final Rectangle r = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
                    areasColision.add(r);
                }

            }
        }

    }

    private void actualizarZonaSalida() {

        int puntoX = ((int) puntoSalida.getX()) * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionXInt() + MARGEN_X;
        int puntoY = ((int) puntoSalida.getY()) * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionYInt() + MARGEN_Y;

        zonaSalida = new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

    }
    private void actualizarRecorgerObjeto(){
        if (partes.length > 8){
        if (!objetosMapa.isEmpty()){
            final Rectangle areaJugador = new Rectangle(ElementosPrincipales.jugador.obtenerPosicionXInt(),ElementosPrincipales.jugador.obtenerPosicionYInt(),Constantes.LADO_SPRITE,Constantes.LADO_SPRITE);
        for (int i = 0 ; i < objetosMapa.size();  i++ ){
            final ContenedorObjetos contenedor = objetosMapa.get(i);
            final Rectangle posicionContenedor = new Rectangle(contenedor.obtenerPosicion().x * Constantes.LADO_SPRITE,contenedor.obtenerPosicion().y* Constantes.LADO_SPRITE, Constantes.LADO_SPRITE,Constantes.LADO_SPRITE);
            if (areaJugador.intersects(posicionContenedor)&& GestorControles.teclado.recoger){ElementosPrincipales.inventario.recojerObjetos(contenedor);objetosMapa.remove(i);}
            }
          }
        }
    }


    public void dibujar(Graphics g) {


        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {
                BufferedImage imagen = paleta[sprite[x + y * this.ancho]].obtenerImagen();

                int puntoX = x * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionXInt() + MARGEN_X;
                int puntoY = y * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionYInt() + MARGEN_Y;

                DibujoDebug.dibujarImagen(g,imagen, puntoX, puntoY);

            }

        }if (partes.length > 8){
        if (!objetosMapa.isEmpty()){
            for (ContenedorObjetos contenedor : objetosMapa){
                final int puntoX = contenedor.obtenerPosicion().x*Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionXInt() + MARGEN_X;
                final int puntoY = contenedor.obtenerPosicion().y*Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionYInt() +MARGEN_Y;
                contenedor.dibujar(g,puntoX,puntoY);
            }
        }}

    }

    public Rectangle obtenerBordes(final int posicionX, final int posicionY) {
        int x = MARGEN_X - posicionX + ElementosPrincipales.jugador.obtenerAncho();
        int y = MARGEN_Y - posicionY + ElementosPrincipales.jugador.obtenerAlto();
        int ancho = this.ancho * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerAncho() * 2;
        int alto = this.alto * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerAlto() * 2;

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
