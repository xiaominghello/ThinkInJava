package concurrency.p662daemon.p2;

import net.mindview.util.DaemonThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author shenxiaowei
 * @Date
 */
public class DaemonFromFactory implements Runnable {
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
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
        int count = 10;
        for (int i = 0; i < count; i++) {
            exec.execute(new DaemonFromFactory());
        }
        System.out.println("All Daemon started");
        TimeUnit.MILLISECONDS.sleep(175);
        exec.shutdown();
    }
}
