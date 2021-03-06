package strings.p289format;

import java.math.BigInteger;
import java.util.Formatter;

/**
 *  类型转换字符
 *  d 整数型
 *  c Unicode字符
 *  b Boolean值
 *  s String
 *  f 浮点数（十进制）
 *  e 浮点数（科学计数）
 *  x 整数（十六进制）
 *  h 散列码（十六进制）
 *  % 字符 "%"
 * @Author shenxiaowei
 * @Date 2020-05-03 20:47
 */
public class Conversion {
    public static void main(String[] args) {
        Formatter f = new Formatter(System.out);
        char u = 'a';
        System.out.println("u = 'a'");
        f.format("s: %s\n", u);
        f.format("c: %c\n", u);
        f.format("b: %b\n", u);
        f.format("h: %h\n", u);

        int v = 121;
        System.out.println("s = 121");
        f.format("d: %d\n", v);
        f.format("c: %c\n", v);
        f.format("b: %b\n", v);
        f.format("s: %s\n", v);
        f.format("x: %x\n", v);
        f.format("h: %h\n", v);

        BigInteger w = new BigInteger("50000000000000");
        System.out.println("w = new BigInteger(\"50000000000000\")");
        f.format("d: %d\n", w);
        f.format("b: %b\n", w);
        f.format("s: %x\n", w);
        f.format("x: %x\n", w);
        f.format("h: %h\n", w);

        double x = 179.543;
        System.out.println("x = 179.543");
        f.format("b: %b\n", x);
        f.format("s: %s\n", x);
        f.format("f: %f\n", x);
        f.format("e: %e\n", x);
        f.format("h: %h\n", x);

        Conversion y = new Conversion();
        System.out.println("y = new Conversion()");
        f.format("b: %b\n", y);
        f.format("s: %s\n", y);
        f.format("h: %h\n", y);

        boolean z = false;
        System.out.println("z = false");
        f.format("b: %b\n", z);
        f.format("s: %s\n", z);
        f.format("h: %h\n", z);

    }
}
