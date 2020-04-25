package concurrency.p674shareresource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Administrator
 * @Date 2020/4/25 16:03
 */
public class ExplicitCriticalSection {
    public static void main(String[] args) {
        AbstractPairManager
                pam1 = new ExplicitPaidManager1(),
                pam2 = new ExplicitPairManager2();
        CriticalSection.testApproaches(pam1, pam2);
    }
}

class ExplicitPaidManager1 extends AbstractPairManager {
    private final Lock lock = new ReentrantLock();

    @Override
    public synchronized void increment() {
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            store(getPair());
        } finally {
            lock.unlock();
        }
    }
}

class ExplicitPairManager2 extends AbstractPairManager {
    private final Lock lock = new ReentrantLock();

    @Override
    public void increment() {
        Pair temp;
        lock.lock();
        try {
            // PairValuesNotEqualException: Pair values not equal: Pair{x=2, y=1}
            // 当执行 x++后，不影响 check线程调用getPair()去比较x，y的值，抛出异常
            p.incrementX();
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.incrementY();
            temp = getPair();
        } finally {
            lock.unlock();
        }
        store(temp);
    }
}
