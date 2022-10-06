package principal.maquinaDeEstado.estados.juego;

import principal.Constantes;
import principal.ElementosPrincipales;
import principal.entes.Jugador;
import principal.interfa_usuario.MenuInferior;
import principal.mapas.Mapa;
import principal.maquinaDeEstado.EstadoJuego;

import java.awt.*;

public class GestorJuego implements EstadoJuego {


    MenuInferior menuInferior;

    public GestorJuego() {
        menuInferior = new MenuInferior();


    }

    private void recargarJuego() {
        final String ruta = "recursos/textos/" + ElementosPrincipales.mapa.obtenerSiguienteMapa();
        ElementosPrincipales.mapa = new Mapa(ruta);
    }




    public void actualizar() {

        if (ElementosPrincipales.jugador.obtenerLimiteArriba().intersects(ElementosPrincipales.mapa.obtenerZonaSalida())) {
            recargarJuego();
        }

        ElementosPrincipales.jugador.actualizar();
        ElementosPrincipales.mapa.actualizar();

    }

    public void dibujar(Graphics g) {
        ElementosPrincipales.mapa.dibujar(g);
        ElementosPrincipales.jugador.dibujar(g);
        menuInferior.dibujar(g);

        g.setColor(Color.white);
        g.drawString("X = " + ElementosPrincipales.jugador.obtenerPosicionXInt(), 20, 20);
        g.drawString("y = " + ElementosPrincipales.jugador.obtenerPosicionYInt(), 20, 30);


    }
}
