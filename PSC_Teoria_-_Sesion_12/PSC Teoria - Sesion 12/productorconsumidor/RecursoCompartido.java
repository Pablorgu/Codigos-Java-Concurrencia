package productorconsumidor;

import java.util.concurrent.Semaphore;

//Condición Productor:
//  No puedo almacenar hasta que no se ha leido.  
//Condición Consumidor:
// No puedo extraer hasta que no se ha almacenado uno nuevo. 
public class RecursoCompartido {
    private int recurso;

    public RecursoCompartido() {
    }

    public synchronized int extraer() {
        while(!almacenado) 
            wait();
        almacenado=false;
        System.out.println("Extraído " + recurso);
        notify();
        return recurso;
    }

    public synchronized void almacenar(int r) {
        

        recurso = r;

        System.out.println("Almacenado " + r);
    }

}
