package principal.maquinaDeEstado;

import principal.maquinaDeEstado.estados.juego.GestorJuego;
import principal.maquinaDeEstado.estados.menuDeJuego.GestorMenu;

import java.awt.*;

public class GestorDeEstado {
    private EstadoJuego[] estados;
    private EstadoJuego estadoActual;


    public GestorDeEstado() {
        iniciaEstado();
        iniciaEstadoActual();
    }

    private void iniciaEstado() {
        estados = new EstadoJuego[2];
        estados[0] = new GestorJuego();
        estados[1] = new GestorMenu();
    }

    private void iniciaEstadoActual() {
        estadoActual = estados[0];
    }

    public void actualizar() {
        estadoActual.actualizar();
    }

    public void dibujar(final Graphics g) {
        estadoActual.dibujar(g);
    }

    public void cambiarEstadActual(final int nuevoEstado) {
        estadoActual = estados[nuevoEstado];
    }

    public EstadoJuego obtenerEstadoActual() {
        return estadoActual;
    }
}
