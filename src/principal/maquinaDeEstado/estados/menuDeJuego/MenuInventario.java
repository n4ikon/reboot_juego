package principal.maquinaDeEstado.estados.menuDeJuego;

import conexion.Conversion;

import principal.Constantes;
import principal.Login;
import principal.graficos.SuperficieDeDibujo;
import principal.herramientas.DibujoDebug;
import principal.herramientas.EscaladorElemento;
import principal.herramientas.GeneradorTooltip;

import java.awt.*;

public class MenuInventario extends SeccionMenu {
    private int limitePeso = 100;
    private int pesoActual = 20;
    private final int margenGeneral = 8;

    private final Rectangle barraPeso;


    public MenuInventario(String nombreSeccion, Rectangle etiquetaMenu, EstructuraMenu em) {
        super(nombreSeccion, etiquetaMenu);
        int anchoBarra = 100;

        barraPeso = new Rectangle(Constantes.ANCHO_JUEGO - anchoBarra - margenGeneral, em.BANER_SUPERIOR.height + margenGeneral, anchoBarra, margenGeneral);

    }


    public void actualizar() {

    }


    public void dibujar(Graphics g, SuperficieDeDibujo sd) {
        dibujaLimitePeso(g);
        if (sd.obtenerRaton().obtenerrectanguloPosicion().intersects
                (EscaladorElemento.escalarRectanguloArriba(barraPeso))) {
            GeneradorTooltip.dibujarTooltip(g, sd, "Limite de peso : " + pesoActual + "/" + limitePeso);

        }

    }

    public void dibujaLimitePeso(Graphics g ){


        final Rectangle contenidoBarra = new Rectangle(barraPeso.x + 1, barraPeso.y + 1, barraPeso.width / (limitePeso / pesoActual), barraPeso.height - 2);
        DibujoDebug.dibujarString(g, "peso", barraPeso.x - 30, barraPeso.y + margenGeneral - 1, Color.black);
        DibujoDebug.dibujarRectanguloRelleno(g, barraPeso, Color.GRAY);
        DibujoDebug.dibujarRectanguloRelleno(g, contenidoBarra, Color.orange);
        DibujoDebug.dibujarString(g, "Nombre: " , barraPeso.x - 30, barraPeso.y - 20 + margenGeneral - 1, Color.black);
    }


}
