package ImplementcioHilos;

public class Hilo3 extends Thread {
    private int numPalabras;
    private boolean esPar;

    public Hilo3(int numPalabras) {
        this.numPalabras = numPalabras;
    }

    @Override
    public void run() {
        // Determina si el número de palabras es par o impar
        esPar = (numPalabras % 2 == 0);
    }

    public boolean isPar() {
        return esPar;
    }
}
