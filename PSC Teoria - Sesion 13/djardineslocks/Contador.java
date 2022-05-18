package djardineslocks;

public class Contador {
    private int cont;

    public Contador() {
        cont = 0;
    }

    public synchronized void inc() {
        cont++;
    }

    public synchronized int valor() {
        return cont;
    }

}
