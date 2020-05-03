package strings.p283;

/**
 * String的 "+" 和 "+=" 是Java中仅有的两个重载过的操作符
 * 编译器实际是创建一个StringBuilder对象，通过调用4次append()方法，然后调用toString()生成结果
 *
 * @Author shenxiaowei
 * @Date 2020-05-03 14:53
 */
public class Concatenation {
    public static void main(String[] args) {
        String mango = "mango";
        String s = "abc" + mango + "def" + 47;
        System.out.println(s);
    }
}
