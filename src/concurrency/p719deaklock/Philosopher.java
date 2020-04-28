package concurrency.p719deaklock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author shenxiaowei
 * @Date 2020-04-28 22:18
 */
public class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random random = new Random(47);
    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
    }

    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " " + "thinking");
                pause();
                right.take("right");
                left.take("left");
                System.out.println(Thread.currentThread().getName() + " " + "eating");
                pause();
                right.drop("right");
                left.drop("left");
            }
        } catch (InterruptedException e) {
            System.out.println(this + " " + "exiting via interrupt");
        }
    }
}
