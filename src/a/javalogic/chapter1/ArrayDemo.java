package a.javalogic.chapter1;

/**
 * 数组作为参数与基本类型是不一样的，基本类型不会对调用者中的变量造成任何影响，
 * 但数组不是，在函数内部修改数组中的元素会修改调用者中的数组内容。
 *
 * 这个其实也容易理解，一个数组变量有两块空间，一块用于存储数组内容本身，
 * 另一块用于存储内容的位置，给数组变量赋值不会影响原有的数组内容本身，
 * 而只会让数组变量指向一个不同的数组内容空间。
 *
 * 在此例中，函数参数中的数组变量arr和main函数中的数组变量arr存储的都是相同的位置，
 * 而数组内容本身只有一份数据，所以，在reset中修改数组元素内容和main中修改是完全一样的。
 *
 * @author nuc8
 * @date 2020/5/17 12:02 下午
 */
public class ArrayDemo {
    public static void reset(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40};
        reset(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
