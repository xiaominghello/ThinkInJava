package strings.p296regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * public boolean find()
 * 尝试查找与该模式匹配的输入序列的下一个子序列。
 * 可以用来在CharSequence中查找多个匹配
 *
 * public boolean find(int start)
 * 重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列。
 *
 * @Author shenxiaowei
 * @Date 2020-05-02 15:36
 */
public class Finding {
    public static void main(String[] args) {
        Matcher m = Pattern.compile("\\w+")
                .matcher("Evening is full of the linnet's wings");
        while (m.find()) {
            System.out.print(m.group() + " ");
        }
        System.out.println();
        int i = 0;
        // 不断重新设定搜索的起始位置
        while (m.find(i)) {
            System.out.print(m.group() + " ");
            i++;
        }
    }
}
