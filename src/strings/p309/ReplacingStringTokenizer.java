package strings.p309;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 在引入正则表达式（J2SE1.4）和 Scanner类（Java SE5）之前，分割字符串的唯一方法是
 * 使用StringTokenizer来分词，不过，现在有了正则表达式和Scanner，我们可以使用更加简单，
 * 更加简洁的方式来完成同样的工作了。
 *
 * @Author shenxiaowei
 * @Date 2020-05-03 22:07
 */
public class ReplacingStringTokenizer {
    public static void main(String[] args) {
        String input = "But I'm not dead yet! I feel happy!";
        StringTokenizer stoke = new StringTokenizer(input);
        while(stoke.hasMoreElements())
            System.out.print(stoke.nextToken() + " ");
        System.out.println();
        // 使用正则表达式
        System.out.println(Arrays.toString(input.split(" ")));
        // 使用Scanner
        Scanner scanner = new Scanner(input);
        while(scanner.hasNext())
            System.out.print(scanner.next() + " ");
    }
}
