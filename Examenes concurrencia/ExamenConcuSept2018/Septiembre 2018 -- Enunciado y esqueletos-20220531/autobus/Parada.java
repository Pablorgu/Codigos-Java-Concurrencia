package autobus;


import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.print.attribute.standard.MediaSize.NA;
import javax.print.event.PrintServiceAttributeEvent;

public class Parada {
	private boolean hayautobus = false;
	private ArrayList<Integer> principal = new ArrayList<>();
	private ArrayList<Integer> secundaria = new ArrayList<>();
	private ReentrantLock l = new ReentrantLock();
	private Condition okesperarbus = l.newCondition();
	private Condition okesperaprincipal = l.newCondition();
	private Condition oksalebus = l.newCondition();
	
	
	public Parada(){
		
	}
	/**
	 * El pasajero id llama a este metodo cuando llega a la parada.
	 * Siempre tiene que esperar el siguiente autobus en uno de los
	 * dos grupos de personas que hay en la parada
	 * El metodo devuelve el grupo en el que esta esperando el pasajero
	 * 
	 */
	public int esperoBus(int id) throws InterruptedException{
		l.lock();
		try{
			System.out.println("Llega a la parada el pasajero " + id);
			int grupo;
			if(!hayautobus){
				grupo=1;
				principal.add(id);
			} else{
				grupo = 2;
				secundaria.add(id);
			}
			return grupo; //comentar esta línea
		}finally {
			l.unlock();
		}
	}
	/**
	 * Una vez que ha llegado el autobús, el pasajero id que estaba
	 * esperando en el grupo i se sube al autobus
	 *
	 */
	public void subeAutobus(int id,int i) throws InterruptedException{
		l.lock();
		try{
			while(!principal.contains(id)) {
				okesperaprincipal.await();
			}
			while(!hayautobus) {
				okesperarbus.await();
			}
			i=1;
			System.out.println("El pasajero "+ id + " del grupo " + i + " se ha subido al autobus. Quedan " + principal.size() + " por subirse");
			principal.remove(principal.indexOf(id));
			if(principal.size()==0) {
				oksalebus.signal();
			}
		} finally {
			l.unlock();
		}
	}
	/**
	 * El autobus llama a este metodo cuando llega a la parada
	 * Espera a que se suban todos los viajeros que han llegado antes
	 * que el, y se va
	 * 
	 */
	public void llegoParada() throws InterruptedException{
		l.lock();
		try{
			hayautobus = true;
			System.out.println("							Llega el autobus a la parada");
			okesperarbus.signalAll();
			while(principal.size() > 0) {
				oksalebus.await();
			}
			hayautobus = false;
			System.out.println("							Se va el autobus");
			if(principal.size() == 0){
			principal = secundaria;
			secundaria.clear();
			okesperaprincipal.signalAll();
			}
		} finally {
			l.unlock();
		}
	}
}
