package a.javalogic.chapter3;

/**
 * @author nuc8
 * @date 2020/5/17 4:08 下午
 */
public class Point {
    public int x;
    public int y;

    public double distance() {
        return Math.sqrt(x * x + y * y);
    }

    public static void main(String[] args) {
        Point p = new Point();
        p.x = 4;
        p.y = 3;
        System.out.println(p.distance());
    }
}


