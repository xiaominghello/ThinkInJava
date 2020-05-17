package a.javalogic.chapter1;

/**
 * 可变参数：适用于参数个数不确定，类型确定的情况，java把可变参数当做数组处理。
 * 可变长度参数的语法是在数据类型后面加 ...
 * 在函数内，可变参数可以看作是数组。
 * 可变长度参数必须是参数列表中的最后一个，一个函数也只能有一个可变长度的参数。
 *
 * 在main函数调用max(0, 2, 4, 5)的时候，实际会转换为调用max(0, new int[]{2, 4, 5})
 * 使用可变长度参数主要是简化了代码书写。
 *
 * @author nuc8
 * @date 2020/5/17 12:15 下午
 */
public class varargs {
    public static int max(int min, int... a) {
        int max = min;
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(max(0));
        System.out.println(max(0, 2));
        System.out.println(max(0, 2, 4));
        System.out.println(max(0, 2, 4, 5));
    }
}
