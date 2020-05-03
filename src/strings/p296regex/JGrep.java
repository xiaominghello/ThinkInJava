package strings.p296regex;

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author shenxiaowei
 * @Date 2020-05-02 23:17
 */
public class JGrep {
    public static void main(String[] args) {
        // \b	单词边界
        // \w	单词字符：[a-zA-Z_0-9]
        // 搜索以[Ssct]开头的单词
        args = new String[]{"src/strings/p296regex/JGrep.java", "\\b[Ssct]\\w+"};
        if (args.length < 2) {
            System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile(args[1]);
        int index = 0;
        Matcher m = p.matcher("");
        // 读取文件所有行，并存入ArrayList中
        for (String line : new TextFile(args[0])) {
            System.out.println(line);
            // 虽然可以在for循环内部创建新的Matcher加载一行输入，
            // 但是，在循环外创建一个空的Matcher对象，然后用reset()方法每次为Matcher加载一行输入，
            // 这种处理会有一定的性能优化
            m.reset(line);
            while (m.find()) {
                System.out.println(index++ + ": " +
                        m.group() + ": " + m.start());
            }
        }
    }
}
