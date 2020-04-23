package thinkInJava.section21Concurrency.p662Daemon.p3;

public class DaemonSpawn implements Runnable {
    @Override
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}
