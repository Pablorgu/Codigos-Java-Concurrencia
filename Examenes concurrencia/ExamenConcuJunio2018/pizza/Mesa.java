package pizza;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Mesa {
	private int trozosPizza = 8; //trozos maximoç
	private boolean esperandoPizza = false;
	private boolean pizzaPagada = false;
	private ReentrantLock l = new ReentrantLock();
	private Condition okpizza = l.newCondition();
	private Condition okpagada = l.newCondition();
	private Condition okpizzero = l.newCondition();
	private Condition okPagar = l.newCondition();



	
	
	/**
	 * 
	 * @param id
	 * El estudiante id quiere una ración de pizza. 
	 * Si hay una ración la coge y sigue estudiando.
	 * Si no hay y es el primero que se da cuenta de que la mesa está vacía
	 * llama al pizzero y
	 * espera hasta que traiga una nueva pizza. Cuando el pizzero trae la pizza
	 * espera hasta que el estudiante que le ha llamado le pague.
	 * Si no hay pizza y no es el primer que se da cuenta de que la mesa está vacía
	 * espera hasta que haya un trozo para él.
	 * @throws InterruptedException 
	 * 
	 */
	public void nuevaRacion(int id) throws InterruptedException{
		l.lock();
		try{
			System.out.println("Estudiante "+ id + " quiere pizza");
			if(trozosPizza > 0) {
				--trozosPizza;
				System.out.println("El estudiante "+ id + " ha cogido un trozo de pizza. Hay " + trozosPizza + " de pizza.");
			}else if(esperandoPizza){
				//ya hay una pizza pedida
				while(esperandoPizza) {
				okpagada.await();
				}
				--trozosPizza;
				System.out.println("El estudiante "+ id + " ha cogido un trozo de pizza. Hay " + trozosPizza + " de pizza.");
			} else {
				System.out.println("El estudiante "+ id + " ha pedido una pizza nueva");
				esperandoPizza = true;
				okpizzero.signal();
				while(esperandoPizza) {
					okpizza.await();
				}
				--trozosPizza;
				System.out.println("El estudiante "+ id + " ha cogido un trozo de pizza despues de pagarla. Hay " + trozosPizza + " de pizza.");
				okpagada.signalAll();
			}
		}finally {
			l.unlock();
		}
	}


	/**
	 * El pizzero entrega la pizza y espera hasta que le paguen para irse
	 * @throws InterruptedException 
	 */
	public void nuevoCliente() throws InterruptedException{
		l.lock();
		try{
			while(!esperandoPizza) {
				okpizzero.await();
			}
			System.out.println("								El pizzero está preparando una pizza");
			System.out.println("								El pizzero lleva la pizza");
			pizzaPagada= true;
			okPagar.signal();
		}finally {
			l.unlock();
		}
	}
	

/**
	 * El pizzero espera hasta que algún cliente le llama para hacer una pizza y
	 * llevársela a domicilio
	 * @throws InterruptedException 
	 */
	public void nuevaPizza() throws InterruptedException{
		l.lock();
		try{
			while(!pizzaPagada) {
				okPagar.await();
			}
			System.out.println("Se ha pagado la pizza");
			trozosPizza = 8;
			esperandoPizza=false;
			pizzaPagada=false;
			okpizza.signal();
		}finally {
			l.unlock();
		}
	}
}
