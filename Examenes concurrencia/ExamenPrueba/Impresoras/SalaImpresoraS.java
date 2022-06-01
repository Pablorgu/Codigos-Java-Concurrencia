package impresoras;

    private int  NI; // numero de impresoras
    private int nClientesOcupados=0;
    ArrayList<Boolean> impresoras = new ArrayList<>();

public interface SalaImpresorasS{
    public int quieroImpresora(int id) throws InterruptedException{
        
        System.out.println("Cliente "+ id + " quiere impresora");

        System.out.println("Cliente " + id + "coge impresora" + idimpr + "quedan libres" + impresoralibres);

    }

    public void devuelvoImpresora(int id, int n) throws InterruptedException{
        System.out.println("Cliente "+ id +" devuelve la impresora" + n + "quedan libres 1");
    }
}