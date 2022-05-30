package recursos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Control {
	private int NUM;// numero total de recursos
	private int numRec;
	private boolean hayEspera = false;
	ArrayList<Integer> list = new ArrayList<>();
	private ReentrantLock l = new ReentrantLock();
	private Condition okRecurso = l.newCondition();
	private Condition okPrimero = l.newCondition();
	

	public Control(int num) {
		this.NUM = num;
		this.numRec = num;
	}

	public void qRecursos(int id, int num) throws InterruptedException {
		l.lock();
		try{
			System.out.println("Proceso " + id + " pide " + num + " recursos. Quedan: " + numRec);
			list.add(id);
			while(hayEspera) {
				okRecurso.await();
			}
			hayEspera = true;
			list.remove(1);
			while(num > numRec) {
				okPrimero.await();
			}
			hayEspera = false;
			numRec-=num;
			System.out.println("										El proceso " + id + " ha cogido " + num + " recursos. Quedan: " + numRec);
		} finally {
			l.unlock();
		}
	}

	public void libRecursos(int id, int num) {
		l.lock();
		try{
			numRec+= num;
			System.out.println("El proceso " + id + " ha liberado " + num + " recursos. Recursos totales: " + numRec);
			okPrimero.notify();
		} finally {
			l.unlock();
		}
	}
}
// CS-1: un proceso tiene que esperar su turno para coger los recursos
// CS-2: cuando es su turno el proceso debe esperar hasta haya recursos
// suficiente
// para él
