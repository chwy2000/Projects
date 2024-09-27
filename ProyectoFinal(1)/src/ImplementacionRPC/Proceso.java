package ImplementacionRPC;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Proceso {

    // Método público para leer un archivo y contar el número de palabras
    public Integer contarPalabras(String rutaArchivo) {
        int cont = 0;
        File archivo = new File(rutaArchivo);
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] palabras = linea.split("\\s+");
                cont += palabras.length;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return cont;
    }

    // Método público para determinar si el número de palabras es par o impar
    public String parOImpar(String rutaArchivo) {
        int numPalabras = contarPalabras(rutaArchivo);
        return (numPalabras % 2 == 0) ? "par" : "impar";
    }

    // Nuevo método para buscar una palabra en el archivo
    public String buscarPalabra(String rutaArchivo, String palabra) {
        File archivo = new File(rutaArchivo);
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (linea.contains(palabra)) {
                    return "La palabra '" + palabra + "' se encuentra en el archivo.";
                }
            }
        } catch (IOException e) {
            return "Error al leer el archivo: " + e.getMessage();
        }
        return "La palabra '" + palabra + "' no se encuentra en el archivo.";
    }
}