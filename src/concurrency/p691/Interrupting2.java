package concurrency.p691;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock.lockInterruptibly() Some other thread interrupts the current thread, and interruption of lock acquisition is supported.
 *
 * @Author Administrator
 * @Date 2020/4/26 16:33
 */
public class Interrupting2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }
}

class BlockedMutex {
    private final Lock lock = new ReentrantLock();

    public BlockedMutex() {
        // Acquired it right away.
        // to demonstrate interruption of a task blocker on a ReentrantLock
        lock.lock();
    }

    public void f() {
        try {
//            Acquires the lock unless the current thread is interrupted.
            lock.lockInterruptibly();
            System.out.println("lock acquired in f()");
        } catch (InterruptedException e) {
//            if the current thread is interrupted while acquiring the lock (and interruption of lock acquisition is supported)
//            e.printStackTrace();
            System.out.println("Interrupted from lock acquisition in f()");
        }
    }
}

class Blocked2 implements Runnable {
    BlockedMutex blockedMutex = new BlockedMutex();

    @Override
    public void run() {
        System.out.println("Waiting for f() in BlockenMuted");
        blockedMutex.f();
        System.out.println("Broken out of blocked call");
    }
}