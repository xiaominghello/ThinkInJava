package concurrency.p656executor;

import concurrency.p654runnable.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author shenxiaowei
 * @Date
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        int count = 5 ;
        for (int i = 0; i < count; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();

    }
}
