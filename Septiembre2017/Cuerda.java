package esqueleto;

import java.util.concurrent.Semaphore;

public class Cuerda {
	private int nBabNS = 0;
	private int nBabSN = 0;
	private Semaphore movNS = new Semaphore(1, true);
	private Semaphore movSN = new Semaphore(1, true);
	private Semaphore turno = new Semaphore(0, true);
	private Semaphore mutexNS = new Semaphore(1, true);
	private Semaphore mutexSN = new Semaphore(1, true);

	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public  void entraDireccionNS(int id) throws InterruptedException{
		movNS.acquire();
		mutexNS.acquire();
		nBabNS++;
		if(nBabNS<3) {
			movNS.release();
		}
		if(nBabSN == 3) {
			System.out.println("Se mueve una cuerda de norte a sur");
			turno.release();
		}
		System.out.println("Entra mono con id: " + id + ". Hay " + nBabNS + "en la cuerda direccion Norte a Sur");
		mutexNS.release();
	}
	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón  colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public  void entraDireccionSN(int id) throws InterruptedException{
		movSN.acquire();
		mutexSN.acquire();
		nBabSN++;
		if(nBabSN<3) {
			movSN.release();
		}
		if(nBabSN == 3) {<
			System.out.println("Se mueve una cuerda de sur a norte");
			turno.release();
		}
		System.out.println("Entra mono con id: " + id + ". Hay " + nBabSN + "en la cuerda direccion Sur a Norte");
		mutexSN.release();
	}
	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Norte-Sur
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public  void saleDireccionNS(int id) throws InterruptedException{
		turno.acquire();
		mutexNS.acquire();
		nBabNS--;
		System.out.println("Sale mono con id: " + id + ". Hay " + nBabNS + "en la cuerda direccion Norte a Sur");
		mutexNS.release();
	}
	
	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Sur-Norte
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public  void saleDireccionSN(int id) throws InterruptedException{
		turno.acquire();
		mutexSN.acquire();
		nBabSN--;
		System.out.println("Sale mono con id: " + id + ". Hay " + nBabSN + "en la cuerda direccion Sur a Norte");
		mutexSN.release();
	}	
		
}
