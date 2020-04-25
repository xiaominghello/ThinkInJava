package concurrency.p674shareresource;

/**
 * @Author shenxiaowei
 * @Date 2020-04-25 21:28
 */
public class SyncObject {
    public static void main(String[] args) {
        DualSynch ds = new DualSynch();
        new Thread() {
            @Override
            public void run() {
                ds.f();
            }
        }.start();
        ds.g();
    }
}

class DualSynch {
    private Object syncObject = new Object();
    public synchronized void f() {
        int count = 5;
        for (int i = 0; i < count; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }

    public void g() {
        synchronized (syncObject) {
            int count = 5;
            for (int i = 0; i < count; i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}
