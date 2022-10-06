package principal;


import principal.herramientas.CargadorRecuros;

import java.awt.*;

public class Constantes {
    public static final int LADO_SPRITE = 32;
    public static int ladoCursor = 0;


    public static int ANCHO_JUEGO = 640;
    public static int ALTO_JUEGO = 360;

    public static int ANCHO_PANTALLA = 1366;
    public static int ALTO_PANTALLA = 768;

    public static double FACTOR_ESCALADO_X = (double) ANCHO_PANTALLA / (double) ANCHO_JUEGO;
    public static double FACTOR_ESCALADO_Y = (double) ALTO_PANTALLA / (double) ALTO_JUEGO;

    public static int CENTRO_VENTANA_X = ANCHO_JUEGO / 2;
    public static int CENTRO_VENTANA_Y = ALTO_JUEGO / 2;


    public static String RUTA_MAPA = "recursos/textos/01";
    public static String RUTA_RATON = "recursos/imagenes/iconos/raton.png";

    public static String RUTA_PERSONAJE = "recursos/imagenes/hojasPersonajes/1.png";
    public static String RUTA_OBJETOS = "recursos/imagenes/hojasObjetos/objetos.png";
    public static String RUTA_OBJETOS_ARMAS = "recursos/imagenes/hojasObjetos/armas.png";


    public static Font FUENTE_PIXEL = CargadorRecuros.cargarFuentes("recursos/fuentes/pixel.ttf");
    public String NOMBRE ;

}
