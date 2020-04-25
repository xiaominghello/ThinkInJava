package concurrency.p674shareresource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 偶数检查器
 *
 * @Author Administrator
 * @Date 2020/4/24 21:45
 */
public class EvenChecker implements Runnable {
    private final AbstractIntGenerator generator;
    private final int id;

    public EvenChecker(AbstractIntGenerator g, int ident) {
        generator = g;
        id = ident;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" +  val + " not even!" + "------------------------------------");
                generator.cancel();
            }
        }
    }

    public static void test(AbstractIntGenerator gp, int count) {
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenChecker(gp, i));
        }
        exec.shutdown();
    }

    public static void test(AbstractIntGenerator gp) {
        test(gp, 10);
    }
}
