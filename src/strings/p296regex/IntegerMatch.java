package strings.p296regex;

/**
 * public boolean matches(String regex)
 * 告知此字符串是否匹配给定的正则表达式。
 * 调用此方法的 str.matches(regex) 形式与以下表达式产生的结果完全相同：
 *
 * Pattern.matches(regex, str)
 *
 * @Author shenxiaowei
 * @Date 2020-05-02 14:31
 */
public class IntegerMatch {
    public static void main(String[] args) {
        // -？\\d+  可能有一个负号后面跟着一个或多个数字
        // -?      一次或一次也没有
        // \\d+    数字一次或多次
        // -｜\\+  -或+
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("5678".matches("-?\\d+"));
        System.out.println("+911".matches("-?\\d+"));
        System.out.println("+911".matches("(-|\\+)?\\d++"));
    }
}
