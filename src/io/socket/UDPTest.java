package io.socket;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP协议的网络编程
 * @author nuc8
 * @date 2020/5/15 9:01 上午
 */
public class UDPTest {

    @Test
    public void sender() {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();

            byte[] data = "我是UDP方式发送的导弹".getBytes();
            InetAddress inetAddress = InetAddress.getLocalHost();
            DatagramPacket datagramPacket = new DatagramPacket(data, 0, data.length, inetAddress, 8899);

            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    @Test
    public void receiver() {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(8899);
            byte[] buffer = new byte[100];
            DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
            // 阻塞
            datagramSocket.receive(packet);
            System.out.println(new String(packet.getData(),0,packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }
}
