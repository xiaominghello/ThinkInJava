package concurrency.p670join;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Administrator
 * @Date 2020/4/24 12:48
 */
public class Joining {
    public static void main(String[] args) {
        Sleeper sleepy = new Sleeper("sleepy", 3000);
        Sleeper grumpy = new Sleeper("Grumpy", 3000);
        new Joiner("Dopey", sleepy);
        new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }
}

class Sleeper extends Thread {
    private final int duration;
    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }
    @Override
    public void run() {
        try {
            System.out.println(getName() + " sleep: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted. " + "isInterrupted(): " +isInterrupted());
        }
        System.out.println(getName() + " has awakened: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}

class Joiner extends Thread {
    private final Sleeper sleeper;
    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }
    @Override
    public void run() {
        try {
            System.out.println(getName() + " wait "+ sleeper.getName() +" join: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            sleeper.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sleeper.getName() +" join completed: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}

