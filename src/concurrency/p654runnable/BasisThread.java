package concurrency.p654runnable;

/**
 * @Author shenxiaowei
 * @Date
 */
public class BasisThread {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println(Thread.currentThread().getName() + ": Waiting for LiftOff");
    }
}
