package principal.maquinaDeEstado.estados.menuDeJuego;

import principal.herramientas.DibujoDebug;
import principal.maquinaDeEstado.EstadoJuego;

import java.awt.*;

public class GestorMenu implements EstadoJuego {

    private final EstructuraMenu estructuraMenu;

    private final SeccionMenu[] secciones;

    private SeccionMenu seccionACtual;

    public GestorMenu() {
        estructuraMenu = new EstructuraMenu();
        secciones = new SeccionMenu[2];

        final Rectangle etiquetaInventario = new Rectangle(estructuraMenu.BANER_LATERAL.x + estructuraMenu.MARGEN_HORIZONTAL_ETIQUETAS, estructuraMenu.BANER_LATERAL.y + estructuraMenu.MARGEN_VERTICAL_ETIQUETAS, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);
        secciones[0] = new MenuInventario("inventario", etiquetaInventario);

        final Rectangle etiquetaEquipo = new Rectangle(estructuraMenu.BANER_LATERAL.x + estructuraMenu.MARGEN_HORIZONTAL_ETIQUETAS, etiquetaInventario.y + etiquetaInventario.height + estructuraMenu.MARGEN_VERTICAL_ETIQUETAS, estructuraMenu.ANCHO_ETIQUETAS, estructuraMenu.ALTO_ETIQUETAS);

        secciones[1] = new MenuEquipo("Equipo", etiquetaEquipo);
        seccionACtual = secciones[0];
    }

    public void actualizar() {

    }

    public void dibujar(final Graphics g) {
        estructuraMenu.dibujar(g);
        for (int i = 0; i < secciones.length; i++) {
            if (seccionACtual == secciones[i]) {
                secciones[i].dibujarEtiquetaActiva(g);
            } else {
                secciones[i].dibujarEtiquetaInactiva(g);
            }

        }


    }

}
