package impresoras;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SalaImpresorasS implements SalaImpresoras{

    private int  NI; // numero de impresoras
    private int nImpresorasocupadas=0; //numero de impresoras ocupadas realmente
    private ArrayList<Boolean> impresoras = new ArrayList<>();
    Semaphore mutex = new Semaphore(1, true);
    Semaphore impresorasllenas = new Semaphore(1,true);

    public SalaImpresorasS(int n) {
        this.NI = n;
        for(int i = 0; i<NI; ++i) {
            impresoras.add(false);
        }
    }

    public int quieroImpresora(int id) throws InterruptedException{
        System.out.println("Cliente "+ id + " quiere impresora");
        impresorasllenas.acquire();
        mutex.acquire();
        int idimpresora = -1;
        for(int i = 0; i < NI ; ++i) {
            if(impresoras.get(i) == false && idimpresora == -1) {
                impresoras.set(i, true);
                idimpresora = i;
            }
        }
        nImpresorasocupadas++;
        System.out.println("                    Cliente " + id + " coge impresora " + idimpresora + " quedan libres " + (NI-nImpresorasocupadas));
        if(nImpresorasocupadas < NI) {
            impresorasllenas.release();
        }
        mutex.release();
        return idimpresora;
    }

    public void devuelvoImpresora(int id, int n) throws InterruptedException{
        mutex.acquire();
        impresoras.set(n, false);
        nImpresorasocupadas--;
        System.out.println("Cliente "+ id +" devuelve la impresora " + n + " quedan libres " + (NI-nImpresorasocupadas));
        if(nImpresorasocupadas == 2) {
            impresorasllenas.release();
        }
        mutex.release();
    }
}