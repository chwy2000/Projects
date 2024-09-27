/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionRPC;

/**
 *
 * @author sergi
 */



import org.apache.xmlrpc.WebServer;
import java.io.PrintWriter;

public class Server1 {
    public static void main(String[] args, PrintWriter output) {
        try {
            output.println("Iniciando el servidor XML-RPC");
            WebServer server = new WebServer(8080);
            Proceso proceso = new Proceso(); // Instancia la clase que maneja las operaciones
            server.addHandler("miServidor", proceso);
            
            server.start();
            output.println("Servidor XML-RPC iniciado exitosamente. Esperando solicitudes de clientes.");
        } catch (Exception e) {
            output.println("Error en el servidor RPC: " + e);
        }
    }
}



