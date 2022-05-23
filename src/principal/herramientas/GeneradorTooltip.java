package principal.herramientas;

import principal.Constantes;
import principal.graficos.SuperficieDeDibujo;

import java.awt.*;

public class GeneradorTooltip {
    public static Point generarTooltips(final Point pi) {
        final int x = pi.x;
        final int y = pi.y;

        final Point centroCanvas = new Point(Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y);

        final Point centroCanvasEscalado = new Point(EscaladorElemento.escalarPuntoArriba(centroCanvas).x,
                EscaladorElemento.escalarPuntoArriba(centroCanvas).y);
        final int margenCursor = 5;

        final Point pf = new Point();
        if (x <= centroCanvasEscalado.x) {
            if (y <= centroCanvasEscalado.y) {
                pf.x = x + Constantes.ladoCursor + margenCursor;
                pf.y = y + Constantes.ladoCursor + margenCursor;

            } else {
                pf.x = x + Constantes.ladoCursor + margenCursor;
                pf.y = y - Constantes.ladoCursor - margenCursor;

            }
        } else {
            if (y <= centroCanvasEscalado.y) {
                pf.x = x - Constantes.ladoCursor - margenCursor;
                pf.y = y + Constantes.ladoCursor + margenCursor;

            } else {
                pf.x = x - Constantes.ladoCursor - margenCursor;
                pf.y = y - Constantes.ladoCursor - margenCursor;


            }
        }
        return pf;
    }

    public static String obtenerPosicionTooltip(final Point pi) {
        final int x = pi.x;
        final int y = pi.y;

        final Point centroCanvas = new Point(Constantes.CENTRO_VENTANA_X, Constantes.CENTRO_VENTANA_Y);

        final Point centroCanvasEscalado = new Point(EscaladorElemento.escalarPuntoArriba(centroCanvas).x,
                EscaladorElemento.escalarPuntoArriba(centroCanvas).y);

        String posicion = "";
        if (x <= centroCanvasEscalado.x) {
            if (y <= centroCanvasEscalado.y) {
                posicion = "no";
            } else {
                posicion = "so";

            }
        } else {
            if (y <= centroCanvasEscalado.y) {
                posicion = "ne";

            } else {
                posicion = "se";


            }
        }
        return posicion;
    }

    public static void dibujarTooltip(final Graphics g, final SuperficieDeDibujo sd, final String texto) {
        final Point posicionRAton = sd.obtenerRaton().obtenerPuntoPosicion();
        final Point posicionTooltip = GeneradorTooltip.generarTooltips(posicionRAton);
        final String pistaPosicion = GeneradorTooltip.obtenerPosicionTooltip(posicionRAton);
        final Point posicionTooltipescalada = EscaladorElemento.escalarPuntoAbajo(posicionTooltip);


        final int ancho = MedidorStrings.medirAnchoPixeles(g, texto);
        final int alto = MedidorStrings.medirAltoPixeles(g, texto);
        final int margenFuente = 2;

        Rectangle tooltip = null;
        switch (pistaPosicion) {
            case "no":
                tooltip = new Rectangle(posicionTooltipescalada.x, posicionTooltipescalada.y, ancho + margenFuente * 2, alto);
                break;
            case "ne":
                tooltip = new Rectangle(posicionTooltipescalada.x - ancho, posicionTooltipescalada.y, ancho + margenFuente * 2, alto);
                break;
            case "so":
                tooltip = new Rectangle(posicionTooltipescalada.x, posicionTooltipescalada.y - alto, ancho + margenFuente * 2, alto);
                break;
            case "se":
                tooltip = new Rectangle(posicionTooltipescalada.x - ancho, posicionTooltipescalada.y - alto, ancho + margenFuente * 2, alto);
                break;

        }
        DibujoDebug.dibujarRectanguloRelleno(g, tooltip, Color.black);
        DibujoDebug.dibujarString(g, texto, new Point(tooltip.x + margenFuente, tooltip.y + tooltip.height - margenFuente), Color.white);
    }

}
