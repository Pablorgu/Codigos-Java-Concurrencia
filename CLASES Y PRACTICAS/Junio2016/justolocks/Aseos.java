package aseos;


import java.util.LongSummaryStatistics;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantLock;
public class Aseos {
	private int nClientes = 0;
	private boolean limpiando = false;
	private boolean quierenlimpiar = false;
	private ReentrantLock l = new ReentrantLock();
	private Condition okLimpiar = l.newCondition();
	private Condition okEntrar = l.newCondition();
	
	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza est� trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando o
	 * est� esperando para poder limpiar los aseos
	 * 
	 */
	public void entroAseo(int id){
		
		l.lock();

		try{
			while(limpiando || quierenlimpiar) {
				okEntrar.await();
			}
			nCliente++;
			System.out.println("El cliente " + id + " ha entrado en el baño." + " Clientes en el aseo: " + nClientes);
		}finally {
			l.unlock();
		}


	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * 
	 */
	public void salgoAseo(int id){
		
		l.lock();

		try{
			nClientes--;
			System.out.println("El cliente " + id + " ha salido del baño." + " Clientes en el aseo: " + nClientes);
			if(nClientes == 0) {
				okLimpiar.signal();
			}
		}finally {
			l.unlock();
		}

	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que no
	 * haya ning�n cliente.
	 * 
	 */
    public void entraEquipoLimpieza(){
	
		l.lock();

		try{
			quierenlimpiar = true;
			System.out.println("El equipo de limpieza quiere limpiar");
			while(nClientes > 0 || limpiando) {
				okLimpiar.await();
			}
			quierenlimpiar = false;
			limpiando = true;
			System.out.prinln("										Entra el equipo de limpieza")
		}finally {
			l.unlock();
		}

	}
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
	 * 
	 * 
	 */
    public void saleEquipoLimpieza(){
    	l.lock();

		try{
			System.out.println("										El equipo de limpieza ha terminado");
			limpiando = false;
			okEntrar.signalAll();
		}finally {
			l.unlock();
		}
	}
}
