package a.javalogic.chapter5.innerclass;

import a.javalogic.chapter3.Point2;

/**
 * 匿名内部类
 *
 * @author nuc8
 * @date 2020/5/22 8:37 上午
 */
public class Outer4 {
    public void test(final int x, final int y) {
        Point2 p = new Point2(2, 3){
            @Override
            public double distance() {
                return distance(new Point2(x, y));
            }
        };
        System.out.println(p.distance());
    }

    public static void main(String[] args) {
        Outer4 outer4 = new Outer4();
        outer4.test(5, 5);
    }
}
