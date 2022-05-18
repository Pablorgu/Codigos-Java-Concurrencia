package pajaritos;

import java.util.concurrent.*;

import javax.management.relation.RelationServiceNotRegisteredException;

public class Nido {
	private int B = 10; // Número máximo de bichos
	private int bichitos; // puede tener de 0 a B bichitos

	Semaphore mutex1 = new Semaphore(1);
	Semaphore mutex2 = new Semaphore(1);
	Semaphore hayBichito = new Semaphore(0);
	Semaphore hayHueco = new Semaphore(1);
	int numBichos = 0;

	public void come(int id) {
		mutex1.acquire();
		hayBichito.acquire();
		--numBichos;
		System.out.println("El bebé " + id + " ha comido un bichito. Quedan " + bichitos);
		if(numBichos==0) hayHueco.release();
		if(numBichos==B) hayBichito.release;
		mutex1.release();

	}

	public void nuevoBichito(int id) {
		// el papa/mama id deja un nuevo bichito en el nido
		mutex2.acquire();
		hayHueco.acquire();
		System.out.println("El papá " + id + " ha añadido un bichito. Hay " + bichitos);
		
	}
}

// CS-Bebe-i: No puede comer del nido si está vacío
// CS-Papa/Mama: No puede poner un bichito en el nido si está lleno
