package principal.inventario.comsumibles;

import principal.Constantes;
import principal.inventario.Objeto;
import principal.sprites.HojaSprites;
import principal.sprites.Sprites;

public class Consumibles extends Objeto {
    public static HojaSprites hojaConsumibles = new HojaSprites(Constantes.RUTA_OBJETOS, Constantes.LADO_SPRITE, false);

    public Consumibles(int id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
    }

    public Consumibles(int id, String nombre, String descripcion, int cantidad) {
        super(id, nombre, descripcion, cantidad);
    }

    public Sprites obtenerSprite() {
        return hojaConsumibles.obtenerSprite(id);
    }
}
