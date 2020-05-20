package a.javalogic.chapter4;

import a.javalogic.chapter3.Point;
import a.javalogic.chapter3.Point2;

/**
 * @author nuc8
 * @date 2020/5/18 9:35 下午
 */
public class ObjectTest {
    public static void main(String[] args) {
        Point point = new Point();
        // a.javalogic.chapter3.Point@266474c2
        // getClass().getName() + "@" + Integer.toHexString(hashCode());
        // 类名@哈希值的十六进制
        // Object并不知道具体对象的属性，不知道怎么用文本描述，但又要区分不同的对象，只能是写一个哈希值
        // 子类知道自己的属性，子类可以重写父类的的方法，以反映自己的不同实现。
        System.out.println(point.toString());

        Point2 point2 = new Point2();
        System.out.println(point2);
    }
}
