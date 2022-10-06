package principal.inventario;

import principal.inventario.armas.Desarmado;
import principal.inventario.armas.Pistola;
import principal.inventario.comsumibles.Consumibles;

public class RegistrosObjetos {
    public static Objeto obtenerObjeto(final int idObjeto){
        Objeto objeto = null;
        switch (idObjeto){

            case 0 :
                objeto = new Consumibles(idObjeto,"","");
                break;
            case 1 :
                objeto = new Consumibles(idObjeto,"","");
                break;
            case 500:
                objeto = new Pistola(idObjeto,"glock","pistola",3, 5);
                break;
            case 599 :
                objeto = new Desarmado(idObjeto,"puños","desarmado",1,2);
                break;
        }
        return objeto;
    }

}
