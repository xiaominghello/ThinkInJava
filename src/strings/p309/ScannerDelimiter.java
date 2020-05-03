package strings.p309;

import java.util.Scanner;

/**
 * 在默认情况下，Scanner根据空白字符对输入进行分词，但是你可以使用正则表达式指定自己所需的定界符
 *
 * @Author shenxiaowei
 * @Date 2020-05-03 21:48
 */
public class ScannerDelimiter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("12 42 78 99 42");
        while (scanner.hasNextInt()) {
            System.out.println(scanner.nextInt());
        }
        System.out.println();
        // \s	空白字符 * 零次多或次
        // 即使用逗号（包括逗号前后任意的空白字符）作为定界符
        scanner = new Scanner("12  ,   42, 78, 99, 42");
        scanner.useDelimiter("\\s*,\\s*");
        while (scanner.hasNextInt()) {
            System.out.println(scanner.nextInt());
        }
    }
}
