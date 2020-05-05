package io.p533inputoutput;

import java.io.*;

/**
 * @Author Administrator
 * @Date 2020/5/5 8:36
 */
public class TestEOF {
    public static void main(String[] args) throws IOException {
        String name = "C:\\Users\\Administrator\\IdeaProjects\\ThinkingInJava\\src\\io\\p533inputoutput\\TestEOF.java";
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(name)));
        while (in.available() != 0) {
            System.out.println((char)in.readByte());
        }
    }
}
