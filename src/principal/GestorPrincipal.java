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
    


    private GestorPrincipal(final String titulo,final int ancho ,final int alto){
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto= alto;

    }
    public static void main(String[] args){
        GestorPrincipal gp = new GestorPrincipal("Apocalipsis T.U.P.", 800 ,600);

        Constantes.ANCHO_PANTALLA = 800;
        Constantes.ALTO_PANTALLA = 600;

        gp.iniciarJuego();
        gp.iniciarBuclePrincipal();

    }
    private void iniciarJuego() {
        enFuncionamiento= true;
        inicializar ();
    }

    private void inicializar() {
        sd = new SuperficieDeDibujo(ancho, alto);
        ventana = new Ventana(titulo,sd);
        ge = new GestorDeEstado();

    }


    private void iniciarBuclePrincipal() {
        int aps = 0;
        int fps = 0;
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
                aps++;
                Constantes.APS = aps;
                delta--;
            }
            dibujar();
            fps++;
            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
                System.out.println("FPS: "+fps + "APS: "+aps);
                aps = 0;
                Constantes.APS = aps;
                fps = 0;
                referenciaContador = System.nanoTime();

            }
        }
    }

    private void actualizar() {
        ge.actualizar();

    }

    private void dibujar() {
        sd.dibujar(ge);

    }


}
