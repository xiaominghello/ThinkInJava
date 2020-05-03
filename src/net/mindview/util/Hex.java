package net.mindview.util;

import com.sun.codemodel.internal.fmt.JBinaryFile;

import java.io.File;
import java.io.IOException;

/**
 * 在处理二进制文件时，我们经常希望以十六进制的格式看看其内容。
 *
 * @Author shenxiaowei
 * @Date 2020-05-03 21:11
 */
public class Hex {
    public static String format(byte[] data) {
        StringBuilder result = new StringBuilder();
        int n = 0;
        for (byte b : data) {
            if (n % 16 == 0) {
                result.append(String.format("%05X: ", n));
            }
            result.append(String.format("%02X ", b));
            n++;
            if (n % 16 == 0) {
                result.append("\n");
            }
        }
        result.append("\n");
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println(format(BinaryFile.read("out/production/ThinkInJava/net/mindview/util/Hex.class")));
        } else {
            System.out.println(format(BinaryFile.read(new File(args[0]))));
        }
    }
}
