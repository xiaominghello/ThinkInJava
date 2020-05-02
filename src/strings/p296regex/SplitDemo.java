package strings.p296regex;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @Author shenxiaowei
 * @Date 2020-05-02 22:33
 */
public class SplitDemo {
    public static void main(String[] args) {
        String input = "This!!unusual use!!of exclamation!!points";
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input)));
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input, 3)));
    }
}
