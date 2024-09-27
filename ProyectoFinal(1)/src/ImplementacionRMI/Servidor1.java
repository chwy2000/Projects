/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionRMI;

/**
 *
 * @author sergi
 */
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.io.PrintWriter;

public class Servidor1 {
    public static void main(String[] args, PrintWriter output) {
        try {
            output.println("Iniciando el servidor RMI");
            Proceso archivoProcessor = new ProcesoImpl();

            // Crear el registro RMI
            LocateRegistry.createRegistry(1099);

            // Ligar el objeto al nombre que le damos
            Naming.bind("ServicioArchivoProcessor", archivoProcessor);

            output.println("Servidor RMI está listo.");
        } catch (Exception e) {
            output.println("Error al iniciar el servidor RMI: " + e);
        }
    }
}

