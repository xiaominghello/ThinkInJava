package a.javalogic.chapter4.extend3;

/**
 * 父类提供了2个方法，将输入的数字添加到内部数组中
 * 对使用者来说，add和addAll就是能够添加数字，具体是怎么添加的，不用关系
 *
 * @author nuc8
 * @date 2020/5/20 5:24 下午
 */
public class Base {
    private static final int MAX_NUM = 1000;
    private int[] arr = new int[MAX_NUM];
    private int count;

    public void add(int number) {
        if (count < MAX_NUM) {
            arr[count++] = number;
        }
    }

    public void addAll(int[] numbers) {
        for (int num : numbers) {
            add(num);
        }
//        for (int num : numbers) {
//            if (count < MAX_NUM) {
//                arr[count++] = num;
//            }
//        }
    }

    public void clear() {
        for (int i = 0; i < count; i++) {
            arr[i] = 0;
        }
        count = 0;
    }
}
