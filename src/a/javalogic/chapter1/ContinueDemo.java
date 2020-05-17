package a.javalogic.chapter1;

/**
 * continue 跳过循环天中剩下的代码，然后执行步进操作。
 * 例子：统计一个数组中某个元素的个数
 * @author nuc8
 * @date 2020/5/17 10:58 上午
 */
public class ContinueDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 5, 6};
        int toSearch = 3;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            // 如果值不等于toSearch，则跳过剩下的循环代码，执行i++
            // 也可以不用continue，使用if也可以
            // 如果类似要跳过的情况比较多，使用continue可能会更易读
            if (arr[i] != toSearch) {
                continue;
            }
            count++;
        }
        System.out.println("found count " + count);
    }
}
