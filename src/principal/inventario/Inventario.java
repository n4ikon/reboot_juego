package principal.inventario;



import java.util.ArrayList;

public class Inventario {

    public final ArrayList<Objeto> objetos;

    public Inventario(){

        objetos = new ArrayList<Objeto>();
        objetos.add(RegistrosObjetos.objetos[0]);
        objetos.add(RegistrosObjetos.objetos[1]);
        objetos.add(RegistrosObjetos.objetos[2]);
        objetos.add(RegistrosObjetos.objetos[3]);

        incrementarObjeto(RegistrosObjetos.objetos[3],1 );

    }
    public boolean incrementarObjeto(final Objeto objeto, final int cantidad ) {
        boolean incrementado = false;
        for (Objeto objetoActual : objetos) {
            if (objetoActual.obtenerId()== objeto.obtenerId()){
             objetoActual.incrementarCantidad(cantidad);
             incrementado=true;
             break;
            }
        }
        return incrementado;
    }
}
