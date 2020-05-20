package a.javalogic.chapter4.extend2;

/**
 * @author nuc8
 * @date 2020/5/19 5:20 下午
 */
public class Child extends Base {
    public static int s;
    private int a;

    static {
        System.out.println("子类静态代码块,a: " + s);
        s = 10;
    }

    {
        System.out.println("子类实例代码块,a: " + a);
        a = 10;
    }

    public Child() {
        System.out.println("子类构造方法,a: " + a);
        a = 20;
    }

    @Override
    protected void step() {
        System.out.println("child s: " + s + ", a: " + a);
    }
}
