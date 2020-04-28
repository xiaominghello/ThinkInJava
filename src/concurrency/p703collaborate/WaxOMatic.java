package concurrency.p703collaborate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author shenxiaowei
 * @Date 2020-04-26 22:28
 */
public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}

class Car {
    private boolean waxOn = false;

    public synchronized void waxed() {
        System.out.println("1");
        waxOn = true;
        notifyAll();
        System.out.println("1-2");
    }

    public synchronized void buffed() {
        System.out.println("4");
        waxOn = false;
        notifyAll();
        System.out.println("4-2");
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn) {
            System.out.println("5");
            wait();
            System.out.println("3");
        }
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn) {
            System.out.println("2");
            wait();
            System.out.println("6");
        }
    }
}

class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Wax on!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
            System.out.println("Exiting Wax On task");
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("Wax off!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
            System.out.println("Exiting Wax Off task");
        }
        System.out.println("Ending Wax Off task");
    }
}

