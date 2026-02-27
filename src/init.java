import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Paths;
import java.util.Scanner;

import com.google.gson.Gson;

import models.consultaDTO;
import models.moneda;
/*DEBER
Hacer menu de conversion entre monedas
Dol/ARS
ARS/DOL
DOL/REAL
REAL/DOL
DOL/COL
COL/DOL
EXIT */
public class init {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=0;
        double in;
        while (n != 7) {
            System.out.println("\n***********************************");
            System.out.println("   CONVERSOR DE MONEDAS V1.0");
            System.out.println("1) Dolar (USD) -> Peso Argentino (ARS)");
            System.out.println("2) Peso Argentino (ARS) -> Dólar (USD)");
            System.out.println("3) Dolar (USD) -> Real Brasileño (BRL)");
            System.out.println("4) Real Brasileño (BRL) -> Dolar (USD)");
            System.out.println("5) Dolar (USD) -> Peso Colombiano (COP)");
            System.out.println("6) Peso Colombiano (COP) -> Dolar (USD)");
            System.out.println("7) Salir");
            System.out.print("Elija una opción: ");
            n = sc.nextInt();
            if (n==7){System.out.println("Cerrando el sistema");
                break;
            }
            System.out.println("Ingrese el monto que desea cambiar");
            in=sc.nextInt();
            switch (n) {
                case 1 -> System.out.println(cambio(consultarCambio("USD", "ARS"), in)); 
                case 2 -> System.out.println(cambio(in, consultarCambio("ARS", "USD")));
                case 3 -> System.out.println(cambio(consultarCambio("USD", "BRL"),in));
                case 4 -> System.out.println(cambio(in,consultarCambio("BRL", "USD")));
                case 5 -> System.out.println(cambio(consultarCambio("USD", "COP"),in));
                case 6 -> System.out.println(cambio(in,consultarCambio("COP", "USD")));
                default -> System.out.println("Numero inesperado intente nuevamente entre el 1 al 7");
            }
        }
    }
    public static double consultarCambio(String a, String b){
            String url = "https://v6.exchangerate-api.com/v6/966d1742b37588eb363e59ed/pair/"+ a + "/" + b;   
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
            var response=client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
            Gson gson = new Gson();
            consultaDTO valor = gson.fromJson(response, consultaDTO.class);
            moneda otrMoneda = new moneda(valor);
            return otrMoneda.getValor();
        }
    public static double cambio(double cC, double p){
        return cC*p;
    }
}

