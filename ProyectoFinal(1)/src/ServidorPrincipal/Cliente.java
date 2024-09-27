package ServidorPrincipal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
    private static final String SERVER_ADDRESS = "localhost"; // Dirección del servidor
    private static final int SERVER_PORT = 8000; // Puerto del servidor principal

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             OutputStream output = socket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al Servidor Principal.");
            System.out.println("Selecciona una opción:");
            System.out.println("1. Servidor Hilos");
            System.out.println("2. Servidor RPC");
            System.out.println("3. Servidor RMI");
            System.out.println("4. Salir");

            String opcion;
            while (!(opcion = input.readLine()).equals("4")) {
                output.write((opcion + "\n").getBytes()); // Enviar opción al servidor
                output.flush();

                // Leer la respuesta del servidor
                String respuesta;
                while ((respuesta = reader.readLine()) != null) {
                    System.out.println("Respuesta del servidor: " + respuesta);
                }
                
                System.out.println("Selecciona una opción:");
            }

            System.out.println("Saliendo...");
        } catch (IOException e) {
            System.err.println("Error en la conexión con el servidor: " + e.getMessage());
        }
    }
}
