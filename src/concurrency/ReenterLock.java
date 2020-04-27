package concurrency;

import java.text.SimpleDateFormat;

/**
 * decrease thread 由于不满足(num > count)的条件调用Object.wait()，
 * 释放持有的锁使得自己进入WAITING状态并加入线程等待队列中。
 * increase thread增加了count数量并且调用了Object.notifyAll()去唤醒等待队列中处于WAITING状态的线程，
 * 但是此时因为调用了Thread.sleep()，并没有释放锁。
 * 所以decrease thread被唤醒后仍然需要竞争对象锁来回到之前的同步方法中，此时decrease thread的状态就是BLOCKED。
 *
 * 判断是否是BLOCKED可以看该线程是否在等待获取锁,
 * WAITING从官方文档中可以知道调用Object.wait(), Thread.join(), LockSupport.park()会使得线程进入该状态
 *
 * 作者：Jerold
 * 链接：https://www.zhihu.com/question/27654579/answer/140541475
 * 来源：知乎
 *
 * @Author Administrator
 * @Date 2020/4/27 13:14
 */
public class ReenterLock {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                counter.decrease(20);
            }
        });
        t1.setName("decrease thread");
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getState());
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                counter.increase(10);
            }
        });
        t2.setName("increase thread");
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getState());

    }

    static class Counter {
        private int count = 10;

        public synchronized void increase(int num) {
            count += num;
//            System.out.println(new SimpleDateFormat("HH:mm:ss").format());
            this.notifyAll();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void decrease(int num) {
            if (num > count) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count -= num;
        }
    }
}
