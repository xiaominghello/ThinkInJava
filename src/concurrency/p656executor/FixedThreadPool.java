package concurrency.p656executor;

import concurrency.p654runnable.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author shenxiaowei
 * @Date
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        int count = 5;
        for (int i = 0; i < count; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
