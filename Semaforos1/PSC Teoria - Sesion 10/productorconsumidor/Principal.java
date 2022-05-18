package productorconsumidor;

public class Principal {
    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(1, true);
        Semaphore s2= new Semaphore(1,true);
        RecursoCompartido rc = new RecursoCompartido(s1,s2);
        Thread productor = new Thread(new Productor(rc));
        Thread consumidor = new Thread(new Consumidor(rc));

        productor.start();
        consumidor.start();

    }
}
