package strings.p296regex;

/**
 * public String replaceAll(String regex,
*                           String replacement)
 * 使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串。
 * 调用此方法的 str.replaceAll(regex, repl) 形式与以下表达式产生的结果完全相同：
 * Pattern.compile(regex).matcher(str).replaceAll(repl)
 *
 * public String replaceFirst(String regex,
 *                            String replacement)
 * 使用给定的 replacement 替换此字符串匹配给定的正则表达式的第一个子字符串。
 * 调用此方法的 str.replaceFirst(regex, repl) 形式与以下表达式产生的结果完全相同：
 * Pattern.compile(regex).matcher(str).replaceFirst(repl)
 *
 *
 * @Author shenxiaowei
 * @Date 2020-05-02 15:09
 */
public class Replacing {
    static String s = Splitting.knights;

    public static void main(String[] args) {
        System.out.println(s);
        System.out.println(s.replaceFirst("f\\w+", "located"));
        System.out.println(s.replaceAll("shrubbery|tree|herring", "banana"));
    }

}
