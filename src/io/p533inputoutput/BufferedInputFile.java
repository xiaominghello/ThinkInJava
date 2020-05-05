package io.p533inputoutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 缓存输入文件
 *
 * @Author Administrator
 * @Date 2020/5/4 22:54
 */
public class BufferedInputFile {
    public static String read(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null) {
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(read("C:\\Users\\Administrator\\IdeaProjects\\ThinkingInJava\\src\\io\\p533inputoutput\\BufferedInputFile.java"));
    }
}
