package concurrency.p674shareresource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Pair里有2个变量x，y，Pair是线程不安全的，如果两个变量都同时不断加且保证 x = y，那么在多线程下如何做到线程安全？
 * <p>
 * 线程1：increment(): 1.执行累加操作 2.sleep()模拟出time consuming operation
 * 线程2： 不断执行检查操作：先getPair() 获取Pair，再比较Pair的x，y是否相等
 * <p>
 * 方案一：在increment()上加锁，那么一旦线程一进入，整个对象被加锁，线程二想getPair()只能等待,可以实现线程安全
 * 方案二：在increment()里的critical section上加锁，那么被锁住的只有increment()方法，整个对象没有被加锁，
 * 线程二可以getPair()后来执行检查操作，不是程序安全的
 * 方案二的由于没有在对象上加锁，所以 checkCounter 大于 方案一的 checkCounter
 *
 * @Author Administrator
 * @Date 2020/4/25 13:25
 */
public class CriticalSection {
    /**
     * Test the two different approaches
     *
     * @param pam1 AbstractPairManager
     * @param pam2 AbstractPairManager
     */
    static void testApproaches(AbstractPairManager pam1, AbstractPairManager pam2) {
        ExecutorService exec = Executors.newCachedThreadPool();
        PairManipulator
                pm1 = new PairManipulator(pam1),
                pm2 = new PairManipulator(pam2);
        PairChecker
                checker1 = new PairChecker(pam1),
                checker2 = new PairChecker(pam2);
        exec.execute(pm1);
        exec.execute(checker1);
        exec.execute(pm2);
        exec.execute(checker2);

        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
        System.exit(0);
    }

    public static void main(String[] args) {
        AbstractPairManager
                pam1 = new PairManager1(),
                pam2 = new PairManager2();
        testApproaches(pam1, pam2);
    }
}

/**
 * not thread safe
 */
class Pair {
    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementX() {
        x++;
    }

    public void incrementY() {
        y++;
    }

    @Override
    public String toString() {
        return "Pair{" + "x=" + x + ", y=" + y + '}';
    }

    public class PairValuesNotEqualException extends RuntimeException {
        public PairValuesNotEqualException() {
            super("Pair values not equal: " + Pair.this);
        }
    }

    /**
     * arbitrary invariant --both variables must be equal
     */
    public void checkState() {
        if (x != y) {
            throw new PairValuesNotEqualException();
        }
    }
}

/**
 * Protect a Pair inside a thread-safe class
 */
abstract class AbstractPairManager {
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair p = new Pair();
    private final List<Pair> storage = Collections.synchronizedList(new ArrayList<>());

    public synchronized Pair getPair() {
        // make a copy to keep the original safe
        return new Pair(p.getX(), p.getY());
    }

    /**
     * Assume this is a time consuming operation
     *
     * @param p Pair
     */
    protected void store(Pair p) {
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * increment
     */
    public abstract void increment();
}

/**
 * Synchronized the entire method
 * increment() 和 getPair() 都是 synchronized 方法，一个被调用，另一个就只能等待
 * 所以实现了线程安全
 */
class PairManager1 extends AbstractPairManager {

    @Override
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}

/**
 * Use a critical section
 * 只锁住了大括号范围的代码，类没有被锁住，其他线程还是可以调用getPair()
 * 不能实现线程安全
 */
class PairManager2 extends AbstractPairManager {

    @Override
    public void increment() {
        Pair temp;
        synchronized (new Object()) {
//        synchronized (this) {
            p.incrementX();
//            这里有耗时操作，发现是线程不安全的
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.incrementY();
            temp = getPair();
        }
//        作者本意是可能想着这里模拟耗时操作，来证明是线程安全的，感觉加错地方了
        store(temp);
    }
}

class PairManipulator implements Runnable {
    private final AbstractPairManager pm;

    public PairManipulator(AbstractPairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.increment();
        }
    }

    @Override
    public String toString() {
        return "Pair: " + pm.getPair() + " checkCounter = " + pm.checkCounter.get();
    }
}

class PairChecker implements Runnable {
    private final AbstractPairManager pm;

    public PairChecker(AbstractPairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.checkCounter.incrementAndGet();
            try {
                pm.getPair().checkState();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
