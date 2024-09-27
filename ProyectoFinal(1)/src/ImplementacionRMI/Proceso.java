package ImplementacionRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Proceso extends Remote {
    // Método para contar palabras en el archivo
    public int cont(String nombreArchivo)throws RemoteException;

    // Método para determinar si el número de palabras es par o impar
    public boolean par(String nombreArchivo)throws RemoteException;

    // Método para buscar una palabra en el archivo
    public boolean buscarPalabra(String nombreArchivo,String palabra)throws RemoteException;
}