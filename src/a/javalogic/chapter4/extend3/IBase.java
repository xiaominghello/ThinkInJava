package a.javalogic.chapter4.extend3;

/**
 * @author nuc8
 * @date 2020/5/21 4:05 下午
 */
public class IBase implements IAdd {
    private static final int MAX_NUM = 1000;
    private int[] arr = new int[MAX_NUM];
    private int count;

    @Override
    public void add(int number) {
        if (count < MAX_NUM) {
            arr[count++] = number;
        }
    }

    @Override
    public void addAll(int[] numbers) {
        for (int num : numbers) {
            add(num);
        }
    }
}
