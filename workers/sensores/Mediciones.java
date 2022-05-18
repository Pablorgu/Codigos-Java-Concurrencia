package sensores;

import java.util.concurrent.Semaphore;

public class Mediciones {
    int numMediciones=0;
    private Semaphore mutex = new Semaphore(1);
    private Semaphore EsperoPorTrabajador = new Semaphore(0);
    private Semaphore EsperoPorSensores = new Semaphore (0);

    public Mediciones() {
        
    }

    /**
     * El sensor id deja su medición y espera hasta que el trabajador
     * ha terminado sus tareas
     * 
     * @param id
     * @throws InterruptedException
     */
    public void nuevaMedicion(int id) {
        
        mutex.acquire();
        System.out.println("Sensor " + id + " deja sus mediciones.");
        numMediciones++;
        if(NumMediciones == 3) {
            EsperoPorSensores.release();
        }
        mutex.release();
        EsperoPorTrabajador.acquire();
        mutex.acquire();
        numMediciones--;
        if(numMediciones!=0) {
            EsperoPorTrabajador.release();
        }
        mutex.release();
    }

    /***
     * El trabajador espera hasta que están las tres mediciones
     * 
     * @throws InterruptedException
     */
    public void leerMediciones() {
        EsperoPorSensores.acquire();
        System.out.println("El trabajador tiene sus mediciones...y empieza sus tareas");
    }

    /**
     * El trabajador indica que ha terminado sus tareas
     */
    public void finTareas() {
        System.out.println("El trabajador ha terminado sus tareas");
        EsperoPorTrabajador.release();
    }

}
