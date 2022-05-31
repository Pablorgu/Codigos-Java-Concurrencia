package barca;

import java.util.concurrent.Semaphore;

import javax.print.event.PrintJobListener;
import javax.swing.plaf.multi.MultiTextUI;

public class Barca extends Thread {

	private static final int C = 4;
	private int numAndroid = 0;
	private int numIphone = 0;
	Semaphore mutex = new Semaphore(1, true);
	Semaphore entraAndroid = new Semaphore(0, true);
	Semaphore saleAndroid = new Semaphore(0, true);
	Semaphore entraIphone = new Semaphore(0, true);
	Semaphore saleIphone = new Semaphore(0, true);
	Semaphore huecolibre =  new Semaphore(1, true);
	/**
	 * Un estudiante con m�vil android llama a este m�todo cuando quiere cruzar el
	 * r�o. Debe esperarse a montarse en la barca de forma segura, y a llegar a la
	 * otra orilla del antes de salir del m�todo
	 * 
	 * @param id
	 *           del estudiante android que llama al m�todo
	 * @throws InterruptedException
	 */

	public void android(int id) throws InterruptedException {
		huecolibre.acquire();
		mutex.acquire();
		 if(numIphone != 3 && (numAndroid ==2 && numIphone == 1)); {
			entraAndroid.release();
		}
		mutex.release();

		entraAndroid.acquire();
		mutex.acquire();
		numAndroid++;
		System.out.println("El android " + id + " se sube a la barca.");
		if(numAndroid + numIphone == 4) {
			System.out.println("El barco ha zarpado con  " + numAndroid + " Androids y " + numIphone + " Iphones");
			saleAndroid.release();
		}
		mutex.release();

		saleAndroid.acquire();
		mutex.acquire();
		numAndroid--;
		if(numAndroid + numIphone == 0) {
			huecolibre.release();
		}
		mutex.release();
	}

	/**
	 * Un estudiante con m�vil android llama a este m�todo cuando quiere cruzar el
	 * r�o. Debe esperarse a montarse en la barca de forma segura, y a llegar a la
	 * otra orilla del antes de salir del m�todo
	 * 
	 * @param id
	 *           del estudiante android que llama al m�todo
	 * @throws InterruptedException
	 */

	public void iphone(int id) throws InterruptedException {
		huecolibre.acquire();
		mutex.acquire();
		 if(numAndroid != 3 && (numIphone ==2 && numAndroid == 1)); {
			entraIphone.release();
		}
		huecolibre.release();
		mutex.release();

		entraIphone.acquire();
		mutex.acquire();
		numIphone++;
		System.out.println("El android " + id + " se sube a la barca.");
		if(numAndroid + numIphone == 4) {
			System.out.println("El barco ha zarpado con  " + numAndroid + " Androids y " + numIphone + " Iphones");
			saleIphone.release();
		}
		mutex.release();

		saleIphone.acquire();
		mutex.acquire();
		numIphone--;
		if(numAndroid + numIphone == 0) {
			huecolibre.release();
		}
		mutex.release();
	}

}

// CS-IPhone: no puede subirse a la barca hasta que est� en modo subida, haya
// sitio
// y no sea peligroso

// CS-Android: no puede subirse a la barca hasta que est� en modo subida, haya
// sitio
// y no sea peligroso

// CS-Todos: no pueden bajarse de la barca hasta que haya terminado el viaje