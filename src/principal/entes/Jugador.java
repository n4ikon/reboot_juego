package principal.entes;

import principal.Constantes;
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.herramientas.DibujoDebug;
import principal.mapas.Mapa;
import principal.sprites.HojaSprites;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Jugador {
    private double posicionX;
    private double posicionY;




    private int direccion;

    private double velocidad = 1;

    private boolean enMovimiento;

    private HojaSprites hs;

    private BufferedImage imagenActual;

    private final int ANCHO_JUGADOR = 16;
    private final int ALTO_JUGADOR = 16;

    private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2, Constantes.CENTRO_VENTANA_Y, ANCHO_JUGADOR, 1);
    private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2, Constantes.CENTRO_VENTANA_Y + ALTO_JUGADOR, ANCHO_JUGADOR, 1);
    private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2, Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);
    private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTRO_VENTANA_X + ANCHO_JUGADOR / 2, Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);

    public static double establecerPosicionX2;
    public static double establecerPosicionY2;
    private int animacion;
    private int estado;

    public static final int RESISTENCIA_TOTAL = 400;

    private int resistencia = 400;
    private int recuperacion = 100;
    private boolean recuperado = true;


    public Jugador() {
        posicionX = posicionX;
        posicionY = posicionY;



        direccion = 0;

        enMovimiento = false;

        hs = new HojaSprites(Constantes.RUTA_PERSONAJE, Constantes.LADO_SPRITE, false);
        imagenActual = hs.obtenerSprite(0).obtenerImagen();

        animacion = 0;
        estado = 0;




    }

    public void actualizar() {
        gestinarVelocidadResistencia();
        cambiarAnimacionEstado();
        enMovimiento = false;
        determinarDireccion();
        animar();



    }


    public void gestinarVelocidadResistencia() {
        if (GestorControles.teclado.corriendo && resistencia > 0) {
            velocidad = 2;
            recuperado = false;
            recuperacion = 0;
        } else {
            velocidad = 1;
            if (!recuperado && recuperacion < 100) {
                recuperacion++;

            }
            if (recuperacion == 100 && resistencia < 400) {
                resistencia++;
            }
        }
    }

    private void cambiarAnimacionEstado() {
        if (animacion < 30) {
            animacion++;
        } else {
            animacion = 0;
        }
        if (animacion < 15) {
            estado = 1;
        } else {
            estado = 2;
        }

    }

    private void determinarDireccion() {
        final int velocidadX = evaluarVelocidadX();
        final int velocidadY = evaluarVelocidadY();

        if (velocidadX == 0 && velocidadY == 0) {
            return;
        }
        if ((velocidadX != 0 && velocidadY == 0) || (velocidadX == 0 && velocidadY != 0)) {
            mover(velocidadX, velocidadY);
        } else {
            if (velocidadX == -1 && velocidadY == -1) {
                if (GestorControles.teclado.izquierda.obtenerUltimaPulsacion() > GestorControles.teclado.arriba.obtenerUltimaPulsacion()) {
                    mover(velocidadX, 0);
                } else {
                    mover(0, velocidadY);
                }
            }
            if (velocidadX == -1 && velocidadY == 1) {
                if (GestorControles.teclado.izquierda.obtenerUltimaPulsacion() > GestorControles.teclado.abajo.obtenerUltimaPulsacion()) {
                    mover(velocidadX, 0);
                } else {
                    mover(0, velocidadY);
                }
            }
            if (velocidadX == 1 && velocidadY == -1) {
                if (GestorControles.teclado.derecha.obtenerUltimaPulsacion() > GestorControles.teclado.arriba.obtenerUltimaPulsacion()) {
                    mover(velocidadX, 0);
                } else {
                    mover(0, velocidadY);
                }
            }
            if (velocidadX == 1 && velocidadY == 1) {
                if (GestorControles.teclado.derecha.obtenerUltimaPulsacion() > GestorControles.teclado.abajo.obtenerUltimaPulsacion()) {
                    mover(velocidadX, 0);
                } else {
                    mover(0, velocidadY);
                }
            }
        }


    }

    private int evaluarVelocidadX() {
        int velocidadX = 0;
        if (GestorControles.teclado.izquierda.estaPulsada() && !GestorControles.teclado.derecha.estaPulsada()) {
            velocidadX = -1;
        } else if (GestorControles.teclado.derecha.estaPulsada() && !GestorControles.teclado.izquierda.estaPulsada()) {
            velocidadX = 1;
        }
        return velocidadX;
    }

    private int evaluarVelocidadY() {
        int velocidadY = 0;
        if (GestorControles.teclado.arriba.estaPulsada() && !GestorControles.teclado.abajo.estaPulsada()) {
            velocidadY = -1;
        } else if (GestorControles.teclado.abajo.estaPulsada() && !GestorControles.teclado.arriba.estaPulsada()) {
            velocidadY = 1;
        }
        return velocidadY;
    }

    private void mover(final int velocidadX, final int velocidadY) {
        enMovimiento = true;

        cambiarDireccion(velocidadX, velocidadY);

        if (!fueraMApa(velocidadX, velocidadY)) {
            if (velocidadX == -1 && !enColisionIzquierda(velocidadX)) {
                posicionX += velocidadX * velocidad;
                restarResistencia();

            }
            if (velocidadX == 1 && !enColisionDerecha(velocidadX)) {
                posicionX += velocidadX * velocidad;
                restarResistencia();
            }
            if (velocidadY == -1 && !enColisionArriba(velocidadY)) {
                posicionY += velocidadY * velocidad;
                restarResistencia();
            }
            if (velocidadY == 1 && !enColisionAbajo(velocidadY)) {
                posicionY += velocidadY * velocidad;
                restarResistencia();
            }
        }
    }

    private void restarResistencia() {
        if (GestorControles.teclado.corriendo && resistencia > 0) {
            resistencia--;
        }
    }

    private boolean enColisionArriba(int velocidadY) {
        for (int r = 0; r < ElementosPrincipales.mapa.areasColision.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColision.get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * (int) velocidad + 3 * (int) velocidad;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
            if (LIMITE_ARRIBA.intersects(areaFutura)) {
                return true;
            }
        }
        return false;
    }

    private boolean enColisionAbajo(int velocidadY) {
        for (int r = 0; r < ElementosPrincipales.mapa.areasColision.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColision.get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * (int) velocidad - 3 * (int) velocidad;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
            if (LIMITE_ABAJO.intersects(areaFutura)) {
                return true;
            }
        }
        return false;
    }

    private boolean enColisionIzquierda(int velocidadX) {
        for (int r = 0; r < ElementosPrincipales.mapa.areasColision.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColision.get(r);

            int origenX = area.x + velocidadX * (int) velocidad + 3 * (int) velocidad;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
            if (LIMITE_IZQUIERDA.intersects(areaFutura)) {
                return true;
            }
        }
        return false;
    }

    private boolean enColisionDerecha(int velocidadX) {
        for (int r = 0; r < ElementosPrincipales.mapa.areasColision.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColision.get(r);

            int origenX = area.x + velocidadX * (int) velocidad - 3 * (int) velocidad;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
            if (LIMITE_DERECHA.intersects(areaFutura)) {
                return true;
            }
        }
        return false;
    }

    private boolean fueraMApa(final int velocidadX, final int velocidadY) {

        int posicionFuturaX = (int) posicionX + velocidadX * (int) velocidad;
        int posicionFuturaY = (int) posicionY + velocidadY * (int) velocidad;

        final Rectangle bordesMapas = ElementosPrincipales.mapa.obtenerBordes(posicionFuturaX, posicionFuturaY);

        final boolean fuera;
        if (LIMITE_ARRIBA.intersects(bordesMapas) || LIMITE_ABAJO.intersects(bordesMapas) || LIMITE_IZQUIERDA.intersects(bordesMapas) || LIMITE_DERECHA.intersects(bordesMapas)) {
            fuera = false;
        } else {
            fuera = true;
        }

        return fuera;

    }

    private void cambiarDireccion(final int velocidadX, final int velocidadY) {
        if (velocidadX == -1) {
            direccion = 3;

        } else if (velocidadX == 1) {
            direccion = 2;
        }
        if (velocidadY == -1) {
            direccion = 1;
        } else if (velocidadY == 1) {
            direccion = 0;
        }


    }

    private void animar() {
        if (!enMovimiento) {
            estado = 0;
            animacion = 0;
        }
        imagenActual = hs.obtenerSprite(direccion, estado).obtenerImagen();
    }


    public void dibujar(Graphics g) {
        final int centroX = Constantes.ANCHO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
        final int centroY = Constantes.ALTO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
        g.setColor(Color.green);

        g.drawImage(imagenActual, centroX, centroY, null);


    }

    public void establecerPosicionX(double posicionX) {
        this.posicionX = posicionX;
    }

    public void establecerPosicionY(double posicionY) {
        this.posicionY = posicionY;
    }

    public double obtenerPosicionX() {
        return posicionX;
    }

    public int obtenerPosicionXInt(){return (int) posicionX;}
    public int obtenerPosicionYInt(){return (int) posicionY;}
    public int obtenerAncho(){return ANCHO_JUGADOR;}
    public int obtenerAlto(){return ALTO_JUGADOR;}
    public int obtenerResistencia(){return resistencia;}


    public double obtenerPosicionY() {
        return posicionY;
    }

    public Rectangle obtenerLimiteArriba() {
        return LIMITE_ARRIBA;
    }
}
