package principal.interfa_usuario;

import java.awt.*;

public class InterfazUsuario {

    public static void dibujarResistencia(Graphics g, int resistencia) {
        int ancho = 100 * resistencia / 400;
        Color celeste = new Color(43, 216, 255);

        g.setColor(Color.white);
        g.drawRect(19, 49, 101, 9);

        g.setColor(celeste);
        g.fillRect(20, 50, ancho, 3);

        g.setColor(Color.blue);
        g.fillRect(20, 55, ancho, 3);
    }
}
