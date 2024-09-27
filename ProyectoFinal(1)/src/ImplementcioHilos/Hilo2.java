package ImplementcioHilos;

public class Hilo2 extends Thread {
    private String contenido;
    private int numPalabras;

    public Hilo2(String contenido) {
        this.contenido=contenido;
    }

    @Override
    public void run() {
        numPalabras=contarPalabras(contenido);
        System.out.println("El archivo tiene "+numPalabras+" palabras");
    }

    public int getNumPalabras() {
        return numPalabras;
    }

    private int contarPalabras(String contenido) {
        if (contenido.trim().isEmpty()) return 0; // Evitar conteos erróneos
        String[]palabras=contenido.split("\\s+");
        return palabras.length;
    }
}
