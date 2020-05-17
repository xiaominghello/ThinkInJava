package a.javalogic.chapter3;

/**
 * @author nuc8
 * @date 2020/5/17 4:12 下午
 */
public class Point2 {
    /**
     * 与方法内定义的局部变量不同，在创建对象的时候，所有的实例变量都会分配一个默认值。
     */
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distance() {
        return Math.sqrt(x * x + y * y);
    }

    public double distance(Point2 p) {
        return Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2));

    }

    /**
     * 构造方法隐含的返回值就是实例本身
     *
     * @param x x
     * @param y y
     */
    public Point2(int x, int y) {
        // this表示当前实例，可以通过this访问实例变量
        this.x = x;
        this.y = y;
    }

    /**
     * 构造方法如果没什么操作要做，可以省略。
     * Java编译器会自动生成一个默认构造方法，也没有具体操作。
     * 但一旦定义了构造方法，Java就不会再自动生成默认的。
     */
    public Point2() {
        // this的第二个用法，用于在构造方法中调用其他构造方法。
        this(0, 0);
    }

    public static void main(String[] args) {
        Point2 point2 = new Point2();
        point2.setX(3);
        point2.setY(4);
        System.out.println(point2.distance());

        Point2 point21 = new Point2(6, 8);
        System.out.println(point21.distance());
    }
}
