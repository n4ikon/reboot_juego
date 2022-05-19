package principal.herramientas;


import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.*;


public class CargadorRecuros {
    public static BufferedImage cargarImagenCompatibleOpaca(final String ruta){

    Image imagen = null;
    try{
        //imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
        imagen = ImageIO.read(new File(ruta));
    }catch(IOException e){
        e.printStackTrace();

    }
    GraphicsConfiguration gc =GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

    BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null),imagen.getHeight(null), Transparency.OPAQUE);

    Graphics g = imagenAcelerada.getGraphics();
     g.drawImage(imagen,0,0,null);
     g.dispose();
     return imagenAcelerada;

    }
    public static BufferedImage cargarImagenCompatibleTranslucida(final String ruta){
        Image imagen = null;
        try{
            //imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
            imagen = ImageIO.read(new File(ruta));
        }catch(IOException e){
            e.printStackTrace();

        }
        GraphicsConfiguration gc =GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null),imagen.getHeight(null), Transparency.TRANSLUCENT);

        Graphics g = imagenAcelerada.getGraphics();
        g.drawImage(imagen,0,0,null);
        g.dispose();
        return imagenAcelerada;

    }
    public static String leerArchivoTexto(final String ruta){
        String contenido = "";
        FileInputStream entradaBytes = null;
        BufferedReader lector = null;

        //InputStream entradaBytes = ClassLoader.class.getResourceAsStream(ruta);
        //BufferedReader lector = new BufferedReader(new InputStreamReader(entradaBytes));
        try{
            entradaBytes = new FileInputStream(ruta);
            lector = new BufferedReader(new InputStreamReader(entradaBytes));
        }catch (FileNotFoundException e1){
            e1.printStackTrace();

        }
        String linea;
        try{
            while ((linea = lector.readLine())!= null){
                contenido += linea;

            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (entradaBytes != null){
                    entradaBytes.close();
                }
                if (lector != null){
                    lector.close();
                }

            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return contenido;

    }
}



