package a.javalogic.chapter4.extend3;

/**
 * @author nuc8
 * @date 2020/5/20 5:26 下午
 */
public class Child extends Base {
    private long sum;

    @Override
    public void add(int number) {
        super.add(number);
        sum += number;
    }

    @Override
    public void addAll(int[] numbers) {
        super.addAll(numbers);
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
    }

    public long getSum() {
        return sum;
    }

    public static void main(String[] args) {
        Child c = new Child();
        c.addAll(new int[]{1,2,3});
        // 12
        System.out.println(c.getSum());
    }
}
