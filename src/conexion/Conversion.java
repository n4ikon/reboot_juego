package conexion;

import com.google.gson.annotations.SerializedName;

public class Conversion {


    @SerializedName("nombre")
    private   String nombre;

    @SerializedName("id")
   private String id;




    public Conversion(String nombre, String id){
    this.id = id;
    this.nombre = nombre; ;
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

}
