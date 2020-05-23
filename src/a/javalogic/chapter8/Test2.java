package a.javalogic.chapter8;

import org.junit.Test;

/**
 * 泛型方法
 *
 * @author nuc8
 * @date 2020/5/22 4:32 下午
 */
public class Test2 {
    public static <T> int indexOf(T[] arr, T elm) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elm)) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int i = Test2.indexOf(new Integer[]{1, 3, 5}, 10);
        System.out.println(i);
        i = Test2.indexOf(new String[]{"hello", "老马", "编程"}, "老马");
        System.out.println(i);
    }
}
