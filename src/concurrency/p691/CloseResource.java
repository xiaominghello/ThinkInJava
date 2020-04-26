package concurrency.p691;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author Administrator
 * @Date 2020/4/26 9:05
 */
public class CloseResource {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8083);
        InputStream socketInput = new Socket("localhost", 8083).getInputStream();
        exec.execute(new IOBlocked(socketInput));
        exec.execute(new IOBlocked(System.in));
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Shutting down all threads");
        exec.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Attempt to close " + socketInput.getClass().getName());
        socketInput.close();
        System.out.println("Close " + socketInput.getClass().getName() + " success");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Attempt to close " +System.in.getClass().getName());
        // 在 read 堵塞的状态下，close 也会堵塞，和书中不同
        System.in.close();
        System.out.println("Close " + System.in.getClass().getName() + " success");
    }
}
