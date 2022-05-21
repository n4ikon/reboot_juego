package principal.maquinaDeEstado.estados.menuDeJuego;

import principal.herramientas.DibujoDebug;

import java.awt.*;

public abstract class SeccionMenu {
    protected final String nombreSeccion;
    protected final Rectangle etiquetaMenu;

    public SeccionMenu(final String nombreSeccion, final Rectangle etiquetaMenu) {
        this.nombreSeccion = nombreSeccion;
        this.etiquetaMenu = etiquetaMenu;

    }

    public abstract void actualizar();

    public abstract void dibujar(final Graphics g);

    public void dibujarEtiquetaInactiva(final Graphics g) {
        DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, Color.white);
        DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 15, etiquetaMenu.y + 12, Color.BLACK);

    }

    public void dibujarEtiquetaActiva(final Graphics g) {
        DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, Color.white);
        final Rectangle marcaActiva = new Rectangle(etiquetaMenu.x, etiquetaMenu.y, 20, etiquetaMenu.height);
        final Color colorActivo = new Color(0xffFF0010);
        DibujoDebug.dibujarRectanguloRelleno(g, marcaActiva, colorActivo);

        DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 15, etiquetaMenu.y + 12, Color.BLACK);

    }

    public String obtenerNombreSeccion() {
        return nombreSeccion;
    }


}
