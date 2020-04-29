package concurrency.p722;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author Administrator
 * @Date 2020/4/29 11:25
 */
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
        exec.execute(new PrioritizedTaskProducer(queue, exec));
        exec.execute(new PrioritizedTaskConsumer(queue));
    }
}

class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
    private final Random random = new Random(47);
    private static int counter = 0;
    private final int id = counter++;
    private final int priority;
    protected static List<PrioritizedTask> sequence = new ArrayList<>();

    public PrioritizedTask(int priority) {
        this.priority = priority;
        sequence.add(this);
    }

    @Override
    public int compareTo(PrioritizedTask o) {
        return Integer.compare(o.priority, priority);
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(250));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("[%1$-3d]", priority) + " Task " + id;
    }

    public String summary() {
        return "(" + id + ":" + priority + ")";
    }

    public static class EndSentinel extends PrioritizedTask {
        private final ExecutorService exec;

        public EndSentinel(ExecutorService e) {
            super(-1);
            exec = e;
        }

        @Override
        public void run() {
            for (PrioritizedTask pt :
                    sequence) {
                System.out.println(pt.summary());
            }
            System.out.println(this + " Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

class PrioritizedTaskProducer implements Runnable {
    private final Random random = new Random(47);
    private final Queue<Runnable> queue;
    private final ExecutorService exec;

    public PrioritizedTaskProducer(Queue<Runnable> queue, ExecutorService exec) {
        this.queue = queue;
        this.exec = exec;
    }

    @Override
    public void run() {
        int count = 20;
        for (int i = 0; i < count; i++) {
            queue.add(new PrioritizedTask(random.nextInt(10)));
            Thread.yield();
        }
        count = 10;
        try {
            for (int i = 0; i < count; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(10));
            }
            for (int i = 0; i < count; i++) {
                queue.add(new PrioritizedTask(i));
            }
            queue.add(new PrioritizedTask.EndSentinel(exec));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished PrioritizedTaskProducer");
    }
}

class PrioritizedTaskConsumer implements Runnable {
    private final PriorityBlockingQueue<Runnable> q;

    public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                q.take().run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished PrioritizedTaskConsumer");
    }
}