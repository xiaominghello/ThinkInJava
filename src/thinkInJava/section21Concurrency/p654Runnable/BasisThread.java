package thinkInJava.section21Concurrency.p654Runnable;

public class BasisThread {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println(Thread.currentThread().getName() + ": Waiting for LiftOff");
    }
}
