package strings.p296regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * public static Pattern compile(String regex,
 *                               int flags)
 * 接受一个标记参数，以调整匹配的行为
 * 将给定的正则表达式编译到具有给定标志的模式中。
 *
 * CANON_EQ
 *           启用规范等价。
 * CASE_INSENSITIVE(?i)
 *           启用不区分大小写的匹配。
 * COMMENTS(?x)
 *           模式中允许空白和注释。
 * DOTALL(?s)
 *           启用 dotall 模式。
 * LITERAL
 *           启用模式的字面值解析。
 * MULTILINE(?m)
 *           启用多行模式。
 * UNICODE_CASE(?u)
 *           启用 Unicode 感知的大小写折叠。
 * UNIX_LINES(?d)
 *           启用 Unix 行模式。
 *
 * @Author shenxiaowei
 * @Date 2020-05-02 22:23
 */
public class ReFlags {
    public static void main(String[] args) {
        // ^ 行的开头
        // 以java开头，不区分大小写，开启多行模式
        Pattern p = Pattern.compile("^java", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher m = p.matcher(
                "java has regex\nJava has regex\n" +
                "JAVA has pretty good regular expressions\n" +
                "Regular expressions are in Java");
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
