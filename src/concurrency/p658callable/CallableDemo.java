package concurrency.p658callable;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @Author shenxiaowei
 * @Date
 */
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        int count = 10;
        for (int i = 0; i < count; i++) {
            results.add(exec.submit(new TaskWithResult(i)));
        }
        for (Future<String> fs : results) {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                exec.shutdown();
            }
        }
    }
}

class TaskWithResult implements Callable<String> {

    private final int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() {
        return Thread.currentThread().getName() + "result of TaskWithResult" + id;
    }
}
