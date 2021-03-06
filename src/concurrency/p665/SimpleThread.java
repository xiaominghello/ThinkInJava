package concurrency.p665;

/**
 * Inheriting directly from the Thread class.
 *
 * @Author Administrator
 * @Date 2020/4/24 10:15
 */
public class SimpleThread extends Thread {

    private int countDown = 5;
    private static int threadCount = 0;

    public SimpleThread() {
        // store the thread name:
        super(Integer.toString(++threadCount));
        start();
    }

    @Override
    public String toString() {
        return "#" + getName() + "(" + countDown + ")";
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int count = 5;
        for (int i = 0; i < count; i++) {
            new SimpleThread();
        }
    }
}
