package a.javalogic.chapter1;

/**
 * break 用于提前结束循环
 * @author nuc8
 * @date 2020/5/17 10:52 上午
 */
public class BreakDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int toSearch = 3;
        int i = 0;
        for (; i < arr.length; i++) {
            if (arr[i] == toSearch) {
                break;
            }
        }
        if (i != arr.length) {
            System.out.println(i);
            System.out.println("found");
        } else {
            System.out.println("not found");
        }
    }
}
