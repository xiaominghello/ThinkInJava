package a.javalogic.chapter7.arrays;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author nuc8
 * @date 2020/5/22 1:34 下午
 */
public class ArraysTest {
    @Test
    public void test() {
        // 1.toString
        int[] arr = {8, 2, 2, 3};
        System.out.println(arr);
        System.out.println(Arrays.toString(arr));
        String[] strArr = {"hello", "world"};
        System.out.println(strArr);
        System.out.println(Arrays.toString(strArr));
    }

    @Test
    public void test2() {
        // 2.排序
        int[] arr = {8, 2, 4, 3};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        String[] arr2 = {"cb", "Cb", "aa"};
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr2));
        Arrays.sort(arr2, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(arr2));
        Arrays.sort(arr2, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        System.out.println(Arrays.toString(arr2));
    }

    @Test
    public void test3() {
        // 3.查找
        int[] arr = {3, 5, 7, 12, 21};
        System.out.println(Arrays.binarySearch(arr, 12));
    }
}
