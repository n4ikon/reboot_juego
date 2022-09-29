package conexion;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import principal.Login;
import principal.maquinaDeEstado.estados.menuDeJuego.MenuInventario;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;



public  class HttpHelper {
    private String URL = "http://localhost:8080";



    public static void getRequest() {
        // GET
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/Jugador")).build();
        CompletableFuture cf = cliente.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(JsonUsable -> {


                    Conversion[] conversion= new Gson().fromJson(JsonUsable, Conversion[].class)
                            ;
                    for (int i = 0; i < conversion.length ; i++) {
                        if ( Login.nombreLogin.equals(conversion[i].getNombre()) && Login.passwordLogin.equals(conversion[i].getPassword())){
                            MenuInventario.nombre = Login.nombreLogin;



                        }

                    }

                    });




        HttpResponse<String> result = (HttpResponse<String>)cf.join();






    }



    public static void Post_JSON(String nombre ) {
        String query_url = "http://localhost:8080/Jugador";
        String json =String.format("{ \"nombre\" : \"%s\"}", nombre);
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            System.out.println(result);
            System.out.println("result after Reading JSON Response");
            JSONObject myResponse = new JSONObject(result);
            System.out.println("jsonrpc- "+myResponse.getString("jsonrpc"));
            System.out.println("id- "+myResponse.getInt("id"));
            System.out.println("result- "+myResponse.getString("result"));
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

 Gson gson = new Gson();
    //Conversion conversion = gson.fromJson(getRequest(),Conversion.class);



}
