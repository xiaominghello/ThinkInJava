package concurrency.p719deaklock;

/**
 * @Author shenxiaowei
 * @Date 2020-04-28 22:12
 */
public class Chopstick {
    private boolean taken = false;
    public synchronized void take(String msg) throws InterruptedException {
        while (taken) {
            System.out.println(Thread.currentThread().getName() + " take fail " + msg );
            wait();
        }
        System.out.println(Thread.currentThread().getName() + " take success " + msg);
        taken = true;
    }

    public synchronized void drop(String msg) {
        System.out.println(Thread.currentThread().getName() + " drop " + msg);
        taken = false;
        notifyAll();
    }
}
