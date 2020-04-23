package thinkInJava.section21Concurrency.p654Runnable;

public class MoreBasicThread {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
            System.out.println(Thread.currentThread().getName() + ": Waiting for LiftOff");
        }
    }
}
