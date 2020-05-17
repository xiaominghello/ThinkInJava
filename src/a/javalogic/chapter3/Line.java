package a.javalogic.chapter3;

/**
 * @author nuc8
 * @date 2020/5/17 10:20 下午
 */
public class Line {
    private Point2 start;
    private Point2 end;

    public Line(Point2 start, Point2 end) {
        this.start = start;
        this.end = end;
    }

    public double length() {
        return start.distance(end);
    }

    public static void main(String[] args) {
        Point2 start = new Point2(0, 0);
        Point2 end = new Point2(3, 4);
        Line line = new Line(start, end);
        System.out.println(line.length());
    }
}
