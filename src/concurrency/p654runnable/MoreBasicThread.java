package concurrency.p654runnable;

/**
 * @Author shenxiaowei
 * @Date
 */
public class MoreBasicThread {
    public static void main(String[] args) {
        int count = 5;
        for (int i = 0; i < count; i++) {
            int countDown = 5;
            new Thread(new LiftOff(countDown)).start();
            System.out.println(Thread.currentThread().getName() + ": Waiting for LiftOff");
        }
    }
}
