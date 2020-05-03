package strings.p283;

/**
 * 不可变String
 * 当需要改变字符串的内容时，String类的方法都会返回一个新的String对象。
 * 同时，如果内容没有发生改变，String的方法只是返回指向原对象的引用而已。
 * 这可以节约存储空间以及避免额外的开销。
 *
 * @Author shenxiaowei
 * @Date 2020-05-03 14:49
 */
public class Immutable {
    public static String upcase(String s) {
        return s.toUpperCase();
    }

    public static void main(String[] args) {
        String q = "howdy";
        System.out.println(q);
        String qq = upcase(q);
        System.out.println(qq);
        System.out.println(q);
    }
}
