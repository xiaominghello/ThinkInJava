package strings.p296regex;

import java.util.Arrays;

/**
 *  public String[] split(String regex)
 *  根据给定正则表达式的匹配拆分此字符串。
 *
 *  public String[] split(String regex, nt limit)
 *  limit 参数控制模式应用的次数，因此影响所得数组的长度。
 *  如果该限制 n 大于 0，则模式将被最多应用 n - 1 次，数组的长度将不会大于 n，
 *  而且数组的最后一项将包含所有超出最后匹配的定界符的输入。
 *  如果 n 为非正，那么模式将被应用尽可能多的次数，而且数组可以是任何长度。
 *  如果 n 为 0，那么模式将被应用尽可能多的次数，数组可以是任何长度，并且结尾空字符串将被丢弃。
 *
 * @Author shenxiaowei
 * @Date 2020-05-02 14:42
 */
public class Splitting {
    public static String knights =
            "Then, when you have found the shrubbery, you must " +
            "cut down the mightiest tree in the forest... " +
            "with... a herring!";

    public static void split(String regex) {
        String[] result = knights.split(regex);
        System.out.println(Arrays.toString(result));
    }

    public static void main(String[] args) {
//        预定义字符类
//            .	任何字符（与行结束符可能匹配也可能不匹配）
//            \d	数字：[0-9]
//            \D	非数字： [^0-9]
//            \s	空白字符：[ \t\n\x0B\f\r]
//            \S	非空白字符：[^\s]
//            \w	单词字符：[a-zA-Z_0-9]
//            \W	非单词字符：[^\w]
        System.out.println(knights);
        // 按 空格 拆分
        split(" ");
        // 按 一个或多个非字母字符 拆分
        split( "\\W+");
        // 按 n后跟着一个或多个非字母字符 拆分
        split("n\\W+");
    }

}
