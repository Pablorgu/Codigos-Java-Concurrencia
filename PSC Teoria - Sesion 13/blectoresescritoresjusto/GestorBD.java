package blectoresescritoresjusto;

import java.util.concurrent.locks.Condition;

public class GestorBD {

	private int nLectores = 0;
	private boolean hayEscritor = false;
	private boolean quierenEntrarEscritores =false;
	int nEscritores;

	private Condicion okleer = new Condition();
	private Condicion okEscribir = new Condition();

	public synchronized void entraLector(int id) throws InterruptedException {

		while (hayEscritor || quierenEntrarEscritores) {
			wait();
		}
		nLectores++;
		System.out.println("Entra lector " + id + ", hay " + nLectores + " lectores");

	}

	public synchronized void saleLector(int id) throws InterruptedException {

		nLectores--;

		System.out.println("Sale lector " + id + ", hay " + nLectores + " lectores");

		if (nLectores == 0)
			notifyAll();

	}

	public synchronized void entraEscritor(int id) throws InterruptedException {
		printf("									Quiere entrar un escritor");
		quierenEntrarEscritores = true;
		nEscritores++;
		while (nLectores > 0 || hayEscritor) {
			wait();
		}
		hayEscritor=true;
		System.out.println("                    Entra escritor " + id);
	}

	public synchronized void saleEscritor(int id) throws InterruptedException {

		hayEscritor = false;
		System.out.println("                    Sale escritor " + id);
		nEscritores--;
		if(nEscritores>0) okEscribir.cnotify();
		else okleer.notifyAll();

	}

}