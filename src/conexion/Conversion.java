package conexion;

import com.google.gson.Gson;

import static conexion.HttpHelper.getRequest;

public class Conversion {

public static String nombre = "nahuel";




    public Conversion(String nombre){


    this.nombre = nombre ;
}
public static String getNombre(){

    return nombre;
}
public void setNombre(){
    this.nombre = nombre;

}

}
