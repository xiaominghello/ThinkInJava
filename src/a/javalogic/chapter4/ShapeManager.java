package a.javalogic.chapter4;

import a.javalogic.chapter3.Point2;

/**
 * @author nuc8
 * @date 2020/5/19 2:27 下午
 */
public class ShapeManager {
    private static final int MAX_NUM = 100;
    private Shape[] shapes = new Shape[MAX_NUM];
    private int shapeNum = 0;

    public void addShape(Shape shape) {
        if (shapeNum < MAX_NUM) {
            shapes[shapeNum++] = shape;
        }
    }

    public void draw() {
        for (int i = 0; i < shapeNum; i++) {
            shapes[i].draw();
            System.out.println(shapes[i].canCastLine(shapes[i]));
        }
    }

    public static void main(String[] args) {
        ShapeManager manager = new ShapeManager();
        // 子类对象赋值给父类引用变量，这叫向上转型，转换为父类类型
        // 变量Shape可以引用任何Shape子类类型的对象，这叫多态
        manager.addShape(new Circle(new Point2(3, 4), 3));
        manager.addShape(new Line("green", new Point2(0,1), new Point2(3, 3)));
        manager.addShape(new ArrowLine("red", new Point2(3, 3), new Point2(6, 5), true, false));
        manager.draw();
    }
}
