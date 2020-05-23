package a.javalogic.chapter5.innerclass;

/**
 * 成员内部类
 *
 * @author nuc8
 * @date 2020/5/22 8:19 上午
 */
public class Outer2 {
    private int a = 100;
    public class Inner {
        public void innerMethod() {
            System.out.println("outer2 a " + a);
            Outer2.this.action();

        }
    }

    private void action() {
        System.out.println("action");
    }

    public void test() {
        Inner inner = new Inner();
        inner.innerMethod();
    }

    public static void main(String[] args) {
        Outer2 outer2 = new Outer2();
        outer2.test();

        Inner inner = outer2.new Inner();
        inner.innerMethod();
    }
}
