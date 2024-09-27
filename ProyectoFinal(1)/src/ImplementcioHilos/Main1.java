/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementcioHilos;

/**
 *
 * @author sergi
 */
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args, PrintWriter output) {
        Scanner scanner = new Scanner(System.in);
        String archivoNombre = "Archivo.txt"; // Ruta relativa al archivo
        File archivo = new File(archivoNombre);
        boolean seguirLeyendoArchivos = true;

        output.println("Servidor Hilos activado.");

        if (!archivo.exists()) {
            output.println("El archivo " + archivoNombre + " no existe. Asegúrese de que esté en la ruta correcta.");
            return; // Salir si no existe el archivo
        }

        while (seguirLeyendoArchivos) {
            // Aquí puedes implementar el código para procesar el archivo, por ejemplo, contar palabras o líneas
            Hilo1 h1 = new Hilo1(archivoNombre);
            h1.start();

            try {
                h1.join(); // Esperar a que Hilo1 termine
            } catch (InterruptedException e) {
                output.println("Error al ejecutar Hilo1: " + e.getMessage());
            }

            // Aquí puedes continuar con Hilo2, Hilo3, etc.

            // A partir de aquí puedes seguir solicitando búsqueda de palabras
            boolean seguirBuscandoPalabra = true;
            while (seguirBuscandoPalabra) {
                output.print("Ingrese la palabra a buscar en el archivo: ");
                String palabra = scanner.nextLine();

                // Crear y ejecutar Hilo4
                Hilo4 h4 = new Hilo4(h1.getContenido(), palabra);
                h4.start();

                try {
                    h4.join(); // Esperar a que Hilo4 termine
                } catch (InterruptedException e) {
                    output.println("Error al ejecutar Hilo4: " + e.getMessage());
                }

                output.print("¿Desea buscar otra palabra? (sí/no): ");
                String respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("no")) {
                    seguirBuscandoPalabra = false;
                }
            }
        }
        scanner.close();
    }
}