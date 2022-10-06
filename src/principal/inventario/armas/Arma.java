package principal.inventario.armas;

import principal.Constantes;
import principal.inventario.Objeto;
import principal.sprites.HojaSprites;
import principal.sprites.Sprites;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Arma extends Objeto {
    public static HojaSprites hojaArmas = new HojaSprites(Constantes.RUTA_OBJETOS_ARMAS, Constantes.LADO_SPRITE, false);
protected int ataqueMinimo;
protected int ataqueMAximo;

    public Arma(int id, String nombre, String descripcion, int ataqueMinimo, int ataqueMAximo) {
        super(id, nombre, descripcion);
        this.ataqueMinimo = ataqueMinimo;
        this.ataqueMAximo = ataqueMAximo;
    }

    public Arma(int id, String nombre, String descripcion, int cantidad, int ataqueMinimo, int ataqueMAximo) {
        super(id, nombre, descripcion, cantidad);
        this.ataqueMinimo = ataqueMinimo;
        this.ataqueMAximo = ataqueMAximo;
    }
    protected abstract ArrayList <Rectangle> obtenerAlcance();

    public Sprites obtenerSprite() {
        return hojaArmas.obtenerSprite(id - 500);
    }
    protected int obtenerAtaqueMedio(final int ataqueMinimo, final int ataqueMAximo){
        Random r = new Random();
        return r.nextInt(ataqueMAximo - ataqueMinimo) + ataqueMinimo;

    }
}
