package strings.p296regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author shenxiaowei
 * @Date 2020-05-02 23:17
 */
public class JGrep {
    public static void main(String[] args) {
        args = new String[]{"JGrep.java", "\\b[Ssct]\\w+"};
        if (args.length < 2) {
            System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile(args[1]);
        int index = 0;
        Matcher m = p.matcher("");
        for (String line : new TextFile(args[0])) {
            m.reset(line);
            while (m.find()) {
                System.out.println(index++ + ": " +
                        m.group() + ": " + m.start());
            }
        }
    }
}
