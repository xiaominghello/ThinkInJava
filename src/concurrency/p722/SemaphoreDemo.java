package concurrency.p722;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author Administrator
 * @Date 2020/4/29 15:15
 */
public class SemaphoreDemo {
    final static int SIZE = 5;

    public static void main(String[] args) throws Exception {
        // 初始化对象池
        final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();
        // 该线程先签出，后签入对象
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new CheckoutTask<>(pool));
        }
        System.out.println("All checkoutTasks created");
        ArrayList<Fat> list = new ArrayList<>();
        // 最后所有对象都被main签出
        for (int i = 0; i < SIZE; i++) {
            Fat f = pool.checkOut();
            System.out.print(i + ": main() thread checked out");
            f.operation();
            list.add(f);
        }
        Future<?> blocked = exec.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // 阻塞
                    pool.checkOut();
                } catch (InterruptedException e) {
                    System.out.println("checkOut() Interrupted");
                }
            }
        });
        TimeUnit.SECONDS.sleep(2);
        // 打断阻塞
        blocked.cancel(true);
        System.out.println("Checking in objects in " + list);
        //把对象签入对象池
        for (Fat f :
                list) {
            pool.checkIn(f);
        }
        // 第二遍签入别忽略
        for (Fat f :
                list) {
            pool.checkIn(f);
        }
        exec.shutdown();
    }
}

class CheckoutTask<T> implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Pool<T> pool;

    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println(this + "checked out " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + "checking in " + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CheckoutTask " + id + " ";
    }
}
