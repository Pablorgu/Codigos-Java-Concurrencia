package Barca;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Barca {
	private int nPasajeros = 0;
	private int orillaactual=1;
	private boolean bajarse = false;
	private boolean fin = false;
	private ReentrantLock l = new ReentrantLock();
	private Condition okentrar = l.newCondition();
	private Condition okbajar = l.newCondition();
	private Condition okviaje = l.newCondition();
	private Condition okfin = l.newCondition();

	/*
	 * El Pasajero id quiere darse una vuelta en la barca desde la orilla pos
	 */
	public  void subir(int id,int pos) throws InterruptedException{
		//TODO
		l.lock();
		try{
		while(orillaactual != pos || nPasajeros > 2 || bajarse) {
			okentrar.await();
		}
		nPasajeros++;
		System.out.println("Viajero "+ id + " se sube al barco en la orilla " + pos);
		// if(nPasajeros < 3 ){
		// 	okentrar.signalAll();
		// }
		if(nPasajeros == 3) {
			okviaje.signal();
		}
	}finally{
		l.unlock();
	}
	}
	
	/*
	 * Cuando el viaje ha terminado, el Pasajero que esta en la barca se baja
	 */
	public  int bajar(int id) throws InterruptedException{
		//TODO
		l.lock();
		try{
		while(!bajarse) {
			okbajar.await();
		}
		nPasajeros--;
		System.out.println(" Viajero "+ id + " se baja del barco");
		if(nPasajeros==0){
		System.out.println("Barca vacía...pueden subir nuevos pasajeros");
		okentrar.signalAll();
		bajarse=false;
		}
		if(orillaactual ==1) {
			orillaactual=0;
		} else {
			orillaactual=1;
		}
		return orillaactual;
		}finally{
			l.unlock();
		}
	}
	/*
	 * El Capitan espera hasta que se suben 3 pasajeros para comenzar el viaje
	 */
	public  void esperoSuban() throws InterruptedException{
		//TODOç
		l.lock();
		try{
		while(nPasajeros<3 || fin || bajarse){
			okviaje.await();
		}
		System.out.println("Empieza el viaje!!!!");
		fin=true;
		okfin.signal();
		}finally{
			l.unlock();
		}
	}
	/*
	 * El Capitan indica a los pasajeros que el viaje ha terminado y tienen que bajarse
	 */
	public  void finViaje() throws InterruptedException{
		//TODO
		l.lock();
		try{
		while(!fin) {
			okfin.await();
		}
		System.out.println("Fin del viaje!!!!");
		fin = false;
		bajarse = true;
		okbajar.signalAll();
		}finally{
			l.unlock();
		}
	}

}
