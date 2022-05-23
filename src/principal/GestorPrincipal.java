package principal;

import principal.control.GestorControles;
import principal.graficos.SuperficieDeDibujo;
import principal.graficos.Ventana;
import principal.maquinaDeEstado.GestorDeEstado;

public class GestorPrincipal {
    private boolean enFuncionamiento = false;
    private String titulo;
    private int ancho;
    private int alto;

    private SuperficieDeDibujo sd;
    private Ventana ventana;
    private GestorDeEstado ge;

    private static int fps = 0;
    private static int aps = 0;


    private GestorPrincipal(final String titulo, final int ancho, final int alto) {
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;

    }

    public static void main(String[] args) {
        GestorPrincipal gp = new GestorPrincipal("Apocalipsis T.U.P.", Constantes.ANCHO_PANTALLA, Constantes.ALTO_PANTALLA);


        gp.iniciarJuego();
        gp.iniciarBuclePrincipal();

    }

    private void iniciarJuego() {
        enFuncionamiento = true;
        inicializar();
    }

    private void inicializar() {
        sd = new SuperficieDeDibujo(ancho, alto);
        ventana = new Ventana(titulo, sd);
        ge = new GestorDeEstado(sd);

    }


    private void iniciarBuclePrincipal() {
        int actualizacionesAcumuladas = 0;
        int framesAcumulados = 0;
        // equivalencia ns por s
        final int NS_POR_SEGUNDO = 1000000000;
        //actualizaciones por segundo
        final int APS_OBJETIVO = 60;
        //cuantos  ns transcurre por actualizacion
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

        long referenciaActualizacion = System.nanoTime();

        long referenciaContador = System.nanoTime();

        double tiempoTranscurrido;
        // cantidad de tiempo que transcurre hasta la actualizacion
        double delta = 0;


        while (enFuncionamiento) {
            //reinica el tiempo
            final long inicioBucle = System.nanoTime();
            //mide el tiempo que transcurrio entre las variables
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            //se le da el valor del tiempo de cuando inicio el bucle
            referenciaActualizacion = inicioBucle;
            //almacena la cantidad de tiempo transcurrido
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
            // cada vez que delta llegue a 1+ se actualiza
            while (delta >= 1) {
                actualizar();
                actualizacionesAcumuladas++;

                delta--;
            }
            dibujar();
            framesAcumulados++;
            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {


                aps = actualizacionesAcumuladas;
                fps = framesAcumulados;

                actualizacionesAcumuladas = 0;
                framesAcumulados = 0;
                referenciaContador = System.nanoTime();

            }
        }
    }

    private void actualizar() {
        if (GestorControles.teclado.inventarioActivo) {
            ge.cambiarEstadActual(1);
        } else {
            ge.cambiarEstadActual(0);
        }
        ge.actualizar();
        sd.actualizar();

    }

    private void dibujar() {
        sd.dibujar(ge);

    }

    public static int obtenerAps() {
        return aps;
    }

    public static int obtenerFps() {
        return fps;
    }


}
