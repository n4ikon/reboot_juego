package conexion;

import com.google.gson.annotations.SerializedName;

public class Conversion {


    @SerializedName("nombre")
    private   String nombre;

    @SerializedName("id")
   private String id;
    @SerializedName("password")
    private String password;
    @SerializedName("posicionX")
    private double posicionX;
    @SerializedName("posicionY")
    private double posicionY;

    private Conversion(String nombre, String id, double posicionX , double posicionY){
    this.id = id;
    this.nombre = nombre;
    this.posicionX = posicionX;
    this.posicionY = posicionY;

}
public double obtenerPosisionX (){
        return  posicionX;
}

    public double obtenerPosisionY() {
        return posicionY;
    }

    public  String getNombre(){

   return this.nombre;
}
public  void setNombre(String nombre){
    nombre = nombre;

}


    public  String get_Id() {
        return id;}

    public   void setId( String id){
      id= id;

    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        password = password;
    }

}
