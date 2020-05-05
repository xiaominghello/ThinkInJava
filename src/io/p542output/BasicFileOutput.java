package io.p542output;

import io.p533inputoutput.BufferedInputFile;

import java.io.*;

/**
 * 基本的文件输出
 *
 * @Author Administrator
 * @Date 2020/5/5 8:48
 */
public class BasicFileOutput {
    static String file = "C:\\Users\\Administrator\\IdeaProjects\\ThinkingInJava\\src\\io\\p542output\\BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        String fileName = "C:\\Users\\Administrator\\IdeaProjects\\ThinkingInJava\\src\\io\\p542output\\BasicFileOutput.java";
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read(fileName)));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + ": " + s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
