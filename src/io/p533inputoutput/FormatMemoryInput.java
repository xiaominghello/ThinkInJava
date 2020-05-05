package io.p533inputoutput;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * 格式化的内存输入
 *
 * @Author Administrator
 * @Date 2020/5/5 8:22
 */
public class FormatMemoryInput {
    public static void main(String[] args) throws IOException {
        String fileName = "C:\\Users\\Administrator\\IdeaProjects\\ThinkingInJava\\src\\io\\p533inputoutput\\FormatMemoryInput.java";
        try {
            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(
                    BufferedInputFile.read(fileName).getBytes()));
            while (true) {
                System.out.println((char)in.readByte());
            }
        } catch (EOFException e) {
            System.err.println("End of stream");
        }
    }
}
