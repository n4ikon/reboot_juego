package principal.control;

import conexion.HttpHelper;
import principal.ElementosPrincipales;
import principal.entes.Jugador;
import principal.inventario.Inventario;
import principal.maquinaDeEstado.estados.menuDeJuego.MenuInventario;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

    public Tecla arriba = new Tecla();
    public Tecla abajo = new Tecla();
    public Tecla izquierda = new Tecla();
    public Tecla derecha = new Tecla();
    public Tecla guardar =new Tecla();
    public boolean recoger = false;

    public boolean corriendo = false;

    public boolean inventarioActivo = false;

    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                arriba.teclaPulsada();
                break;
            case KeyEvent.VK_S:
                abajo.teclaPulsada();
                break;
            case KeyEvent.VK_A:
                izquierda.teclaPulsada();
                break;
            case KeyEvent.VK_D:
                derecha.teclaPulsada();
                break;
            case KeyEvent.VK_E:
                recoger = true;
                break;
            case KeyEvent.VK_SHIFT:
                corriendo = true;
                break;

            case KeyEvent.VK_I:
                inventarioActivo = !inventarioActivo;
                break;

            case KeyEvent.VK_L:
                HttpHelper.Put_JSON(MenuInventario.nombre,ElementosPrincipales.jugador.obtenerPosicionXInt(),ElementosPrincipales.jugador.obtenerPosicionYInt());
                break;

            case KeyEvent.VK_ESCAPE:
                System.exit(0);

        }

    }


    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                arriba.teclaLiberada();
                break;
            case KeyEvent.VK_S:
                abajo.teclaLiberada();
                break;
            case KeyEvent.VK_A:
                izquierda.teclaLiberada();
                break;
            case KeyEvent.VK_D:
                derecha.teclaLiberada();
                break;
            case KeyEvent.VK_L:
                guardar.teclaLiberada();
                break;
            case KeyEvent.VK_E:
                recoger = false;
                break;
            case KeyEvent.VK_SHIFT:
                corriendo = false;
                break;
        }


    }
}
