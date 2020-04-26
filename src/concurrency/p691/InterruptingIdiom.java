package concurrency.p691;

import java.util.concurrent.TimeUnit;

/**
 * 中断惯用法
 * 中断发生的唯一时刻：任务要进入到阻塞操作中，或者已经在阻塞操作内部时
 * 此例即 n1 要进入sleep(), 或者n1 已经在sleep(),
 *
 * @Author shenxiaowei
 * @Date 2020-04-26 20:36
 */
public class InterruptingIdiom {
    public static void main(String[] args) throws InterruptedException {
        args = new String[]{"1100"};
        if (args.length != 1) {
            System.out.println("usage: java InterruptingIdiom delay-in-mS");
            System.exit(1);
        }
        Thread t = new Thread(new Blocked3());
        t.start();
        // 通过使用给定的不同的延时时间，在不同地点退出Blocked3.run()
        TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
        //除非当前线程正在中断自身（这始终是允许的），否则将调用此线程的checkAccess方法，
        // 这可能导致引发SecurityException。
        //      如果此线程在调用对象类的wait（）、wait（long）
        //          或wait（long，int）方法或该类的join（）、join（long）、
        //          join（long，int）、sleep（long）或sleep（long，int）方法时被阻止，
        //          则其中断状态将被清除，并将接收到InterruptedException。
        //      如果此线程在中断通道上的I/O操作中被阻塞，则通道将关闭，线程的中断状态将被设置，
        //          线程将收到ClosedByInterruptException。
        //      如果这个线程在选择器中被阻塞，那么线程的中断状态将被设置，
        //          并且它将立即从选择操作返回，可能具有非零值，就像选择器的唤醒方法被调用一样。
        //      如果前面的条件都不成立，那么这个线程的中断状态将被设置。
        //
        //      中断不存活的线程不会有任何效果。
        t.interrupt();
    }

}

/**
 * 强调在你经由异常离开循环时，正确清理资源的重要性
 */
class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int id) {
        this.id = id;
        System.out.println("NeedsCleanup " + id);
    }

    public void cleanup() {
        System.out.println("Cleaning up " + id);
    }
}

class Blocked3 implements Runnable {
    private volatile double d = 0.0;

    @Override
    public void run() {
        try {
            // 测试当前线程是否已中断。此方法清除线程的中断状态。换言之，
            // 如果连续两次调用此方法，则第二次调用返回false（除非在第一次调用清除其中断状态后，
            // 第二次调用检查它之前，当前线程被再次中断）。
            // 由于线程不存活而被忽略的线程中断将由此方法返回false反映出来。
            // interrupted 是 static 方法
            // isInterrupted 是普通方法
//            while (!Thread.interrupted()) {
            while (!Thread.currentThread().isInterrupted()) {
                // point1
                NeedsCleanup n1 = new NeedsCleanup(1);
                // Start try-finally immediately after definition of n1
                // to guarantee proper cleanup of n1
                try {
                    System.out.println("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    // point2
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    // guarantee proper cleanup of n1
                    try {
                        System.out.println("Calculating");
                        int count = 2500000;
                        for (int i = 0; i < count; i++) {
                            d = d + (Math.PI + Math.E) / d;
                        }
                        System.out.println("Finished time-consuming operation");
                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Exiting via InterruptedException");

        }
    }
}
