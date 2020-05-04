package io.p525file;

import net.mindview.util.Directory;
import net.mindview.util.PPrint;

import java.io.File;

/**
 * 使用PPrint工具类 和 Directory工具类
 * 输出指定目录下的所有目录
 * 输出指定目录下满足特定正则表达式的文件
 * walk  递归
 * local 不递归
 *
 * @Author shenxiaowei
 * @Date 2020-05-02 12:15
 */
public class DirectoryDemo {
    public static void main(String[] args) {
        PPrint.pprint(Directory.walk(".").dirs);
        for (File file : Directory.local(".", "T.*")) {
            System.out.println(file);
        }
        System.out.println("----------");
        for (File file : Directory.walk(".", "T.*\\.java")) {
            System.out.println(file);
        }
        System.out.println("==========");
        for (File file : Directory.walk(".", ".*[Zz].*\\.java")) {
            System.out.println(file);
        }
    }
}
