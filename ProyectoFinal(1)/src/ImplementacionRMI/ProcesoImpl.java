
package ImplementacionRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

public class ProcesoImpl extends UnicastRemoteObject implements Proceso {

    private static final String directorio="Archivos"; // Directorio relativo donde están los archivos

    public ProcesoImpl()throws RemoteException {
        super();
    }

    @Override
    public int cont(String nombreArchivo)throws RemoteException {
        int contador=0;
        Path path=Paths.get(directorio, nombreArchivo);

        // Verificar si el archivo existe
        if(!Files.exists(path)){
            throw new RemoteException("El archivo " + nombreArchivo + " no se encuentra en el directorio.");
        }

        try {
            // Leer el contenido del archivo
            String contenido=new String(Files.readAllBytes(path));
            String[] palabras=contenido.split("\\s+"); // Dividir por espacios en blanco
            contador=palabras.length;
        } catch(IOException e) {
            throw new RemoteException("Error al leer el archivo: "+e.getMessage());
        }

        return contador;
    }

    @Override
    public boolean par(String nombreArchivo)throws RemoteException {
        int total=cont(nombreArchivo);  // Usamos el resultado del método cont para evitar duplicar lógica
        return total%2==0;
    }

    @Override
    public boolean buscarPalabra(String nombreArchivo,String palabra)throws RemoteException {
        Path path=Paths.get(directorio,nombreArchivo);

        // Verificar si el archivo existe
        if(!Files.exists(path)){
            throw new RemoteException("El archivo "+nombreArchivo+" no existe en el directorio.");
        }

        try {
            // Leer el contenido del archivo
            String contenido=new String(Files.readAllBytes(path));
            String[] palabras=contenido.split("\\s+"); // Dividir por espacios en blanco
            return Arrays.asList(palabras).contains(palabra); // Buscar la palabra en el archivo
        } catch (IOException e) {
            throw new RemoteException("Error al leer el archivo: " + e.getMessage());
        }
    }
}