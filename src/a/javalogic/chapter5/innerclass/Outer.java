package a.javalogic.chapter5.innerclass;

/**
 * 静态内部类
 *
 * @author nuc8
 * @date 2020/5/22 8:13 上午
 */
public class Outer {
    private static int shared = 100;

    public static class StaticInner {
        public void innerMethod() {
            System.out.println("inner " + shared);
        }
    }

    public void test() {
        StaticInner si = new StaticInner();
        si.innerMethod();
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.test();

        Outer.StaticInner si = new Outer.StaticInner();
        si.innerMethod();
    }
}
