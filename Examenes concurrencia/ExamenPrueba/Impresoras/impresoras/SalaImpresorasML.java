package impresoras;

import java.lang.reflect.Array;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import impresoras.SalaImpresoras;

public class SalaImpresorasML implements SalaImpresoras{

    private int  NI; // numero de impresoras
    private int nClientesOcupados=0;
    ArrayList<Boolean> impresoras = new ArrayList<>();
    ArrayList<Integer> Cliente = new ArrayList<>();
    private ReentrantLock l = new ReentrantLock();
    private Condition okImpresoras = l.newCondition();

    public SalaImpresorasML(int n){
        this.NI = n;
        for(int i=0; i<NI; ++i) {
            impresoras.add(false);
        }
    }


    public int quieroImpresora(int id) throws InterruptedException{
        l.lock();
        try {
            int impresoraocupada= -1;
            Cliente.add(id);
            System.out.println("Se ha añadido al cliente " + id + " a la cola de espera.");
            while(nClientesOcupados >= NI && !(Cliente.get(nClientesOcupados-1)==id)) {
                okImpresoras.await();
            }
            nClientesOcupados++;
            for (int i = 0; i<nClientesOcupados ; i++) {
                if(impresoras.get(i)==false && impresoraocupada == -1) {
                    impresoras.set(i, true);
                    impresoraocupada = i;
                }
            }
            System.out.println("El cliente "+ id + " ha ocupado la impresora " + impresoraocupada);
            return impresoraocupada;
        }finally {
            l.unlock();
        }
    }

    public void devuelvoImpresora(int id, int n) throws InterruptedException{
        l.lock();
        try{
            int poscliente=-1;
            for(int i=0; i < nClientesOcupados; i++) {
                if (Cliente.get(i) == id){
                    poscliente=i;
                }
            }
            impresoras.set(n, false);
            nClientesOcupados--;
            Cliente.remove(poscliente);
            System.out.println("        El cliente "+ id + " abandona la impresora "+ n + ". Hay "+ nClientesOcupados + "impresoras ocupadas.");
            okImpresoras.signalAll();     
        }finally {
            l.unlock();
        }
    }
}
