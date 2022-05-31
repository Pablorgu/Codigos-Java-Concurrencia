package Fibonacci;

public class Fibonacci implements Runnable{
    private int id;
    private int res;
    private int ant;
    public Fibonacci(int n) {
        this.id = n;
    }

    public int getAnterior(){
        return ant;
    }

    public int getResultado(){
        return res;
    }

    public void run() {
        if (id==0) {res=0} 
        else if(id==1) {res=1;}
        else {
            Fibonacci f1 = new Fibonacci(id-2);
            Fibonacci f2 = new Fibonacci(id-1);
            Thread h1 = new Thread(f1);
            Thread h2 = new Thread(f2);

            h1.run();
            h2.run();
        }
    }
}
