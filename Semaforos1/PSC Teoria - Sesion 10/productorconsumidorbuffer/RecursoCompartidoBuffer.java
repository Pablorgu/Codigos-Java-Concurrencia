package productorconsumidorbuffer;

import java.util.concurrent.Semaphore;

//Condición Productor:
//  No puedo almacenar mientras el buffer no tenga sitio.  Un semáforo haySitio se puede encargar de sincronizar cuando hay hueco para almacenar.
//Condición Consumidor:
// No puedo extraer hasta que no hay datos. Un semáforo hayDato se puede encargar de sincronizar cuando hay dato.
//Se necesita un índice indInsertar para conocer dónde se intertará el siguiente elemento.
//Se necesita un índice indExtraer para conocer dónde se extraerá el siguiente elemento.
//Además, el buffer va a ser tratado de forma circular, una variable tam almacenará el tamaño del buffer para actualizar correctamente los índices.

public class RecursoCompartidoBuffer {
    private int[] recurso;
    Semaphore haySitio;
    Semaphore hayDato;
    int indInsertar;
    int indExtraer;
    int tam;

    public RecursoCompartidoBuffer(int tam, Semaphore haySitio, Semaphore hayDato) {
        indInsertar = 0;
        indExtraer = 0;
        this.hayDato = hayDato;
        this.haySitio = haySitio;
        this.tam = tam;
        recurso = new int[tam];

    }

    public int extraer() {
        int datoLeido;
        try{hayDato.acquire();}
        catch (InterruptedException e) <{e.printStackTrace();}
        indExtraer--;
        haySitio.release();
        datoLeido = recurso[indExtraer];
        System.out.println("Extraído " + datoLeido);

        return datoLeido;
    }

    public void almacenar(int r) {
        try{
            haySitio.acquire();
        } catch (InterruptedException e) {e.printStackTrace();}
        recurso[indInsertar] = r;
        indInsertar++;
        if(indInsertar=tam) {
            indInsertar = 0;
        }
        System.out.println("Almacenado " + r);
        hayDato.release();

    }

}
