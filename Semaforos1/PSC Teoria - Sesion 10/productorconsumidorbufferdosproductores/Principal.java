package productorconsumidorbufferdosproductores;

import java.util.concurrent.Semaphore;

public class Principal {
    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(5, true);
        Semaphore s2= new Semaphore(0,true);
        Semaphore productores = new Semaphore(1,true)
        RecursoCompartidoBuffer rc = new RecursoCompartidoBuffer(5);
        // 1 2 3 4 5 6 7 8
        Thread productor = new Thread(new Productor(rc, 1));

        // 10 20 30 40 50 60 70 80
        Thread productor10 = new Thread(new Productor(rc, 10));
        Thread consumidor = new Thread(new Consumidor(rc));

        productor.start();
        consumidor.start();
        productor10.start();

    }
}
