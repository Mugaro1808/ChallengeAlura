import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("|* BIENVENIDO AL CONVERSOR DE MONEDAS *|\n");

        Aplicacion aplicacion = new Aplicacion();

        aplicacion.mostrarMenu();
    }
}