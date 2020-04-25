package concurrency.p674shareresource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * next() 生成偶数
 * 通过lock解决线程安全问题
 *
 * @Author shenxiaowei
 * @Date 2020-04-24 22:33
 */
public class MutexEvenGenerator extends AbstractIntGenerator {
    private int currentEvenValue = 0;
    private final Lock lock = new ReentrantLock();

    @Override
    public int next() {
        lock.lock();
        try {
            ++currentEvenValue;
            Thread.yield();
            ++currentEvenValue;
            return currentEvenValue;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}
