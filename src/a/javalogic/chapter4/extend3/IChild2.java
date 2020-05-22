package a.javalogic.chapter4.extend3;

/**
 * @author nuc8
 * @date 2020/5/21 4:25 下午
 */
public class IChild2 {
    private IBase2 iBase2;
    private long sum;

    public IChild2() {
        iBase2 = new IBase2();
    }

    public void add(int number) {
        iBase2.add(number);
        sum += number;
    }

    public void addAll(int[] numbers) {
        iBase2.addAll(numbers);
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
    }

    public long getSum() {
        return sum;
    }

    public static void main(String[] args) {
        IChild2 ic2 = new IChild2();
        ic2.addAll(new int[]{1,2,3});
        System.out.println(ic2.getSum());
    }
}
