import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Aplicacion {

    private static final String URL_USD = "https://v6.exchangerate-api.com/v6/6a5c6ef06a798daacd3f4042/latest/USD";
    private static final String URL_ARS = "https://v6.exchangerate-api.com/v6/6a5c6ef06a798daacd3f4042/latest/ARS";
    private static final String URL_EUR = "https://v6.exchangerate-api.com/v6/6a5c6ef06a798daacd3f4042/latest/EUR";
    private static final String URL_COP = "https://v6.exchangerate-api.com/v6/6a5c6ef06a798daacd3f4042/latest/COP";
    private static final String URL_JPY = "https://v6.exchangerate-api.com/v6/6a5c6ef06a798daacd3f4042/latest/JPY";

    public void mostrarMenu() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int opcionElegida = 0;

        HttpClient client = HttpClient.newHttpClient();

        while (opcionElegida != 9) {
            System.out.println("\n\n *************************************");
            System.out.println("\n ** Escriba el número de la opción deseada a convertir **");
            System.out.println(" 1  - Dólar --> Peso Argentino: ");
            System.out.println(" 2  - Peso Argentino --> Dólar: ");
            System.out.println(" 3  - Dólar --> Euro: ");
            System.out.println(" 4  - Euro --> Dólar: ");
            System.out.println(" 5  - Dólar --> Peso Colombiano: ");
            System.out.println(" 6  - Peso Colombiano --> Dólar: ");
            System.out.println(" 7  - Dólar --> Yen: ");
            System.out.println(" 8  - Yen --> Dólar: ");
            System.out.println(" 9  - Salir ");
            System.out.print("Seleccione una opción: ");
            opcionElegida = scanner.nextInt();

            switch (opcionElegida) {
                case 1:
                    obtenerDatos(client, URL_USD, "USD to ARS", "ARS");
                    break;
                case 2:
                    obtenerDatos(client, URL_ARS, "ARS to USD", "USD");
                    break;
                case 3:
                    obtenerDatos(client, URL_USD, "USD to EUR", "EUR");
                    break;
                case 4:
                    obtenerDatos(client, URL_EUR, "EUR to USD", "USD");
                    break;
                case 5:
                    obtenerDatos(client, URL_USD, "USD to COP", "COP");
                    break;
                case 6:
                    obtenerDatos(client, URL_COP, "COP to USD", "USD");
                    break;
                case 7:
                    obtenerDatos(client, URL_USD, "USD to JPY", "JPY");
                    break;
                case 8:
                    obtenerDatos(client, URL_JPY, "JPY to USD", "USD");
                    break;
                case 9:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        scanner.close();
    }

    private void obtenerDatos(HttpClient client, String url, String conversionType, String monedaDestino) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            double tasaDeCambio = jsonObject.getAsJsonObject("conversion_rates").get(monedaDestino).getAsDouble();

            System.out.println("1 " + conversionType.split(" ")[0] + " = " + tasaDeCambio + " " + monedaDestino);
        } else {
            System.out.println("Error al obtener los datos: " + response.statusCode());
        }
    }
}
