package concurrency.p719deaklock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 以下4个条件都满足，就会发生死锁：
 * 1.互斥条件，筷子一次只能被一个人使用
 * 2.至少一个任务它持有一个资源且正在等待获取一个当前被别的任务持有的资源，拿着一根等另一根
 * 3.资源不能被任务抢占，不会去抢别人手里的筷子
 * 4.必须有循环等待，这时，一个任务等待其他任务所持有的资源，后者又在等待另一个任务所持有的资源，
 * 这样一直下去，直到有一个任务在等待第一个任务所持有的资源，使得大家都被锁住，每个人都拿着一根等另一根
 * <p>
 * Philosopher都试图用特定的顺序拿Chopstick：先右后左
 * 正因为此，就可能发生"每个人都拿着右边的Chopstick，并等待左边的Chopstick
 * <p>
 * 此书通过破坏第四个条件解决死锁问题：
 * 如果最后一个Philosopher被初始化成先拿左边的Chopstick，后拿右边的Chopstick，
 * 那么这个Philosopher将永远不会阻止其右边的Philosopher拿起他们间的Chopstick
 *
 * @Author shenxiaowei
 * @Date 2020-04-28 22:26
 */
public class DeadlockingDiningPhilosophers {
    public static void main(String[] args) throws Exception {
        args = new String[]{"0", "5", "timeout"};
        int ponder = 5;
        if (args.length > 0) {
            ponder = Integer.parseInt(args[0]);
        }
        int size = 5;
        if (args.length > 1) {
            size = Integer.parseInt(args[1]);
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            exec.execute(new Philosopher(
//                    chopsticks[(i+1)%size] 主要是为了最后一个人右手边的筷子下标为0，即形成一个循环
                    chopsticks[i], chopsticks[(i + 1) % size], i, ponder));
        }

//        FixedDingingPhilosophers
//        for (int i = 0; i < size; i++) {
//            if (i < (size - 1)) {
//                exec.execute(new Philosopher(
//                        chopsticks[i], chopsticks[(i + 1) % size], i, ponder));
//            } else {
//                exec.execute(new Philosopher(
////                        最后一个人左右边筷子下标互换，效果和最后一个人先拿左边一样
//                        chopsticks[0], chopsticks[i], i, ponder));
//            }
//
//        }

        if (args.length == 3 && args[2].equals("timeout")) {
            TimeUnit.SECONDS.sleep(5);
        } else {
            System.out.println("Press 'Enter' to quick");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
