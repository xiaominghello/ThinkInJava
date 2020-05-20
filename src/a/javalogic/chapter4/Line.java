package a.javalogic.chapter4;

import a.javalogic.chapter3.Point2;

/**
 * super 的使用与 this 有点像，但 super 和 this 是不同的，
 * this 引用一个对象，是实实在在存在的，可以作为函数参数，可以作为返回值，
 * 但 super 只是一个关键字，不能作为参数和返回值，它只是用于告诉编译器访问父类的相关变量和方法。
 *
 * @author nuc8
 * @date 2020/5/18 10:14 下午
 */
public class Line extends Shape{
    private Point2 start;
    private Point2 end;

    public Line(String color, Point2 start, Point2 end) {
        // 表示调用父类的带 color 参数的构造方法。调用父类构造方法是，super 必须放在第一行
        super(color);
        this.start = start;
        this.end = end;
    }

    public double length() {
        return start.distance(end);
    }

    public Point2 getStart() {
        return start;
    }

    public Point2 getEnd() {
        return end;
    }

    @Override
    public void draw() {
        System.out.println("draw line from " + start.toString() + " to " + end.toString() + " ,using color " +
                // 表示调用父类的 getColor 方法，当然不写 super.也是可以的，因为这个方法子类没有同名的，没有歧义，当有歧义的时候，
                // 通常 super.可以明确的表示调用父类的方法
                super.getColor());
    }

    public static void main(String[] args) {
        Point2 start = new Point2(0, 0);
        Point2 end = new Point2(3, 4);
        Line line = new Line("red", start, end);
        System.out.println(line.length());
        line.draw();
    }
}