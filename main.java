import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.w3c.dom.ls.LSOutput;

import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
int opcion = 1;
int opcionMenu = 0;

Scanner scanner = new Scanner(System.in);
while(opcion != 3){
    String Moneda1 = "";
    String Moneda2 = "";
    double monto = 0;

    System.out.println("""
            \n*******************************
            BIENVENIDO AL CONVERSOR DE MONEDAS v1.5
 
            1) CONVERSAR MONEDA
            3) SALIR
            *******************************
            """);
    opcion = scanner.nextInt();
if(opcion == 3){
    System.out.println("GRACIAS POR USAR MI APLICION \nGithub: https://github.com/Chequel197/Challenges_Conversor_Moneda.git\n" + "Linkedin: https://www.linkedin.com/in/ezequiel-sanchez-4b8a062a7/\n");
    break;
}
    switch (opcion){
        case 1:
            //menu
            System.out.println("""
                *****************************
                ARS - Peso argentino
                                
                BOB - Boliviano boliviano
                                
                BRL - Real brasileño
                                
                CLP - Peso chileno
                                
                COP - Peso colombiano
                                
                USD - Dólar estadounidense
                *****************************
                Digite las iniciales de la moneda base: 
                """);
            Moneda1 = scanner.next().toUpperCase();

            System.out.println("""
                *****************************
                ARS - Peso argentino
                                
                BOB - Boliviano boliviano
                                
                BRL - Real brasileño
                                
                CLP - Peso chileno
                                
                COP - Peso colombiano
                                
                USD - Dólar estadounidense
                ***************************** 
                """);
            System.out.println("Digite las iniciales de la moneda a la que quiere conversar");
            Moneda2 = scanner.next().toUpperCase();

            System.out.println("DIgite la cantidad de "+Moneda1+" que va conversionar a -->> "+ Moneda2);
            monto = Double.parseDouble(String.valueOf(scanner.nextDouble()));
            break;
    }
    URI direccion = URI.create("https://v6.exchangerate-api.com/v6/c36dc6ac318df2ab2a296f11/pair/"+Moneda1+"/"+Moneda2+"/"+monto);


    try {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        //se convirte a Json
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson((response.body()), JsonObject.class);
        //Despues de haberlo convertido se llama al parametro "conversion_resulta" para asacar el resultado de la
        //De la conversion de la moneda directamente de la consulta de la API
        double Respuesta = jsonObject.get("conversion_result").getAsDouble();

        System.out.println("\n El resultado es de convertir "+monto+" "+ Moneda1 + " es "+ +Math.round(Respuesta*100.0)/100.0 + " "+Moneda2) ;


    } catch (NumberFormatException e){
        System.out.println("ERROR");
    } catch (Exception e){
        System.out.println("ERROR");
    }

}

    }
}
