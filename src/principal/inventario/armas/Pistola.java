package principal.inventario.armas;

import java.awt.*;
import java.util.ArrayList;

public class Pistola extends Arma{

    public Pistola(int id, String nombre, String descripcion, int ataqueMinimo, int ataqueMAximo) {
        super(id, nombre, descripcion, ataqueMinimo, ataqueMAximo);
    }

    public Pistola(int id, String nombre, String descripcion, int cantidad, int ataqueMinimo, int ataqueMAximo) {
        super(id, nombre, descripcion, cantidad, ataqueMinimo, ataqueMAximo);
    }

    protected ArrayList<Rectangle> obtenerAlcance() {
        return null;
    }
}
