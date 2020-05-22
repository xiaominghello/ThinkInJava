package a.javalogic.chapter5;

import java.util.Arrays;

/**
 * 这里演示的是对Point数组的操作，实际上可以针对任何实现了MyComparable接口的类型数组进行操作。
 * 这就是接口的威力。
 * 针对接口而非具体类型进行编程，是计算机程序的一种重要思维方式。
 * 接口很多时候反映了对象以及对对象操作的本质。
 * 优点：1.代码复用，同一套代码可以处理多种不同类型的对象，只要这些对象都有相同的能力
 *      2.更重要的是降低了耦合，提高了灵活性
 *
 * @author nuc8
 * @date 2020/5/21 8:34 上午
 */
public class CompUtil {
    /**
     * 获取传入数组中的最大值
     * @param objs
     * @return
     */
    public static Object max(Mycomparable[] objs) {
        if (objs == null || objs.length == 0) {
            return null;
        }
        Mycomparable max = objs[0];
        for (int i = 1; i < objs.length; i++) {
            if (max.compareTo(objs[i]) < 0) {
                max = objs[i];
            }
        }
        return max;
    }

    /**
     * 对数组生序排序，简单排序选择
     * @param objs
     */
    public static void sort(Mycomparable[] objs) {
        for (int i = 0; i < objs.length; i++) {
            int min = i;
            for (int j = i + 1; j < objs.length; j++) {
                if (objs[j].compareTo(objs[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                Mycomparable temp = objs[i];
                objs[i] = objs[min];
                objs[min] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Point[] points = new Point[]{new Point(2, 3), new Point(3, 4), new Point(1, 2)};
        System.out.println("max: " + CompUtil.max(points));
        CompUtil.sort(points);
        System.out.println("sort: " + Arrays.toString(points));

    }
}
