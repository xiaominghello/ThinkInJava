package strings.p296regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 组(Groups)
 * 组是用括号划分的正则表达式，可以根据组的编号来引用某个组。
 * 组号为0表示整个表达式，组号1表示被第一对括号括起的组，依此类推。
 * A(B(C))D
 * 有3个组，组0：ABCD  组1：BC 组3：C
 *
 * public int groupCount()
 * 返回此匹配器模式中的捕获组数。
 *
 * @Author shenxiaowei
 * @Date 2020-05-02 15:45
 */
public class Groups {
    static public final String POEM =
            "Twas brillig, and the slithy toves\n" +
            "Did gyre and gimble in the wabe.\n" +
            "All mimsy were the borogoves,\n" +
            "And the mome raths outgrabe.\n\n" +
            "Beware the Jabberwock, my son,\n" +
            "The jaws that bite, the claws that catch.\n" +
            "Bewate the Jubjub bird, and shun\n" +
            "The frumious Bandersnatch.";

    public static void main(String[] args) {
        // \s	空白字符：[ \t\n\x0B\f\r]
        // \S	非空白字符：[^\s]
        // (?m) 嵌入式标志表达式，表示开启多行模式
        // $    在多行模式下，$在行结束符前后匹配
        // 目的是捕获每行的最后3个词,这其中又有5个组
        // 组1  \\S+\\s+\\S+\\s+\\S+
        // 组2  \\S+
        // 组3          \\S+\\s+\\S+
        // 组4          \\S+
        // 组5                  \\S+
        Matcher m = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$")
                .matcher(POEM);
        while (m.find()) {
            for (int j = 0; j <= m.groupCount(); j++) {
                System.out.print("[" + m.group(j) + "]");
            }
            System.out.println();
        }
    }
}
