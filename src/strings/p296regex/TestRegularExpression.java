package strings.p296regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * public final class Pattern
 * extends Object
 * implements Serializable
 * 正则表达式的编译表示形式。
 *
 * public final class Matcher
 * extends Object
 * implements MatchResult
 * 通过解释 Pattern 对 character sequence 执行匹配操作的引擎。
 *
 *
 * public static Pattern compile(String regex)
 * 将给定的正则表达式编译到模式中。
 *
 * public Matcher matcher(CharSequence input)
 * 创建匹配给定输入与此模式的匹配器。
 *
 * public boolean find()
 * 尝试查找与该模式匹配的输入序列的下一个子序列。
 * 此方法从匹配器区域的开头开始，如果该方法的前一次调用成功了并且从那时开始匹配器没有被重置，则从以前匹配操作没有匹配的第一个字符开始。
 * 如果匹配成功，则可以通过 start、end 和 group 方法获取更多信息。
 *
 * public String group()
 * 返回由以前匹配操作所匹配的输入子序列。
 * public int start()
 * 返回以前匹配的初始索引。
 * public int end()
 * 返回最后匹配字符之后的偏移量。
 *
 * @Author shenxiaowei
 * @Date 2020-05-02 15:17
 */
public class TestRegularExpression {
    public static void main(String[] args) {
        // abc+ 匹配ab，后面跟着一个或多个c
        // (abc)+ 匹配1个或多个abc
        // (abc){2,} 匹配至少2次abc
        args = new String[]{"abcabcabcdefabc", "abc+", "(abc)+", "(abc){2,}"};

        if (args.length < 2) {
            System.out.println("Usage:\njava TestRegularExpression " +
                    "characterSequence regularExpression+");
            System.exit(0);
        }
        System.out.println("Input: \"" + args[0] + "\"");
        for (String arg : args) {
            System.out.println("Regular expression: \"" + arg + "\"");
            Pattern p = Pattern.compile(arg);
            Matcher m = p.matcher(args[0]);
            while (m.find()) {
                System.out.println("Match \"" + m.group() + "\" at positions " +
                        m.start() + "-" + (m.end() - 1));
            }
        }
    }
}
