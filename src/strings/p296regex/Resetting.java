package strings.p296regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author shenxiaowei
 * @Date 2020-05-02 23:11
 */
public class Resetting {
    public static void main(String[] args) {
        // [abc]	a、b 或 c（简单类）
        Matcher m = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with bags");
        while (m.find()) {
            System.out.print(m.group() + " ");
        }
        System.out.println();
        // 重置匹配器。
        m.reset();
        while (m.find()) {
            System.out.print(m.group() + " ");
        }
        System.out.println();
        // 重置此具有新输入序列的匹配器
        m.reset("fix the rig with rags");
        while (m.find()) {
            System.out.print(m.group() + " ");
        }
    }
}
