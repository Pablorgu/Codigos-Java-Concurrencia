package esqueleto;

import java.sql.SQLClientInfoException;
import java.util.concurrent.Semaphore;

public class Cuerda {
	private int nBab = 0;
	private Semaphore lleno = new Semaphore(1, true);
	private Semaphore turno = new Semaphore(1, true);
	private Semaphore mutex = new Semaphore(1, true);

	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public  void entraDireccionNS(int id) throws InterruptedException{
		lleno.acquire();
		mutex.acquire();
		nBab++;
		System.out.println("Entra mono con id: " + id + " en la cuerda direccion Norte a Sur. Hay " + nBab + " babuinos en la cuerda.");
		if(nBab<3) {
			lleno.release();
		}
		mutex.release();
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
		lleno.acquire();
		mutex.acquire();
		nBab++;
		System.out.println("Entra mono con id: " + id + " en la cuerda direccion Sur a Norte. Hay " + nBab + " babuinos en la cuerda.");
		if(nBab<3) {
			lleno.release();
		}
		mutex.release();

	}
	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Norte-Sur
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public  void saleDireccionNS(int id) throws InterruptedException{
		turno.acquire();
		mutex.acquire();
		nBab--;
		System.out.println("				Sale mono con id: " + id + " direccion Norte a Sur. Hay "+ nBab + " babuinos en la cuerda.");
		if(nBab<3) {
			lleno.release();
		}
		turno.release();
		mutex.release();
	}
	
	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Sur-Norte
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public  void saleDireccionSN(int id) throws InterruptedException{
		turno.acquire();
		mutex.acquire();
		nBab--;
		System.out.println("				Sale mono con id: " + id + " direccion Sur a Norte. Hay "+ nBab + " babuinos en la cuerda.");
		if(nBab<3) {
			lleno.release();
		}
		turno.release();
		mutex.release();

	}	
		
}
