package concurrency.p690threadlocal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程本地存储
 * ThreadLocal 对象通常当作静态域存储，通过get() set() 方法访问该对象的内容
 * increment() 和 get() 都不是 synchronized 的，但是没有发生线程安全问题
 * 就是因为每个对象的value都是对立的，并没有共享
 *
 * @Author shenxiaowei
 * @Date 2020-04-25 21:35
 */
public class ThreadLocalVariableHolder {
    // 创建一个匿名ThreadLocal 对象，重写初始化方法，返回一个随机数
    // 每个ThreadLocalVariableHolder类的实例都会随机创建一个随机的本地存储 value
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        private Random random = new Random(47);

        @Override
        protected synchronized Integer initialValue() {
            return random.nextInt(10000);
        }
    };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        int count = 5;
        for (int i = 0; i < count; i++) {
            exec.execute(new Accessor(i));
        }
        TimeUnit.MILLISECONDS.sleep(50);
        exec.shutdownNow();
    }

}

class Accessor implements Runnable {
    private final int id;

    public Accessor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "#" + id + ": " + ThreadLocalVariableHolder.get();
    }
}