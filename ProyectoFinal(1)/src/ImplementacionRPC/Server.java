package ImplementacionRPC;


import org.apache.xmlrpc.WebServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int puerto = 8002; // Puerto para el servidor XML-RPC
        try {
            WebServer server = new WebServer(puerto);
            Proceso1 proceso = new Proceso1();
            server.addHandler("miServidor", proceso);
            server.start();
            System.out.println("Servidor XML-RPC iniciado exitosamente. Esperando solicitudes de clientes.");

            // Mantener el servidor en espera de solicitudes
            try (ServerSocket serverSocket = new ServerSocket(puerto)) {
                while (true) {
                    try (Socket clientSocket = serverSocket.accept();
                         PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                         BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                        output.println("Servidor RPC en espera de solicitudes.");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor RPC: " + e);
        }
    }
}
