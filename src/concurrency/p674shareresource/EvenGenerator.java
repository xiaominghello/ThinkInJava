package concurrency.p674shareresource;

/**
 * next() 生成偶数
 * 有线程安全问题
 *
 * @Author Administrator
 * @Date 2020/4/24 21:52
 */
public class EvenGenerator extends AbstractIntGenerator {
    private int currentEvenValue = 0;
    @Override
    public int next() {
        ++currentEvenValue;
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
