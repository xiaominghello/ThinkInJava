package concurrency.p674ShareResource;

/**
 * @Author Administrator
 * @Date 2020/4/24 21:43
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();
    public void cancel() {
        canceled = true;
    }
    public boolean isCanceled() {
        return canceled;
    }
}
