package principal.inventario.armas;

import java.awt.*;
import java.util.ArrayList;

public class Desarmado extends Arma{

    public Desarmado(int id, String nombre, String descripcion, int ataqueMinimo, int ataqueMAximo) {
        super(id, nombre, descripcion, ataqueMinimo, ataqueMAximo);
    }

    public Desarmado(int id, String nombre, String descripcion, int cantidad, int ataqueMinimo, int ataqueMAximo) {
        super(id, nombre, descripcion, cantidad, ataqueMinimo, ataqueMAximo);
    }

    protected ArrayList<Rectangle> obtenerAlcance() {
        return null;
    }
}
