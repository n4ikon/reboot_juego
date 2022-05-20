package principal.maquinaDeEstado.estados.juego;

import principal.Constantes;
import principal.entes.Jugador;
import principal.interfa_usuario.MenuInferior;
import principal.mapas.Mapa;
import principal.maquinaDeEstado.EstadoJuego;

import java.awt.*;

public class GestorJuego implements EstadoJuego {


    Mapa mapa;

    Jugador jugador;
    MenuInferior menuInferior;

    public GestorJuego() {
        iniciarMapa(Constantes.RUTA_MAPA);
        iniciarJugador();
        menuInferior = new MenuInferior(jugador);

    }

    private void recargarJuego() {
        final String ruta = "recursos/textos/" + mapa.obtenerSiguienteMapa();
        iniciarMapa(ruta);
        iniciarJugador();

    }


    private void iniciarMapa(final String ruta) {
        mapa = new Mapa(ruta);
    }

    private void iniciarJugador() {
        jugador = new Jugador(mapa);
    }

    public void actualizar() {

        if (jugador.obtenerLimiteArriba().intersects(mapa.obtenerZonaSalida())) {
            recargarJuego();
        }

        jugador.actualizar();
        mapa.actualizar((int) jugador.obtenerPosicionX(), (int) jugador.obtenerPosicionY());

    }

    public void dibujar(Graphics g) {
        mapa.dibujar(g, (int) jugador.obtenerPosicionX(), (int) jugador.obtenerPosicionY());
        jugador.dibujar(g);
        menuInferior.dibujar(g, jugador);

        g.setColor(Color.red);
        g.drawString("X = " + jugador.obtenerPosicionX(), 20, 20);
        g.drawString("y = " + jugador.obtenerPosicionY(), 20, 30);

        //g.fillRect((int) mapa.obtenerZonaSalida().getX(), (int) mapa.obtenerZonaSalida().getY(), (int) mapa.obtenerZonaSalida().getWidth(), (int) mapa.obtenerZonaSalida().getHeight());

        g.drawString("siguiente mapa : " + mapa.obtenerSiguienteMapa(), 20, 150);
        g.drawString("coordenadas salida X : " + mapa.obtenerPuntoSalida().getX() + " y =" + mapa.obtenerPuntoSalida().getY(), 20, 160);


    }
}
