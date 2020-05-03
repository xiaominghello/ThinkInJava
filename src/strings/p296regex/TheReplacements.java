package strings.p296regex;

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 * @Author shenxiaowei
 * @Date 2020-05-02 22:38
 */

/*! Here's    a    block of text to use as input to
    the regular expression matcher. Note that we'll
    first extract the block of text by looking for
    the special delimiters, then process the
    extracted block. !*/
public class TheReplacements {
    public static void main(String[] args) {
        String s = TextFile.read("src/strings/p296regex/TheReplacements.java");
        // Match the specially commented block of text above:
        Matcher mInput = Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL).matcher(s);
        if (mInput.find()) {
            s = mInput.group(1);
            System.out.println(s + "\n");

        }
        // 把2次以上的空格用一个空格替换
        s = s.replaceAll(" {2,}", " ");
        // 去掉每行开头的空格
        s = s.replaceAll("(?m)^ +", "");
        System.out.println(s + "\n");
        // 替换掉第一个匹配成功的部分
        s = s.replaceFirst("[aeiou]", "(VOWEL1)");
        System.out.println(s + "\n");
        StringBuffer sbuf = new StringBuffer();
        Pattern p = Pattern.compile("[aeiou]");
        Matcher m = p.matcher(s);
        while (m.find()) {
            m.appendReplacement(sbuf, m.group().toUpperCase());
        }
        System.out.println(sbuf + "\n");
        m.appendTail(sbuf);
        System.out.println(sbuf);
    }
}
