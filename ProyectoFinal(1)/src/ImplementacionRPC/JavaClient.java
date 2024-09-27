package ImplementacionRPC;

import java.util.Scanner;
import java.util.Vector;
import org.apache.xmlrpc.XmlRpcClient;

public class JavaClient {
    public static void main(String[] args) {
        try {
            XmlRpcClient cliente = new XmlRpcClient("http://localhost:8080/"); // Cambia la URL y el puerto si es necesario
            
            Scanner scanner = new Scanner(System.in);
            String nombreArchivo;

            while (true) {
                // Solicita al usuario el nombre del archivo
                System.out.print("Ingrese el nombre del archivo .txt o escriba salir para terminar: ");
                nombreArchivo=scanner.nextLine();
                
                if(nombreArchivo.equalsIgnoreCase("salir")) {
                    break; // Termina el bucle si el usuario ingresa 'salir'
                }

                // Procesa el archivo ingresado
                procesarArchivo(cliente,nombreArchivo);

                // Menú para buscar palabra o volver a seleccionar archivo
                while (true){
                    System.out.print("Ingrese una palabra para buscar en el archivo o escriba salir para regresar al menu: ");
                    String entrada=scanner.nextLine();

                    if(entrada.equalsIgnoreCase("salir")) {
                        break; // Regresa al menú de selección de archivo
                    } else {
                        // Busca la palabra en el archivo
                        buscarPalabra(cliente,nombreArchivo,entrada);
                    }
                }
            }
            
            scanner.close();
            
        } catch (Exception e) {
            System.err.println("JavaClient: "+e);
        }
    }

    private static void procesarArchivo(XmlRpcClient cliente,String nombreArchivo) {
        try {
            Vector<String>params=new Vector<>();
            params.add(nombreArchivo);

            // Llama al método contarPalabras en el servidor
            Object resultado=cliente.execute("miServidor.contarPalabras", params);
            int numPalabras=((Integer) resultado).intValue();
            System.out.println("El numero de palabras en "+nombreArchivo+"es: "+numPalabras);

            // Llama al método parOImpar en el servidor
            Object resultadoParOImpar=cliente.execute("miServidor.parOImpar", params);
            String parOImpar=(String) resultadoParOImpar;
            System.out.println("El numero de palabras en "+nombreArchivo+" es: "+parOImpar);

        } catch (Exception e) {
            System.err.println("Error al procesar el archivo "+nombreArchivo+": "+e);
        }
    }

    private static void buscarPalabra(XmlRpcClient cliente, String nombreArchivo, String palabra) {
        try {
            Vector<String>params=new Vector<>();
            params.add(nombreArchivo);
            params.add(palabra);

            // Llama al método buscarPalabra en el servidor
            Object resultado=cliente.execute("miServidor.buscarPalabra", params);
            String mensaje=(String) resultado;
            System.out.println(mensaje);

        } catch (Exception e) {
            System.err.println("Error buscando la palabra en el archivo "+nombreArchivo+": "+e);
        }
    }
}