package principal.sprites;

import principal.herramientas.CargadorRecuros;

import java.awt.image.BufferedImage;

public class HojaSprites {
    final private int anchoHojaPixeles;
    final private int altoHojaPixeles;

    final private int anchoHojaSprites;
    final private  int altoHojaSprites;

    final private int anchoSprite;
    final private int altoSprite;


    private Sprites[] sprites;

    public HojaSprites(final String ruta, final int tamanoSprite, final Boolean hojaOpaca){
        final BufferedImage imagen;
        if (hojaOpaca) {
            imagen = CargadorRecuros.cargarImagenCompatibleOpaca(ruta);
        }else {
            imagen = CargadorRecuros.cargarImagenCompatibleTranslucida(ruta);
        }
        anchoHojaPixeles = imagen.getWidth();
        altoHojaPixeles = imagen.getHeight();

        anchoHojaSprites = anchoHojaPixeles/ tamanoSprite;
        altoHojaSprites= altoHojaPixeles / tamanoSprite;

        anchoSprite = tamanoSprite;
        altoSprite = tamanoSprite;

        sprites = new Sprites[anchoHojaSprites * altoHojaSprites];

        extraerSprite(imagen);


    }
    public HojaSprites(final String ruta, final int anchoSprite, final int altoSprite, final Boolean hojaOpaca){
        final BufferedImage imagen;
        if (hojaOpaca) {
            imagen = CargadorRecuros.cargarImagenCompatibleOpaca(ruta);
        }else {
            imagen = CargadorRecuros.cargarImagenCompatibleTranslucida(ruta);
        }
        anchoHojaPixeles = imagen.getWidth();
        altoHojaPixeles = imagen.getHeight();

        anchoHojaSprites = anchoHojaPixeles/ anchoSprite;
        altoHojaSprites= altoHojaPixeles / altoSprite;

        this.anchoSprite = anchoSprite;
        this.altoSprite = altoSprite;

        sprites = new Sprites[anchoHojaSprites * altoHojaSprites];

        extraerSprite(imagen);


    }
    private void extraerSprite(final BufferedImage imagen){
        for(int y = 0; y < altoHojaSprites; y++){
            for( int x = 0; x < anchoHojaSprites; x++){
                final int posicionX = x * anchoSprite;
                final int posicionY = y *altoSprite;
                sprites[x + y * anchoHojaSprites] = new Sprites(imagen.getSubimage(posicionX,posicionY,anchoSprite,altoSprite));
            }
        }

    }
    public Sprites obtenerSprite(final int indice){
        return sprites[indice];

    }
    public Sprites obtenerSprite(final int x , final int y){
        return sprites[x + y * anchoHojaSprites];
    }
}
