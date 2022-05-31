package agua;


import java.util.concurrent.*;

import javax.swing.plaf.multi.MultiTreeUI;

public class GestorAgua {
	public int nH = 0;
	public int nO = 0;
	public Semaphore Hfalta = new Semaphore(1, true);
	public Semaphore Ofalta = new Semaphore(1, true);
	public Semaphore mutex = new Semaphore(1, true);
	public Semaphore Moleculahecha = new Semaphore(0, true);
	
	
	public void hListo(int id) throws InterruptedException{ 
		Hfalta.acquire();
		mutex.acquire();
		++nH;
		System.out.println("Se ha añadido molecula Hidrogeno " + id + ". Hay " + nH + " moleculas de Hidrogeno y " + nO + " moleculas de Oxigeno");
		if(nH<2) {
			Hfalta.release();
		}
		if(nH == 2 && nO == 1) {
			Moleculahecha.release();
			System.out.println("Se ha creado una molécula");
		}
		mutex.release();
		Moleculahecha.acquire();
		mutex.acquire();
		--nH;
		if(nH+nO == 0) {
			Hfalta.release();
			Ofalta.release();
		} else {
			Moleculahecha.release();
		}
		mutex.release();
	}
	
	public void oListo(int id) throws InterruptedException{ 
		Ofalta.acquire();
		mutex.acquire();
		++nO;
		System.out.println("Se ha añadido molecula Oxigeno " + id + ". Hay " + nH + " moleculas de Hidrogeno y " + nO + " moleculas de Oxigeno");
		if(nO<1) {
			Ofalta.release();
		}
		if(nH == 2 && nO == 1) {
			Moleculahecha.release();
			System.out.println("Se ha creado una molécula");
		}
		mutex.release();
		Moleculahecha.acquire();
		mutex.acquire();
		--nO;
		if(nH+nO == 0) {
			Hfalta.release();
			Ofalta.release();
		} else {
			Moleculahecha.release();
		}
		mutex.release();
	}
}