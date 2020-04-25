package concurrency.p659sleep;

import concurrency.p654runnable.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author shenxiaowei
 * @Date
 */
public class SleepTask extends LiftOff {
    @Override
    public void run() {
        try {
            while (countDown-- > 0) {
                System.out.println(Thread.currentThread().getName() + ": " + status());
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        int count = 5;
        for (int i = 0; i < count; i++) {
            exec.execute(new SleepTask());
        }
        exec.shutdown();
    }
}
