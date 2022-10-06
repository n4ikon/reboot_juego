package principal.maquinaDeEstado.estados.menuDeJuego;
import conexion.Conversion;
import principal.Constantes;
import principal.Login;
import principal.graficos.SuperficieDeDibujo;
import principal.herramientas.DibujoDebug;

import java.awt.*;

public abstract class SeccionMenu {
    protected final String nombreSeccion;
    protected final Rectangle etiquetaMenu;

    protected final int margenGenearl = 8;

    public SeccionMenu(final String nombreSeccion, final Rectangle etiquetaMenu) {
        this.nombreSeccion = nombreSeccion;
        this.etiquetaMenu = etiquetaMenu;

    }

    public abstract void actualizar();

    public abstract void dibujar(final Graphics g, final SuperficieDeDibujo sd);

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

    public void dibujarEtiquetaInResaltada(Graphics g) {
        DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, Color.white);
        DibujoDebug.dibujarRectanguloRelleno(g, new Rectangle(etiquetaMenu.x + etiquetaMenu.width - 10, etiquetaMenu.y + 5, 5, etiquetaMenu.height - 10), new Color(0xff2a2a2a));

        DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 15, etiquetaMenu.y + 12, Color.BLACK);

    }

    public void dibujarEtiquetaActResaltada(Graphics g) {
        DibujoDebug.dibujarRectanguloRelleno(g, etiquetaMenu, Color.white);
        final Rectangle marcaActiva = new Rectangle(etiquetaMenu.x, etiquetaMenu.y, 20, etiquetaMenu.height);
        final Color colorActivo = new Color(0xffFF0010);
        DibujoDebug.dibujarRectanguloRelleno(g, marcaActiva, colorActivo);

        DibujoDebug.dibujarRectanguloRelleno(g, new Rectangle(etiquetaMenu.x + etiquetaMenu.width - 10, etiquetaMenu.y + 5, 5, etiquetaMenu.height - 10), new Color(0xff2a2a2a));


        DibujoDebug.dibujarString(g, nombreSeccion, etiquetaMenu.x + 15, etiquetaMenu.y + 12, Color.BLACK);


    }

    public String obtenerNombreSeccion() {
        return nombreSeccion;
    }

    public Rectangle obtenerEtiquetaMenu() {
        return etiquetaMenu;
    }

    public Rectangle obtenerEtiquetaMenuEScalada() {
        final Rectangle etiquetaEScalada = new Rectangle((int) (etiquetaMenu.x * Constantes.FACTOR_ESCALADO_X), (int) (etiquetaMenu.y * Constantes.FACTOR_ESCALADO_Y), (int) (etiquetaMenu.width * Constantes.FACTOR_ESCALADO_X), (int) (etiquetaMenu.height * Constantes.FACTOR_ESCALADO_Y));
        return etiquetaEScalada;
    }

}
