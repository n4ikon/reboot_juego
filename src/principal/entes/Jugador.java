package principal.entes;

import principal.Constantes;
import principal.control.GestorControles;
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

    private int animacion;
    private int estado;

    public Jugador(double posicionX, double posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;


        direccion = 0;

        enMovimiento = false;

        hs = new HojaSprites("recursos/imagenes/hojasPersonajes/1.png", Constantes.LADO_SPRITE, false);
        imagenActual = hs.obtenerSprite(0).obtenerImagen();

        animacion = 0;
        estado = 0;

    }

    public void actualizar() {
        cambiarAnimacionEstado();
        enMovimiento = false;
        determinarDireccion();
        animar();


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

        posicionX += velocidadX * velocidad;

        posicionY += velocidadY * velocidad;

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
        final int centroX = Constantes.ANCHO_PANTALLA / 2 - Constantes.LADO_SPRITE / 2;
        final int centroY = Constantes.ALTO_PANTALLA / 2 - Constantes.LADO_SPRITE / 2;
        g.setColor(Color.green);

        g.drawImage(imagenActual, centroX, centroY, null);
        g.drawRect(centroX + 6, centroY, 18, 32);
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

    public double obtenerPosicionY() {
        return posicionY;
    }
}
