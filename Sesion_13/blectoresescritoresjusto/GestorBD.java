package blectoresescritoresjusto;

public class GestorBD {

	private int nLectores = 0;
	private boolean hayEscritor = false;
	private boolean quierenEntrarEscritores =false;

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
			notify();

	}

	public synchronized void entraEscritor(int id) throws InterruptedException {
		printf("									Quiere entrar un escritor");
		while (nLectores > 0 || hayEscritor) {
			quierenEntrarEscritores = true;
			wait();
		}

		hayEscritor = true;

		System.out.println("                    Entra escritor " + id);
	}

	public synchronized void saleEscritor(int id) throws InterruptedException {

		hayEscritor = false;
		System.out.println("                    Sale escritor " + id);
		notifyAll();

	}

}