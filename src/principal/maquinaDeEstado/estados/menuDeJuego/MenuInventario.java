package principal.maquinaDeEstado.estados.menuDeJuego;

import principal.Constantes;
import principal.graficos.SuperficieDeDibujo;
import principal.herramientas.DibujoDebug;
import principal.herramientas.EscaladorElemento;
import principal.herramientas.GeneradorTooltip;
import principal.herramientas.MedidorStrings;
//import principal.inventario.Inventario;

import java.awt.*;

public class MenuInventario extends SeccionMenu {
    private int limitePeso = 100;
    private int pesoActual = 20;
    private final int margenGeneral = 8;

    private final Rectangle barraPeso;
    //private final Inventario inventario;
    private final EstructuraMenu em;
    public static String nombre;


    public MenuInventario(String nombreSeccion, Rectangle etiquetaMenu, EstructuraMenu em) {
        super(nombreSeccion, etiquetaMenu);
        int anchoBarra = 100;
        this.em = em;

        barraPeso = new Rectangle(Constantes.ANCHO_JUEGO - anchoBarra - 12, em.BANER_SUPERIOR.height + margenGeneral, anchoBarra, 8);
 //inventario = new Inventario();
    }


    public void actualizar() {

    }


    public void dibujar(Graphics g, SuperficieDeDibujo sd) {
        dibujaLimitePeso(g);
        dibujarElementosInventario(g,em);
        //dibujarSpritesInventario(g,em);
        //dibujarPaginador(g,em);

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
        DibujoDebug.dibujarString(g, "Nombre : " + nombre, barraPeso.x - 30, barraPeso.y - 20 + margenGeneral - 1, Color.black);
    }
    private void dibujarElementosInventario(final Graphics g,EstructuraMenu em){
        final Point puntoInicial = new Point(em.FONDO.x + 16, barraPeso.y + barraPeso.height + margenGeneral );
        g.setColor(Color.white);
        for(int y =0;y< 6 ; y++) {
            for (int x = 0; x < 12; x++) {
                DibujoDebug.dibujarRectanguloRelleno(g, puntoInicial.x + x * (Constantes.LADO_SPRITE + margenGeneral),
                        puntoInicial.y + y * (Constantes.LADO_SPRITE + margenGeneral), Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
            }
        }
    }
    /*private void dibujarSpritesInventario(final Graphics g,EstructuraMenu em){
        final Point puntoInicial = new Point(em.FONDO.x + 16, barraPeso.y + barraPeso.height + margenGeneral );
        for (int i = 0; i < inventario.objetos.size(); i++) {
            DibujoDebug.dibujarImagen(g,inventario.objetos.get(i).obtenerSprite().obtenerImagen(),
                    puntoInicial.x + i *(Constantes.LADO_SPRITE + margenGeneral),puntoInicial.y);
            g.setColor(Color.black);
            DibujoDebug.dibujarRectanguloRelleno(g,puntoInicial.x + i * (Constantes.LADO_SPRITE + margenGeneral)+ Constantes.LADO_SPRITE - 12
            ,puntoInicial.y + Constantes.LADO_SPRITE - 8 ,12,8);
            g.setColor(Color.white);
            String texto = "" + inventario.objetos.get(i).obtenerCantidad();
            DibujoDebug.dibujarString(g,texto,puntoInicial.x + i *(Constantes.LADO_SPRITE + margenGeneral)+ Constantes.LADO_SPRITE - MedidorStrings.medirAnchoPixeles(g,texto),
                    puntoInicial.y + Constantes.LADO_SPRITE - 1);
        }
    }

    private void dibujarPaginador(Graphics g , EstructuraMenu em){
        final int altoBoton = 32;
        final int anchoBoton= 16;
        final Rectangle anterior = new Rectangle(em.FONDO.x + em.FONDO.width - margenGeneral * 2 - anchoBoton * 2 - 4 ,
                em.FONDO.y + em.FONDO.height-margenGeneral- altoBoton,anchoBoton,altoBoton);
        final Rectangle siguiente =new Rectangle(anterior.x + anterior.width + margenGeneral,anterior.y,anchoBoton,
                altoBoton);
        g.setColor(Color.black);
        DibujoDebug.dibujarRectanguloRelleno(g,anterior);
        DibujoDebug.dibujarRectanguloRelleno(g,siguiente);
    }
*/

}
