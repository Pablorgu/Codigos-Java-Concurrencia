package pajaritos;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class Nido {
	private int B = 10; // Número máximo de bichos
	private int bichitos = 10; // puede tener de 0 a B bichitos
	private ReentrantLock t = new ReentrantLock();
	private Condition okbichos = t.newCondition();
	private Condition okhueco = t.newCondition();

	public void come(int id) throws InterruptedException {
		t.lock();
		try{
			while(bichitos==0){
				okbichos.await();
			}
			bichitos--;
			System.out.println("El bebé " + id + " ha comido un bichito. Quedan " + bichitos);
			if(bichitos<B) {
				okhueco.signal();
			}
		} finally {
			t.unlock();
		}
	}

	public void nuevoBichito(int id) throws InterruptedException {
		// el papa/mama id deja un nuevo bichito en el nido
		t.lock();
		try{
		while(bichitos==B) {
			okhueco.await();
		}
		++bichitos;
		System.out.println("El papá " + id + " ha añadido un bichito. Hay " + bichitos);
		if(bichitos>0) {
			okbichos.signal();
		}
		} finally {
			t.unlock();
		}
	}
}

// CS-Bebe-i: No puede comer del nido si está vacío
// CS-Papa/Mama: No puede poner un bichito en el nido si está lleno
