package ServidorPrincipal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorPrincipal {
    private static final int PORT = 8000; // Puerto del servidor principal
    private static final int HILOS_PORT = 8001; // Puerto del servidor Hilos
    private static final int RPC_PORT = 8002; // Puerto del servidor RPC
    private static final int RMI_PORT = 8003; // Puerto del servidor RMI

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor Principal en ejecución en el puerto " + PORT + ".");

            while (true) {
                try {
                    // Aceptar nuevas conexiones
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                    
                    // Manejar la conexión en un nuevo hilo
                    new Thread(() -> handleClient(clientSocket)).start();
                } catch (IOException e) {
                    System.err.println("Error al aceptar conexión: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor principal: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (var input = clientSocket.getInputStream();
             var output = clientSocket.getOutputStream()) {

            // Leer la opción enviada por el cliente
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String opcionStr = reader.readLine();
            int opcion;

            try {
                opcion = Integer.parseInt(opcionStr.trim());
            } catch (NumberFormatException e) {
                sendMessageToClient(output, "Opción no válida. Por favor ingrese un número.");
                return; // Terminar ejecución para este cliente
            }

            switch (opcion) {
                case 1:
                    startHilosServer(output);
                    break;
                case 2:
                    startRpcServer(output);
                    break;
                case 3:
                    startRmiServer(output);
                    break;
                default:
                    sendMessageToClient(output, "Opción no válida.");
                    break;
            }
        } catch (IOException e) {
            System.err.println("Error al manejar la conexión del cliente: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar la conexión del cliente: " + e.getMessage());
            }
        }
    }

    private static void startHilosServer(OutputStream clientOutput) {
        System.out.println("Iniciando Servidor Hilos...");
        try (Socket hilosSocket = new Socket("localhost", HILOS_PORT);
             BufferedReader hilosInput = new BufferedReader(new InputStreamReader(hilosSocket.getInputStream()))) {

            // Leer respuesta del servidor Hilos y enviar al cliente
            String respuesta;
            while ((respuesta = hilosInput.readLine()) != null) {
                sendMessageToClient(clientOutput, respuesta);
            }
        } catch (IOException e) {
            sendMessageToClient(clientOutput, "Error al conectar con el Servidor Hilos: " + e.getMessage());
            System.err.println("Error al conectar con el Servidor Hilos: " + e.getMessage());
        }
    }

    private static void startRpcServer(OutputStream clientOutput) {
        System.out.println("Iniciando Servidor RPC...");
        try (Socket rpcSocket = new Socket("localhost", RPC_PORT);
             BufferedReader rpcInput = new BufferedReader(new InputStreamReader(rpcSocket.getInputStream()))) {

            // Leer respuesta del servidor RPC y enviar al cliente
            String respuesta;
            while ((respuesta = rpcInput.readLine()) != null) {
                sendMessageToClient(clientOutput, respuesta);
            }
        } catch (IOException e) {
            sendMessageToClient(clientOutput, "Error al conectar con el Servidor RPC: " + e.getMessage());
            System.err.println("Error al conectar con el Servidor RPC: " + e.getMessage());
        }
    }

    private static void startRmiServer(OutputStream clientOutput) {
        System.out.println("Iniciando Servidor RMI...");
        try (Socket rmiSocket = new Socket("localhost", RMI_PORT);
             BufferedReader rmiInput = new BufferedReader(new InputStreamReader(rmiSocket.getInputStream()))) {

            // Leer respuesta del servidor RMI y enviar al cliente
            String respuesta;
            while ((respuesta = rmiInput.readLine()) != null) {
                sendMessageToClient(clientOutput, respuesta);
            }
        } catch (IOException e) {
            sendMessageToClient(clientOutput, "Error al conectar con el Servidor RMI: " + e.getMessage());
            System.err.println("Error al conectar con el Servidor RMI: " + e.getMessage());
        }
    }

    private static void sendMessageToClient(OutputStream output, String message) {
        try {
            output.write((message + "\n").getBytes());
            output.flush();
        } catch (IOException e) {
            System.err.println("Error al enviar mensaje al cliente: " + e.getMessage());
        }
    }
}
