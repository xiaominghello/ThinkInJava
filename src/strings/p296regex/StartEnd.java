package strings.p296regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author shenxiaowei
 * @Date 2020-05-02 22:01
 */
public class StartEnd {
    public static String input =
            "As long as there there there is injustice, whenever a\n" +
            "Targathian baby cries out, wherever a distress\n" +
            "signal sounds among the stars ... We'll be there.\n" +
            "This fine ship, and this fine crew ...\n" +
            "Never give up! Never surrender!";

    private static class Display {
        private boolean regexPrinted = false;
        private String regex;

        Display(String regex) {
            this.regex = regex;
        }

        void display(String message) {
            // 对于  while (m.find()) { }
            // 只打印一遍regex
            if (!regexPrinted) {
                System.out.println(regex);
                regexPrinted = true;
            }
            // 打印多次 message
            System.out.println(message);
        }
    }

    static void examine(String s, String regex) {
        Display d = new Display(regex);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        // 尝试查找与该模式匹配的输入序列的下一个子序列。
        while (m.find()) {
            d.display("find() '" + m.group() +
                    "' start = " + m.start() + " end = " + m.end());
        }
        // 尝试将从区域开头开始的输入序列与该模式匹配。
        // 与 matches 方法类似，此方法始终从区域的开头开始；与之不同的是，它不需要匹配整个区域。
        // 当且仅当输入序列的前缀匹配此匹配器的模式时才返回 true。
        if (m.lookingAt()) {
            d.display("lookingAt() start = " +
                    m.start() + " end = " + m.end());
        }
        // 尝试将整个区域与模式匹配。
        // 当且仅当整个区域序列匹配此匹配器的模式时才返回 true。
        if (m.matches()) {
            d.display("matches() start = " +
                    m.start() + " end = " + m.end());
        }
    }

    public static void main(String[] args) {
        for (String in : input.split("\n")) {
            System.out.println("input : " + in);
            for (String regex :
                    new String[]{"\\w*ere\\w*", "\\w*ever", "T\\w+", "Never.*?!"}) {
                examine(in, regex);
            }
            System.out.println();
        }
    }
}
