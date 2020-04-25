package concurrency.p674shareresource;

/**
 * next() 生成偶数
 * 通过synchronized 解决线程安全问题
 *
 * @Author shenxiaowei
 * @Date 2020-04-24 22:33
 */
public class SynchronizeEvenGenerator extends AbstractIntGenerator {
    private int currentEvenValue = 0;

    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizeEvenGenerator());
    }
}
