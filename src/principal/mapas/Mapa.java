package principal.mapas;

import principal.Constantes;
import principal.herramientas.CargadorRecuros;
import principal.sprites.HojaSprites;
import principal.sprites.Sprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mapa {
    private String[] partes;

    private final int ancho;
    private final int alto;

    private final Sprites[] paleta;
    private final boolean[] colisiones;

    private final int[] sprite;



    public Mapa (final String ruta){
        String contenido = CargadorRecuros.leerArchivoTexto(ruta);
        partes = contenido.split("\\*");
        ancho = Integer.parseInt(partes[0]);
        alto = Integer.parseInt(partes[1]);

        String hojasUtilizadas = partes[2];
        String[] hojasSeparadas = hojasUtilizadas.split(",");

        String paletaEntera = partes[3];
        String[] partesPaleta = paletaEntera.split("#");

        paleta = asignarSprite(partesPaleta,hojasSeparadas);



        String colisionesEnteras = partes[4];
        colisiones = extraerColisiones(colisionesEnteras);


        String spritesEnteros = partes[5];
        String[] cadenasSprites = spritesEnteros.split(" ");

        sprite = extraerSprite(cadenasSprites);


        }

        private Sprites[] asignarSprite(final String[] partesPaleta, final String[] hojasSparadas){
            Sprites[] paleta = new Sprites[partesPaleta.length];
            HojaSprites hoja = new HojaSprites("recursos/imagenes/hojasSprite/"+ hojasSparadas[0]+ ".png", 32,false);
            for (int i = 0; i < partesPaleta.length; i++) {
                String spriteTemporal = partesPaleta[i];
                String[] partesSprite = spriteTemporal.split("-");

                int indicePaleta = Integer.parseInt(partesSprite[0]);
                int indiceSpriteHoja =Integer.parseInt(partesSprite[2]);

                paleta[indicePaleta] = hoja.obtenerSprite(indiceSpriteHoja);

            }


        return paleta;

        }




    private boolean[] extraerColisiones(final String cadenaColisiones){

        boolean[] colisiones = new boolean[cadenaColisiones.length()];
        for (int i = 0; i < cadenaColisiones.length(); i++) {
            if(cadenaColisiones.charAt(i)== '0'){
                colisiones[i] = false;
            }else {colisiones[i] = true;
            }

        }
        return colisiones;
    }
    private int[] extraerSprite(final String[] cadenasSprites){
        ArrayList<Integer> sprite = new ArrayList<Integer>();

        for (int i = 0; i < cadenasSprites.length; i++) {
            if ( cadenasSprites[i].length()==2){
              sprite.add(Integer.parseInt(cadenasSprites[i]));
            }else {
                String uno = "";
                String dos = "";

                String error = cadenasSprites[i];

                uno += error.charAt(0);
                uno += error.charAt(1);

                dos += error.charAt(2);
                dos += error.charAt(3);

                sprite.add(Integer.parseInt(uno));
                sprite.add(Integer.parseInt(dos));
            }
        }
        int[] vectorSprites = new int [sprite.size()];
        for (int i = 0; i < sprite.size(); i++) {
            vectorSprites[i] =sprite.get(i);
        }
        
        return vectorSprites;

    }

    public void dibujar(Graphics g, int posicionX , int posicionY){
        int anchoSprite = Constantes.LADO_SPRITE;
        int altoSprite = anchoSprite;

        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho ; x++) {
                BufferedImage imagen =paleta[sprite[x + y * this.ancho]].obtenerImagen();
                g.drawImage(imagen,x* anchoSprite - posicionX,y*altoSprite - posicionY,null);

            }
            
        }

    }
        
}
