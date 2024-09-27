package ImplementcioHilos;

public class Hilo4 extends Thread {
    private String contenido;
    private String palabraBuscar;
    private boolean encontrada;

    public Hilo4(String contenido, String palabraBuscar) {
        this.contenido = contenido;
        this.palabraBuscar = palabraBuscar;
    }

    @Override
    public void run() {
        // Busca la palabra en el contenido
        encontrada = contenido.contains(palabraBuscar);
    }

    public boolean isEncontrada() {
        return encontrada;
    }
}
