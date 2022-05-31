package productorconsumidor;

import java.util.concurrent.Semaphore;

//Condición Productor:
//  No puedo almacenar hasta que no se ha leido.  Un semáforo haySitio se puede encargar de sincronizar cuando hay hueco para almacenar.
//Condición Consumidor:
// No puedo extraer hasta que no se ha almacenado uno nuevo. Un semáforo hayDato se puede encargar de sincronizar cuando hay dato.

public class RecursoCompartido {
    private int recurso;
    private Semaphore HayDato;
    private Semaphore HaySitio;

    public RecursoCompartido(Semaphore s1, Semaphore s2) {
        this.HayDato=s1;
        this.HaySitio=s2;
    }

    public int extraer() {
        try{
            HayDato.acquire();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        int datoLeido;

        datoLeido = recurso;
        System.out.println("Extraído " + datoLeido);
        HaySitio.release();
        return datoLeido;
    }

    public void almacenar(int r) {
        try{
            HaySitio.acquire();
        } catch {
            e.printStackTrace
        }
        recurso = r;
        System.out.println("Almacenado " + r);
        HayDato.release();
    }

}
