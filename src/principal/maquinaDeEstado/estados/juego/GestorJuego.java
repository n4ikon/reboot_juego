package principal.maquinaDeEstado.estados.juego;

import principal.control.GestorControles;
import principal.entes.Jugador;
import principal.herramientas.CargadorRecuros;
import principal.mapas.Mapa;
import principal.maquinaDeEstado.EstadoJuego;
import principal.sprites.HojaSprites;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GestorJuego implements EstadoJuego{





    Mapa mapa = new Mapa("recursos/textos/texto1");

    Jugador jugador = new Jugador(0,0);

    public void actualizar() {
        jugador.actualizar();


    }
    public void dibujar(Graphics g) {
        mapa.dibujar(g,(int) jugador.obtenerPosicionX(),(int) jugador.obtenerPosicionY());
        jugador.dibujar(g);

        g.setColor(Color.red);
        g.drawString("X = " +jugador.obtenerPosicionX(), 20 , 20 );
        g.drawString("y = " + jugador.obtenerPosicionY(),20 ,30);

    }
}
