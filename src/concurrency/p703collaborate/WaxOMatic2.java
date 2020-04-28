package concurrency.p703collaborate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Administrator
 * @Date 2020/4/28 9:15
 */
public class WaxOMatic2 {
    public static void main(String[] args) throws InterruptedException {
        Car2 car2 = new Car2();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff2(car2));
        exec.execute(new WaxOn2(car2));
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
    }
}

class Car2 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean waxOn = false;

    public void waxed() {
        lock.lock();
        try {
            System.out.println("1");
            waxOn = true;
            condition.signalAll();
            System.out.println("1-2");
        } finally {
            lock.unlock();
        }
    }

    public void buffed() {
        lock.lock();
        try {
            System.out.println("4");
            waxOn = false;
            condition.signalAll();
            System.out.println("4-2");
        } finally {
            lock.unlock();
        }
    }

    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        try {
            while (!waxOn) {
                System.out.println("5");
                condition.await();
                System.out.println("3");
            }
        } finally {
            lock.unlock();
        }
    }

    public void waitForBuffing() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn) {
                System.out.println("2");
                condition.await();
                System.out.println("6");
            }
        } finally {
            lock.unlock();
        }
    }
}

class WaxOn2 implements Runnable {
    private Car2 car2;

    public WaxOn2(Car2 car2) {
        this.car2 = car2;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Wax on");
                TimeUnit.MILLISECONDS.sleep(200);
                car2.waxed();
                car2.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOff2 implements Runnable {
    private Car2 car2;

    public WaxOff2(Car2 car2) {
        this.car2 = car2;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car2.waitForWaxing();
                System.out.println("Wax off");
                TimeUnit.MILLISECONDS.sleep(200);
                car2.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off Task");
    }
}

