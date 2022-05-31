Package yield01;
public class Principal {
    

    public static void main(String[] args) throws InterruptedException {
        Thread h11 = new Thread(new MiSegundaHebra('A'));
        Thread h12 = new Thread(new MiSegundaHebra('B'));
        Thread h13= new Thread(new MiSegundaHebra('C'));

        System.out.println(h11.getState());

        h11.setPriority(Thread.MAX_PRIORITY);

        h11.start();
        h12.start();
        h13.start();

        System.out.println(h11.getState());

        h11.join();

        System.out.println(h11.getState()); 

    }
}
