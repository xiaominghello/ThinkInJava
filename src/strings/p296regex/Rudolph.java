package strings.p296regex;

/**
 * @Author shenxiaowei
 * @Date 2020-05-03 10:59
 */
public class Rudolph {
    public static void main(String[] args) {
        for (String pattern :
                new String[]{"Rudolph", "[rR]udolph", "[rR][aeiou][a-z]ol.*", "R.*"}) {
            System.out.println("Rudolph".matches(pattern));
        }
    }
}

