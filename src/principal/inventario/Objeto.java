package principal.inventario;


import principal.Constantes;
import principal.sprites.HojaSprites;
import principal.sprites.Sprites;

public class Objeto {
    public static HojaSprites hojaObjetos = new HojaSprites(Constantes.RUTA_OBJETOS, Constantes.LADO_SPRITE, false);

    private final int id;
    private final String nombre;
    private final String descripcion;
    private final Sprites sprites;
    private int cantidad;
    private int cantidadMax;

    public Objeto(final int id, final String nombre, final String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sprites = hojaObjetos.obtenerSprite(id);
        cantidad = 0;
        cantidadMax = 99;
    }


    public Objeto(final int id, final String nombre, final String descripcion, final int cantidad) {
        this(id, nombre, descripcion);
        if (cantidad <= cantidadMax) {
            this.cantidad = cantidad;
        }
    }


    public Sprites obtenerSprite(){
        return sprites;
    }
    public boolean incrementarCantidad(final int incremento){
        boolean incrementado = false;
        if (cantidad + incremento <= cantidadMax){
            cantidad += incremento;
            incrementado = true;
        }
        return incrementado;
    }
    public boolean reducirCantidad(final int reduccion){
        boolean reducido = false;
        if (cantidad - reduccion >= 0){
            cantidad -= reduccion;
            reducido = true;
        }
        return reducido;
    }
    public int obtenerCantidad(){
        return cantidad;
    }
    public int obtenerId(){
        return id;
    }
}
