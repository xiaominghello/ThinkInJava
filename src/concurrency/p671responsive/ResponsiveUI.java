package concurrency.p671responsive;

import java.io.IOException;

/**
 * @Author Administrator
 * @Date 2020/4/24 21:06
 */
public class ResponsiveUI extends Thread {
    private static volatile double d = 1;

    public ResponsiveUI() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (true) {
            d = d + (Math.PI + Math.E) / d;
        }
    }

    public static void main(String[] args) throws IOException {
//        new UnResponsiveUI();
        new ResponsiveUI();
        System.in.read();
        System.out.println(d);
    }
}

class UnResponsiveUI {
    private static volatile double d = 1;

    public UnResponsiveUI() throws IOException {
        while (d > 0) {
            d = d + (Math.PI + Math.E) / d;
        }
        System.in.read();
    }
}
