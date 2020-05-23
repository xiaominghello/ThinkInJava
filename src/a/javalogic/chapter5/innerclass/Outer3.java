package a.javalogic.chapter5.innerclass;

/**
 * 方法内部类
 *
 * @author nuc8
 * @date 2020/5/22 8:32 上午
 */
public class Outer3 {
    private int a = 100;

    public void test(final int param) {
        final String str = "hello";
        class Inner{
            public void innerMethod() {
                System.out.println("outer a " + a);
                System.out.println("param " + param);
                System.out.println("local str " + str);
            }
        }
        Inner inner = new Inner();
        inner.innerMethod();
    }

    public static void main(String[] args) {
        Outer3 outer3 = new Outer3();
        outer3.test(1);
    }
}
