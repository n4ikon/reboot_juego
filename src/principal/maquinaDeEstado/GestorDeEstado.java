package principal.maquinaDeEstado;

import principal.Login;
import principal.graficos.SuperficieDeDibujo;
import principal.maquinaDeEstado.estados.juego.GestorJuego;
import principal.maquinaDeEstado.estados.menuDeJuego.GestorMenu;

import java.awt.*;

public class GestorDeEstado {
    private EstadoJuego[] estados;
    private EstadoJuego estadoActual;


    public GestorDeEstado(final SuperficieDeDibujo sd) {
        iniciaEstado(sd);
        iniciaEstadoActual();
    }

    private void iniciaEstado(final SuperficieDeDibujo sd) {
        estados = new EstadoJuego[3];
        estados[0] = new GestorJuego();
        estados[1] = new GestorMenu(sd);
        estados[2]= new Login();




    }

    private void iniciaEstadoActual() {
        estadoActual = estados[2];
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
