package a.javalogic.chapter4;

import a.javalogic.chapter3.Point2;

/**
 * @author nuc8
 * @date 2020/5/19 2:23 下午
 */
public class ArrowLine extends Line{
    private boolean startArrow;
    private boolean endArrow;

    public ArrowLine(String color, Point2 start, Point2 end, boolean startArrow, boolean endArrow) {
        super(color, start, end);
        this.startArrow = startArrow;
        this.endArrow = endArrow;
    }

    @Override
    public void draw() {
        super.draw();
        if (startArrow) {
            System.out.println("draw start arrow");
        }
        if (endArrow) {
            System.out.println("draw end arrow");
        }
    }

    public static void main(String[] args) {
        Point2 startPoint = new Point2(0, 0);
        Point2 endPoint = new Point2(3, 4);
        ArrowLine arrowLine = new ArrowLine("green", startPoint, endPoint, true, true);
        System.out.println(arrowLine.length());
        arrowLine.draw();
    }
}
