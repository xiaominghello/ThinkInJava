package io.p542output;

import io.p533inputoutput.BufferedInputFile;

import java.io.*;

/**
 * @Author Administrator
 * @Date 2020/5/5 11:44
 */
public class FileOutputShortcut {
    static String file = "C:\\Users\\Administrator\\IdeaProjects\\ThinkingInJava\\src\\io\\p542output\\FileOutputShortcut.out";
    public static void main(String[] args) throws IOException {
        String fileName = "C:\\Users\\Administrator\\IdeaProjects\\ThinkingInJava\\src\\io\\p542output\\FileOutputShortcut.java";
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read(fileName)));
        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + ": " + s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
