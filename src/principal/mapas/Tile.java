package principal.mapas;

import principal.sprites.Sprites;

import java.awt.*;

public class Tile {
    private final Sprites sprite;
    private final int id;
    private boolean solido;


    public Tile( final Sprites sprite, final int id){
        this.sprite = sprite;
        this.id = id;
        solido = false;

    }
    public Tile( final Sprites sprite, final int id,final boolean solido) {
        this.sprite = sprite;
        this.id = id;
        this.solido = solido;
    }

    public Sprites obtenerSprite() {
        return sprite;
    }

    public int obtenerId() {
        return id;
    }

    public void establecerSolido(boolean solido) {
        this.solido = solido;
    }
    public Rectangle obtenerLimites(final int x , final int y){
        return new Rectangle(x,y,sprite.obtenerAncho(),sprite.obteneralto());
    }
}

