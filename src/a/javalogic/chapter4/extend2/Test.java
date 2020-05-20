package a.javalogic.chapter4.extend2;

/**
 * 基类静态代码块,s: 0
 * 子类静态代码块,a: 0
 * 基类实例代码块,a: 0
 * 基类构造方法,a: 1
 * 子类实例代码块,a: 0
 * 子类构造方法,a: 10
 *
 * @author nuc8
 * @date 2020/5/19 5:24 下午
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("---- new Chile()");
        Child c = new Child();
        System.out.println("\n---- c.action()");
        c.action();
        Base b = c;
        System.out.println("\n---- b.action()");
        b.action();
        System.out.println("\n---- b.s: " + b.s);
        System.out.println("\n---- c.s: " + c.s);
    }
}
