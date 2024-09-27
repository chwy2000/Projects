package ImplementcioHilos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Hilo1 extends Thread {
    private String archivoNombre;
    private String contenido;

    public Hilo1(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    @Override
    public void run() {
        try {
            File archivo = new File(archivoNombre);
            // Leer el contenido del archivo
            contenido=Files.readString(archivo.toPath());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: "+e.getMessage());
        }
    }

    public String getContenido() {
        return contenido;
    }
}


