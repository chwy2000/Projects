package ImplementcioHilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;

public class Main {
    private static final int PORT = 8001; // Puerto del servidor de hilos

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor Hilos en ejecución en el puerto " + PORT + ".");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    String opcion = input.readLine(); // Leer la opción del servidor principal
                    if (opcion != null) {
                        int opcionSeleccionada = Integer.parseInt(opcion);
                        output.println("Opción recibida: " + opcionSeleccionada);
                        
                        // Procesar el archivo solo si la opción es válida
                        String archivoNombre = "Archivo.txt"; // Ruta relativa
                        File archivo = new File(archivoNombre);
                        if (!archivo.exists()) {
                            output.println("El archivo " + archivoNombre + " no existe.");
                            continue;
                        }

                        // Procesar archivo
                        output.println("Iniciando procesamiento del archivo...");
                        Hilo1 h1 = new Hilo1(archivoNombre);
                        h1.start();
                        h1.join(); // Esperar a que Hilo1 termine

                        // Una vez que Hilo1 ha terminado, se puede obtener el contenido
                        String contenido = h1.getContenido();

                        // Crear y iniciar Hilo2 para contar palabras
                        Hilo2 h2 = new Hilo2(contenido);
                        h2.start();
                        h2.join(); // Esperar a que Hilo2 termine
                        int numPalabras = h2.getNumPalabras();

                        // Crear y iniciar Hilo3 para verificar si el número de palabras es par o impar
                        Hilo3 h3 = new Hilo3(numPalabras);
                        h3.start();
                        h3.join(); // Esperar a que Hilo3 termine
                        boolean esPar = h3.isPar(); // Obtenemos si es par o impar

                        // Crear y iniciar Hilo4 para buscar una palabra específica
                        String palabraBuscar = "ejemplo"; // Cambia esto por la palabra que deseas buscar
                        Hilo4 h4 = new Hilo4(contenido, palabraBuscar);
                        h4.start();
                        h4.join(); // Esperar a que Hilo4 termine
                        boolean palabraEncontrada = h4.isEncontrada(); // Comprobar si la palabra fue encontrada

                        // Enviar resultados al servidor principal
                        output.println("Número de palabras: " + numPalabras);
                        output.println("Es par: " + esPar);
                        output.println("Palabra '" + palabraBuscar + "' encontrada: " + palabraEncontrada);
                    }
                } catch (IOException e) {
                    System.err.println("Error al procesar la solicitud: " + e.getMessage());
                } catch (InterruptedException e) {
                    System.err.println("Error al esperar a que un hilo termine: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
