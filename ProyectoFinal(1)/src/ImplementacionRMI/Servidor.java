
package ImplementacionRMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            // Crear objeto de la implementación del servicio
            Proceso archivoProcessor = new ProcesoImpl();

            // Crear el registro RMI
            LocateRegistry.createRegistry(8003);

            // Ligar el objeto al nombre que le damos
            Naming.bind("ServicioArchivoProcessor", archivoProcessor);
            System.out.println("Servidor RMI está listo.");

            // Mantener el servidor en espera de solicitudes
            int puerto = 1099; // Puerto para el servidor RMI
            try (ServerSocket serverSocket = new ServerSocket(puerto)) {
                while (true) {
                    try (Socket clientSocket = serverSocket.accept();
                         PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                         BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                        output.println("Servidor RMI en espera de solicitudes.");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al iniciar el servidor RMI: " + e);
        }
    }
}
