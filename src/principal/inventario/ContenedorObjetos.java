package principal.inventario;

import principal.Constantes;
import principal.herramientas.CargadorRecuros;
import principal.herramientas.DibujoDebug;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ContenedorObjetos {
    BufferedImage sprite = CargadorRecuros.cargarImagenCompatibleTranslucida("recursos/imagenes/pc.png");
    private Point posicion;
    private Objeto[] objetos;
    public ContenedorObjetos(final Point posicion,final int[] objetos, final int[] cantidades){
        this.posicion = posicion;
        this.objetos = new Objeto[objetos.length];
        for (int  i = 0; i  <objetos.length ;  i++) {
            this.objetos[i] = RegistrosObjetos.obtenerObjeto(objetos[i]);
            this.objetos[i].incrementarCantidad(cantidades[i]);

        }

    }
    public void dibujar (final Graphics g, final int puntoX , final int puntoY){
        DibujoDebug.dibujarImagen(g,sprite,puntoX,puntoY);
    }
    public Point obtenerPosicion(){
        return posicion;
    }
    public Objeto[] obtenerObjetos(){
        return objetos;
    }
}
