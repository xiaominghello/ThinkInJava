package io.p533inputoutput;

import java.io.IOException;
import java.io.StringReader;

/**
 * 从内存输入
 *
 * @Author Administrator
 * @Date 2020/5/5 8:19
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(BufferedInputFile.read("C:\\Users\\Administrator\\IdeaProjects\\ThinkingInJava\\src\\io\\p533inputoutput\\MemoryInput.java"));
        int c;
        while ((c = in.read()) != -1) {
            System.out.println((char)c);
        }
    }
}
