package barca;

public class Barca extends Thread {

	private static final int C = 4;
		private int pasAndroid = 0;
		private int pasIphone = 0;
		private boolean subir = true;
	/**
	 * Un estudiante con m�vil android llama a este m�todo cuando quiere cruzar el
	 * r�o. Debe esperarse a montarse en la barca de forma segura, y a llegar a la
	 * otra orilla del antes de salir del m�todo
	 * 
	 * @param id
	 *           del estudiante android que llama al m�todo
	 * @throws InterruptedException
	 */

	public synchronized void android(int id) throws InterruptedException {
		System.out.println("El android " + id + " se sube a la barca.");

			System.out.println("El barco se ha movido con " + pasAndroid + " pasajeros de Android y " + pasIphone 
			+ " de Iphones");
		
		// System.out.println("Llegamos a la orilla.");

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

	public synchronized void iphone(int id) throws InterruptedException {
	
		System.out.println("El iphone " + id + " se sube a la barca.");
		
			System.out.println("El barco se ha movido con " + pasAndroid + " pasajeros de Android y " + pasIphone 
		
		// System.out.println("Llegamos a la orilla.");

	}

}

// CS-IPhone: no puede subirse a la barca hasta que est� en modo subida, haya
// sitio
// y no sea peligroso

// CS-Android: no puede subirse a la barca hasta que est� en modo subida, haya
// sitio
// y no sea peligroso

// CS-Todos: no pueden bajarse de la barca hasta que haya terminado el viaje