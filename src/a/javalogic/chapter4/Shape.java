package a.javalogic.chapter4;

/**
 * @author nuc8
 * @date 2020/5/18 9:42 下午
 */
public class Shape {
    private static final String DEFAULT_COLOR = "black";
    private String color;

    public Shape() {
        this(DEFAULT_COLOR);
    }

    public Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void draw() {
        System.out.println("draw shape");
    }

    public boolean canCastLine(Shape shape) {
        return shape instanceof Line;
    }
}
