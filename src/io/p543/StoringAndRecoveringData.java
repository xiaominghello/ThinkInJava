package io.p543;

import java.io.*;

/**
 * @Author Administrator
 * @Date 2020/5/5 20:08
 */
public class StoringAndRecoveringData {
    public static void main(String[] args) throws IOException {
        String file = "C:\\Users\\Administrator\\IdeaProjects\\ThinkingInJava\\src\\io\\p543\\Data.txt";
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(file)));
        out.writeDouble(3.14159);
        out.writeUTF("That was pi");
        out.writeDouble(1.41414);
        out.writeUTF("Squate root of 2");
        out.close();
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(file)));
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
    }
}
