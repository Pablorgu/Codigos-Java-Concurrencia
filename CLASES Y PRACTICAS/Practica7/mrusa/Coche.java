package mrusa;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

public class Coche implements Runnable {
	private int tam;
	private int nPasajeros = 0;
	private Semaphore lleno = new Semaphore(0, true);
	private Semaphore haySitio = new Semaphore(1,true);
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore finviaje = new Semaphore(0, true);


	public Coche(int tam) {
		this.tam = tam;
	}

	public void subir(int id) throws InterruptedException {
		// id del pasajero que se sube al coche
		haySitio.acquire();
		mutex.acquire();
		nPasajeros++;
		System.out.println("El pasajero" + id + " sube al coche. Hay " + nPasajeros + " pasajeros a bordo");
		if(nPasajeros<tam) {
			haySitio.release();
		}
		if(nPasajeros==tam) {
			lleno.release();
		}
		mutex.release();
	}

	public void bajar(int id) throws InterruptedException {
		// id del pasajero que se baja del coche
		finviaje.acquire();
		mutex.acquire();
		nPasajeros--;
		System.out.println("El pasajero" + id + "baja del coche. Hay " + nPasajeros + " pasajeros a bordo");
		if(nPasajeros>0) {
			finviaje.release();
		}
		if(nPasajeros==0){
			haySitio.release();
		}
		mutex.release();
	}

	private void esperaLleno() throws InterruptedException {
		// el coche espera a que este lleno para dar una vuelta
		lleno.acquire();
		System.out.println("El coche da una vuelta");
		finviaje.release();
		
		}

	public void run() {
		while (true)
			try {
				this.esperaLleno();
				Thread.sleep(200);

			} catch (InterruptedException ie) {
			}

	}
}
// tam pasajeros se suben al coche->el coche da un viaje
// ->tam pasajeros se bajan del coche->......

// CS-Coche: Coche no se pone en marcha hasta que no está lleno
// CS-Pas1: Pasajero no se sube al coche hasta que no hay sitio para el.
// CS-Pas2: Pasajero no se baja del coche hasta que no ha terminado el viaje
