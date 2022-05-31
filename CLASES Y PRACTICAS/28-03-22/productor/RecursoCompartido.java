package join02;

public class RecursoCompartido {
    private int recurso;

    private volatile boolean hayDato = false;

    public int leer() {
        while(!haydato) {
            Thread.sleep(100);
        }

        int a = recurso;
        System.out.println("")
    }

    public void escribir(int recurso) {
        while (hayDato);
        //haydato==FALSE
        recurso = r;
        System.out.prinln("han escrito" + r);
        hayDato = true;
    }
}