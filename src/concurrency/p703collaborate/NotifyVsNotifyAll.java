package concurrency.p703collaborate;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 先解释两个概念。
 *
 * 等待池：假设一个线程A调用了某个对象的wait()方法，线程A就会释放该对象的锁后，进入到了该对象的等待池，等待池中的线程不会去竞争该对象的锁。
 *
 * 锁池：只有获取了对象的锁，线程才能执行对象的 synchronized 代码，对象的锁每次只有一个线程可以获得，其他线程只能在锁池中等待
 * 区别：
 *
 * notify() 方法随机唤醒对象的等待池中的一个线程，进入锁池；notifyAll() 唤醒对象的等待池中的所有线程，进入锁池。
 *
 * 此例中，5个Task对象被创建，然后执行run(),然后调用wait(),都被挂起
 * 当调用Task.blocker.prod() 时，随机唤醒一个线程执行后面代码
 * 当调用Task.blocker.prodAll(); 时，5个线型都被唤醒，进入阻塞状态，同一时间只有一个线程能获得锁，其他4个继续阻塞，
 * 等该线程执行完循环里的代码后，释放锁，然后5个线程继续随机获得锁，
 * 执行完循环的线程再次获得锁，会调用wait()被挂起，没执行循环里后续代码的则执行代码，再下一次获得锁后执行wait()被挂起
 * 最后的结果就是5个线程都执行了循环后续的代码，并都调用wait()被挂起
 *
 * @Author Administrator
 * @Date 2020/4/27 9:13
 */
public class NotifyVsNotifyAll {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        int count = 5;
        for (int i = 0; i < count; i++) {
            exec.execute(new Task());
        }
        exec.execute(new Task2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod =true;
            @Override
            public void run() {
                if (prod) {
                    System.out.println("\nnotify() ");
                    Task.blocker.prod();
                    prod = false;
                } else {
                    System.out.println("\nnotifyAll() ");
                    Task.blocker.prodAll();
                    prod = true;
                }
            }
        }, 400, 400);
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("\nTimer cancled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nTask2.blocker.prodAll()");
        Task2.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nShutting down");
        exec.shutdownNow();
    }
}

class Blocker {
    synchronized void waitingCall() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(Thread.currentThread() + " wait ");
                wait();
                System.out.print(Thread.currentThread() + " awake ");
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
    }

    synchronized void prod() {
        notify();
    }

    synchronized void prodAll() {
        notifyAll();
    }
}

class Task implements Runnable {
    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}

class Task2 implements Runnable {
    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}
