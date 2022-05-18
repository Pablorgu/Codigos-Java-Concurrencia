package Fibonacci;

public class Principal {
public static void main(String[] args) {
    Fibonacci f1 = new Fibonacci(1);
    Thread ignacio = new Thread(f1);


    try {
        ignacio.join();
    } catch (Exception e) {
        //TODO: handle exception
    }
}