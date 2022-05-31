package esqueleto;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Cuerda {
		private int nBab = 0;
		private boolean cuerdaocupadaNS = false; 
		private boolean cuerdaocupadaSN = false; 
		private ReentrantLock l = new ReentrantLock();
		private Condition okEntrar = l.newCondition();
		private Condition okSalir = l.newCondition();

	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public  void entraDireccionNS(int id) throws InterruptedException{
		l.lock();
			try {
			while(nBab==3) {
				okEntrar.await();
			}
			++nBab;
			System.out.println("Entra mono con id: " + id + " en la cuerda direccion Norte a Sur. Hay " + nBab + " babuinos en la cuerda.");
		}finally{
			l.unlock();
		}
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
		l.lock();
		try{
			while(nBab==3) {
				okEntrar.await();
			}
			++nBab;
			System.out.println("Entra mono con id: " + id + " en la cuerda direccion Sur a Norte. Hay " + nBab + " babuinos en la cuerda.");
		}finally {
			l.unlock();
		}
	}
	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Norte-Sur
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public  void saleDireccionNS(int id) throws InterruptedException{
		l.lock();
		try{
			while(cuerdaocupadaSN) {
				okSalir.await();
			}
			cuerdaocupadaNS = true;
			--nBab;
			System.out.println("				Sale mono con id: " + id + " direccion Norte a Sur. Hay "+ nBab + " babuinos en la cuerda.");
			cuerdaocupadaNS = false;
			okSalir.signalAll();
			if(nBab<3) {
				okEntrar.signal();
			}
		}finally {
			l.unlock();
		}
	}
	
	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Sur-Norte
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public  void saleDireccionSN(int id) throws InterruptedException{
		l.lock();
		try{
			while(cuerdaocupadaNS) {
				okSalir.await();
			}
			cuerdaocupadaSN = true;
			--nBab;
			System.out.println("				Sale mono con id: " + id + " direccion Sur a Norte. Hay "+ nBab + " babuinos en la cuerda.");
			cuerdaocupadaSN = false;
			okSalir.signalAll();
			if(nBab<3)
			okEntrar.signal();
		} finally {
			l.unlock();
		}
	}	
		
}
