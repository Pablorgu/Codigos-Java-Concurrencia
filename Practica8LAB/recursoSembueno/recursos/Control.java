package recursos;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Control {
	private int NUM;// numero total de recursos
	private int numRec;
	private int numAct;
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore mutex2 = new Semaphore(1, true);
	private Semaphore quiereRecurso = new Semaphore(1, true);
	private Semaphore hayRecursoSuf = new Semaphore(1, true);
	

	public Control(int num) {
		this.NUM = num;
		this.numRec = num;
	}

	public void qRecursos(int id, int num) throws InterruptedException {
		mutex.acquire();
		System.out.println("Proceso " + id + " pide " + num + " recursos. Quedan: " + numRec);
		mutex.release();
		quiereRecurso.acquire();
		mutex.acquire();
		numAct=num;
		mutex.release();
		hayRecursoSuf.acquire();
		quiereRecurso.release();
		mutex.acquire();
		numRec-=num;
		System.out.println("										El proceso " + id + " ha cogido " + num + " recursos. Quedan: " + numRec);
		if(numAct < numRec) {
			hayRecursoSuf.release();
		}
		mutex.release();
	}
	
	public void libRecursos(int id, int num) throws InterruptedException{
		mutex.acquire();
		numRec += num;
		System.out.println("El proceso " + id + " ha liberado " + num + " recursos. Recursos totales: " + numRec);
		if(numAct < numRec) {
			hayRecursoSuf.release();
		}
		mutex.release();
		}
}
// CS-1: un proceso tiene que esperar su turno para coger los recursos
// CS-2: cuando es su turno el proceso debe esperar hasta haya recursos
// suficiente
// para Ã©l