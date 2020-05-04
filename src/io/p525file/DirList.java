package io.p525file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * File
 * 文件和目录路径名的抽象表示形式。
 *
 * public String[] list()
 * 返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录。
 *
 * public String[] list(FilenameFilter filter)
 * 返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中满足指定过滤器的文件和目录。
 *
 * @Author shenxiaowei
 * @Date 2020-05-01 20:25
 */
public class DirList {
    public static void main(String[] args) {
        args = new String[]{"\\.\\w*"};
        File path = new File(".");
        System.out.printf("%-20s %b\n","path.exists():", path.exists());
        System.out.printf("%-20s %b\n","path.isDirectory():", path.isDirectory());
        System.out.printf("%-20s %b\n","path.isFile():", path.isFile());
        String[] list;
        if (args.length == 0) {
            list = path.list();
        } else {
            // 策略模式 list()实现了基本的功能，然后按照FilenameFilter的形式提供策略，
            // 以便完善list()在提供服务是所需的算法
            // list()方法会为此目录下的每个文件名调用accept()，来判断该文件是否包含在内，
            // 判断结果有accept()返回的布尔值表示
            list = path.list(new DirFilter(args[0]));
        }
        // 文件名按字母排序
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem: list) {
            System.out.println(dirItem);
        }
    }
}

class DirFilter implements FilenameFilter {

    private Pattern pattern;

    public DirFilter(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
