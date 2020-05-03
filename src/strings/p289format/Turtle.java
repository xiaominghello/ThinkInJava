package strings.p289format;

import java.io.PrintStream;
import java.util.Formatter;

/**
 * Formatter类
 * 可以将Formatter看作一个翻译器，它将你的格式化字符串与数据翻译成需要的结果。
 * 当你创建一个Formatter对象的时候，需要向其构造器传递一些信息，告诉它最终的结果将向哪里输出
 *
 * @Author shenxiaowei
 * @Date 2020-05-03 20:17
 */
public class Turtle {
    private String name;
    private Formatter f;

    public Turtle(String name, Formatter f) {
        this.name = name;
        this.f = f;
    }

    public void move(int x, int y) {
        f.format("%s The Turtle is at (%d,%d)\n", name, x, y);
    }

    public static void main(String[] args) {
        PrintStream outAlias = System.out;
        // 输出到System.out
        // 最常用的输出目的地：PrintStream, OutputStream, File
        Turtle tommy = new Turtle("Tommy", new Formatter(System.out));
        Turtle terry = new Turtle("Terry", new Formatter(outAlias));
        tommy.move(0,0);
        terry.move(4, 8);
        tommy.move(3,4);
        terry.move(2, 5);
        tommy.move(3, 3);
        terry.move(3, 3);
    }
}
