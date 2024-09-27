/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImplementacionRMI;

/**
 *
 * @author sergi
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface Proceso1 extends Remote {
    Map<String, String> procesarArchivo(String palabra) throws RemoteException;
}

