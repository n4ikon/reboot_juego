package principal.inventario;



import java.util.ArrayList;

public class Inventario {

    public final ArrayList<Objeto> objetos;

    public Inventario(){

        objetos = new ArrayList<Objeto>();


    }
    public void recojerObjetos(final ContenedorObjetos co){
        for (Objeto objeto : co.obtenerObjetos()){
            if (objetoExiste(objeto)){incrementarObjeto(objeto, objeto.obtenerCantidad());
            }else {objetos.add(objeto);}

        }
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
    public boolean objetoExiste(final Objeto objeto){
        boolean existe = false;
        for(Objeto objetoActual : objetos){
            if(objetoActual.obtenerId()==objeto.obtenerId()){
                existe = true;
                break;
            }
        }
        return existe;
    }
}
