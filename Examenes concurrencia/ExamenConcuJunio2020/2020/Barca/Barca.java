package Barca;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Barca {
	private int nPasajeros = 0;
	private int orillaactual=1;
	private Semaphore hayhueco0 = new Semaphore(0, true);
	private Semaphore hayhueco1 = new Semaphore(1,true);
	private Semaphore finViaje = new Semaphore(0,true);
	private Semaphore bajar = new Semaphore(0, true);
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore empiezaviaje = new Semaphore(0, true);


	/*
	 * El Pasajero id quiere darse una vuelta en la barca desde la orilla pos
	 */
	public  void subir(int id,int pos) throws InterruptedException{
		//TODO
		if(pos==0){
			hayhueco0.acquire();
		} else if(pos==1) {
			hayhueco1.acquire();
		}
		mutex.acquire();
		++nPasajeros;
		System.out.println("Viajero "+ id + " se sube al barco en la orilla " + pos);
		if(nPasajeros < 3 && pos==0) {
			hayhueco0.release();
		} else if(nPasajeros < 3 && pos == 1) {
			hayhueco1.release();
		}
		if(nPasajeros==3) {
			empiezaviaje.release();
		}
		mutex.release();
	}
	
	/*
	 * Cuando el viaje ha terminado, el Pasajero que esta en la barca se baja
	 */
	public  int bajar(int id) throws InterruptedException{
		//TODO
		bajar.acquire();
		mutex.acquire();
		--nPasajeros;
		System.out.println(" Viajero "+ id + " se baja del barco");
		if(nPasajeros>0) {
			bajar.release();
		}
		if(nPasajeros==0){
		System.out.println("Barca vacía...pueden subir nuevos pasajeros");
		if(orillaactual==0) {
			orillaactual=1;
			hayhueco1.release();
		} else if (orillaactual==1) {
			orillaactual=0;
			hayhueco0.release();
		}
		}
		mutex.release();
		return orillaactual;
	}
	/*
	 * El Capitan espera hasta que se suben 3 pasajeros para comenzar el viaje
	 */
	public  void esperoSuban() throws InterruptedException{
		//TODO
		empiezaviaje.acquire();
		System.out.println("Empieza el viaje!!!!");
		finViaje.release();
		
	}
	/*
	 * El Capitan indica a los pasajeros que el viaje ha terminado y tienen que bajarse
	 */
	public  void finViaje() throws InterruptedException{
		//TODO
		finViaje.acquire();
		System.out.println("Fin del viaje!!!!");
		bajar.release();
	}

}
