package concurrency.p662daemon.p1;

import java.util.concurrent.TimeUnit;

/**
 * @Author shenxiaowei
 * @Date
 */
public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("sleep() interrupted");
        }
    }

    public static void main(String[] args) throws Exception {
        int count = 10;
        for (int i = 0; i < count; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All Daemon started");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
