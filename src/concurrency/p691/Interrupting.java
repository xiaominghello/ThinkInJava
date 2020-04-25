package concurrency.p691;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author shenxiaowei
 * @Date 2020-04-25 22:52
 */
public class Interrupting {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    /**
     * 通过exec.sumbit() 获得任务的上下文，从而可以调用cancel()来中断任务
     * 发现可以中断sleep()
     * 但是不能中断 试图获取synchronized锁的线程，试图执行I/O操作的线程
     * 这意味着在创建执行I/O任务时，I/O具有锁住多线程程序的潜在可能，特别时对于基于Web的程序，这更关乎利害
     * 对于这类问题，有一个略显笨拙但确实行之有效的解决方案，即关闭任务在其上发生阻塞的底层资源
     *
     * @param r
     * @throws InterruptedException
     */
    static void test(Runnable r) throws InterruptedException {
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        f.cancel(true);
        System.out.println("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlocker());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System.exit(0)");
        // since last 2 interrupts failed
        System.exit(0);
    }
}

/**
 * 可中断的阻塞示例
 */
class SleepBlocker implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Interrupted from sleep");
        }
        System.out.println("Exiting SleepBlocked.run()");
    }
}

/**
 * 不可中断的阻塞示例
 */
class IOBlocked implements Runnable {
    private InputStream in;

    public IOBlocked(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for read()");
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted from blocked I/O");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exiting IOBlocked.run()");
    }
}

/**
 * 不可中断的阻塞示例
 */
class SynchronizedBlocked implements Runnable {
    public synchronized void f() {
        while (true) {
            // never release lock
            Thread.yield();
        }
    }

    public SynchronizedBlocked() {
        new Thread() {
            @Override
            public void run() {
                // lock acquired by this thread
                f();
            }
        }.start();
    }

    @Override
    public void run() {
        System.out.println("Trying to call f()");
        f();
        System.out.println("Exiting SynchronizedBlocked.run()");
    }
}
