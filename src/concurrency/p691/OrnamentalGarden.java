package concurrency.p691;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author shenxiaowei
 * @Date 2020-04-25 22:03
 */
public class OrnamentalGarden {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        int count = 5;
        for (int i = 0; i < count; i++) {
            exec.execute(new Entrance(i));
        }
        TimeUnit.MILLISECONDS.sleep(1000);
        Entrance.cancel();
        exec.shutdownNow();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
            System.out.println("Some tasks were not terminated!");
        }
        System.out.println("Total: " + Entrance.getTotalCount());
        System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
    }

}

/**
 * 跟踪花园参观者的主计数器
 */
class Count {
    private int count = 0;
    private Random random = new Random(47);

    public synchronized int increment() {
        // 人为创造一个可能导致线程不安全的情况
        int temp = count;
        if (random.nextBoolean()) {
            Thread.yield();
        }
        return count = ++temp;
    }

    public synchronized int value() {
        return count;
    }
}

/**
 * 花园的入口
 * count       所有花园入口都共享主计数器count
 * entrances   每个入口都维护一个所有入口的集合
 * number      通过该入口的人数
 * canceled    该入口是否开放
 *
 */
class Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<>();
    private int number = 0;
    private final int id;
    private static volatile boolean canceled = false;

    public static void cancel() {
        canceled = true;
    }

    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }

    @Override
    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + " Total: " + count.increment());
        }
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
        }
        System.out.println("Stopping " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    @Override
    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance entrance :
                entrances) {
            sum += entrance.getValue();
        }
        return sum;
    }
}
