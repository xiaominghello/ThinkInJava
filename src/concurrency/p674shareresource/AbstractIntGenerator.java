package concurrency.p674shareresource;

/**
 * 生成整数的抽象类
 *
 * @Author Administrator
 * @Date 2020/4/24 21:43
 */
public abstract class AbstractIntGenerator {
    private volatile boolean canceled = false;

    /**
     * generate next int
     *
     * @return next int
     */
    public abstract int next();
    public void cancel() {
        canceled = true;
    }
    public boolean isCanceled() {
        return canceled;
    }
}
