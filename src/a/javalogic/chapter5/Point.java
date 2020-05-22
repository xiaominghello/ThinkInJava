package a.javalogic.chapter5;

/**
 * 一个类可以实现多个接口，表面类的对象具备多种能力
 * @author nuc8
 * @date 2020/5/21 8:25 上午
 */
public class Point implements Mycomparable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public int compareTo(Object other) {
        if (!(other instanceof Point)) {
            throw new IllegalArgumentException();
        }
        Point otherPoint = (Point)other;
        double delta = distance() - otherPoint.distance();
        if (delta < 0) {
            return -1;
        } else if (delta > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public static void main(String[] args) {
        Mycomparable p1 = new Point(1, 1);
        Mycomparable p2 = new Point(2, 2);
        System.out.println(p1.compareTo(p2));
    }

    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }
}
