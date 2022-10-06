package principal.inventario;


import principal.Constantes;
import principal.sprites.HojaSprites;
import principal.sprites.Sprites;

public abstract class Objeto {
    //public static HojaSprites hojaObjetos = new HojaSprites(Constantes.RUTA_OBJETOS, Constantes.LADO_SPRITE, false);

    protected final int id;
    protected final String nombre;
    protected final String descripcion;

    protected int cantidad;
    protected int cantidadMax;

    public Objeto(final int id, final String nombre, final String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;

        cantidad = 0;
        cantidadMax = 99;
    }


    public Objeto(final int id, final String nombre, final String descripcion, final int cantidad) {
        this(id, nombre, descripcion);
        if (cantidad <= cantidadMax) {
            this.cantidad = cantidad;
        }
    }


    public abstract Sprites obtenerSprite();

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
    public String obtenerNombre(){
        return nombre;
    }
    public String obtenerDescripcion(){
        return descripcion;
    }
}
