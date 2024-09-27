
package ImplementacionRMI;

import java.rmi.Naming;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            // Conectar con el servicio RMI
            Proceso archivo=(Proceso)Naming.lookup("rmi://localhost/ServicioArchivoProcessor");
            Scanner scan=new Scanner(System.in);

            boolean continuar=true;
            while(continuar){
                // Solicitar el nombre del archivo
                System.out.println("Escribe el nombre del archivo .txt: ");
                String nombreArchivo=scan.nextLine().trim();

                // Validar el nombre del archivo
                if(nombreArchivo.isEmpty()||!nombreArchivo.endsWith(".txt")){
                    System.out.println("El archivo no existe, vuelve a ingresar un archivo.txt.");
                    continue;
                }

                try {
                    // Contar palabras en el archivo
                    int palabras=archivo.cont(nombreArchivo);

                    // Mostrar el número de palabras
                    System.out.println("El archivo tiene "+palabras+" palabras.");

                    // Determinar si el número de palabras es par o impar
                    boolean esPar=archivo.par(nombreArchivo);
                    if(esPar){
                        System.out.println("El número de palabras es par.");
                    }else{
                        System.out.println("El número de palabras es impar.");
                    }

                    // Ciclo para buscar múltiples palabras
                    boolean buscarOtraPalabra=true;
                    while(buscarOtraPalabra) {
                        // Solicitar una palabra para buscar en el archivo
                        System.out.println("Ingresa la palabra que deseas buscar: ");
                        String palabra=scan.nextLine().trim();

                        // Verificar si la palabra está en el archivo
                        boolean encontrada=archivo.buscarPalabra(nombreArchivo, palabra);
                        if(encontrada){
                            System.out.println("La palabra '"+palabra+"' si se ecuentra en el archivo.");
                        }else{
                            System.out.println("La palabra '" + palabra + "' no se encuentra en el archivo.");
                        }

                        // Preguntar si desea buscar otra palabra
                        System.out.println("¿Deseas buscar otra palabra en este archivo? s/n: ");
                        String respuestaPalabra=scan.nextLine().trim();
                        if(!respuestaPalabra.equalsIgnoreCase("s")) {
                            buscarOtraPalabra = false; // Sale del ciclo de búsqueda de palabras
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Error al procesar el archivo: ");
                    e.printStackTrace();
                }

                // Después de buscar palabras, pedir inmediatamente otro archivo
                System.out.println("¿Deseas procesar otro archivo? s/n: ");
                String respuestaArchivo=scan.nextLine().trim();
                if(!respuestaArchivo.equalsIgnoreCase("s")) {
                    continuar = false; // Finaliza el programa
                }
            }

            System.out.println("Programa finalizado.");
        } catch (Exception e) {
            System.err.println("Error al realizar la operación: " + e);
            e.printStackTrace();
        }
    }
}