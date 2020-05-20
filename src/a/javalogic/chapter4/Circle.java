package a.javalogic.chapter4;

import a.javalogic.chapter3.Point2;

/**
 * @author nuc8
 * @date 2020/5/18 9:46 下午
 */
public class Circle extends Shape{
    private Point2 center;
    private double r;

    public Circle(Point2 center, double r) {
        this.center = center;
        this.r = r;
    }

    public double area() {
        return Math.PI * r * r;
    }

    @Override
    public void draw() {
        System.out.println("draw circle at " + center.toString() + " with r " + r + ", using color : " + getColor() );
    }

    public static void main(String[] args) {
        Point2 center = new Point2(3, 4);
        Circle circle = new Circle(center, 5);
        // draw circle at (3+4) with r 5.0, using color : black
        // 这里比较奇怪的是，color是什么时候赋值的？
        // 在new的过程中，父类的构造方法也会执行，且会优先于子类执行。
        circle.draw();
        System.out.println(circle.area());
    }
}
