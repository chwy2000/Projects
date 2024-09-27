/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionRPC;

/**
 *
 * @author sergi
 */

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Proceso1{
    public Map<String, String> procesarArchivo(String palabra) {
        String archivoNombre = "Archivo.txt"; // Ruta relativa al archivo de texto
        File archivo = new File(archivoNombre);
        Map<String, String> resultados = new HashMap<>();

        if (!archivo.exists()) {
            resultados.put("error", "El archivo no existe.");
            return resultados;
        }

        // Lógica para buscar la palabra en el archivo
        try (Scanner scanner = new Scanner(archivo)) {
            int conteo = 0;
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                if (linea.contains(palabra)) {
                    conteo++;
                }
            }
            resultados.put("mensaje", "Archivo procesado exitosamente.");
            resultados.put("palabraBuscada", palabra);
            resultados.put("conteo", String.valueOf(conteo));
        } catch (Exception e) {
            resultados.put("error", "Error al procesar el archivo: " + e.getMessage());
        }

        return resultados;
    }
}
