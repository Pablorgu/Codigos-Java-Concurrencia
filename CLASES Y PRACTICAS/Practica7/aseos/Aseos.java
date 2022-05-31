package aseos;

import java.util.concurrent.Semaphore;

public class Aseos {

	Semaphore Limpieza = new Semaphore(1, true);
	Semaphore mutex = new Semaphore(1, true);
	int nClientes = 0;
	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza está
	 * trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza está trabajando
	 * o
	 * está esperando para poder limpiar los aseos
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void entroAseo(int id) throws InterruptedException {
		mutex.acquire();
		nClientes++;
		if(nClientes == 1) {
			Limpieza.acquire();
		}
		System.out.println("El cliente " + id + " ha entrado en el baño."
				+ "Clientes en el aseo: " + nClientes);
		mutex.release();
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void salgoAseo(int id) throws InterruptedException {
		mutex.acquire();
		nClientes--;
		System.out.println("El cliente " + id + " ha salido del baño."
				+ "Clientes en el aseo: " + nClientes);
		if(nClientes==0) {
			Limpieza.release();
		}
		mutex.release();

	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos
	 * CS: El equipo de trabajo está solo en los aseos, es decir, espera hasta que
	 * no
	 * haya ningún cliente.
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void entraEquipoLimpieza() throws InterruptedException {
		Limpieza.acquire();
		System.out.println("El equipo de limpieza está trabajando.");
	}

	/**
	 * Utilizado por el Equipo de Limpieza cuando sale de los aseos
	 * 
	 * @throws InterruptedException
	 * 
	 * 
	 */
	public void saleEquipoLimpieza() throws InterruptedException {
		System.out.println("El equipo de limpieza ha terminado.");
		Limpieza.release();

	}
}
