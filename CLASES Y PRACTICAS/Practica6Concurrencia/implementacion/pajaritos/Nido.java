package pajaritos;

import java.util.concurrent.*;

public class Nido {
	private int B = 10; // Número máximo de bichos
	private int bichitos= 10; // puede tener de 0 a B bichitos
	Semaphore mutex = new Semaphore(1, true);
	Semaphore hayBichos = new Semaphore(1, true);
	Semaphore hayHueco = new Semaphore(0, true);

	public void come(int id) throws InterruptedException {
		hayBichos.acquire();
		mutex.acquire();
		--bichitos;
		System.out.println("El bebé " + id + " ha comido un bichito. Quedan " + bichitos);
		if(bichitos > 0) {
			hayBichos.release();
		} 
		if(bichitos==9){
			hayHueco.release();
		}
		mutex.release();
	}

	public void nuevoBichito(int id) throws InterruptedException {
		// el papa/mama id deja un nuevo bichito en el nido
		hayHueco.acquire();
		mutex.acquire();
		++bichitos;
		System.out.println("El papá " + id + " ha añadido un bichito. Hay " + bichitos);
		if(bichitos < B) {
			hayHueco.release();
		}
		if(bichitos==1) {
			hayBichos.release();
		}
		mutex.release();
	}
}

// CS-Bebe-i: No puede comer del nido si está vacío
// CS-Papa/Mama: No puede poner un bichito en el nido si está lleno
