package a.javalogic.chapter4.extend3;

/**
 *  继续至少有两个好处：一是复用代码，另一个是利用多态和动态绑定统一处理多种不同子类的对象。
 *  使用组合替代继续，可以复用代码，但不能统一处理。
 *  使用接口替代继续，针对接口编程，可以实现统一处理不同类型的对象，但接口没有代码实现，无法复用代码。
 *
 *  将组合和接口结合起来替代继续，就既可以统一处理，又可以复用代码了。
 *
 * @author nuc8
 * @date 2020/5/21 4:08 下午
 */
public class IChild {
    private IBase iBase;
    private long sum;

    public IChild() {
        iBase = new IBase();
    }

    public void add(int number) {
        iBase.add(number);
        sum += number;
    }

    public void addAll(int[] numbers) {
        iBase.addAll(numbers);
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
    }

    public long getSum() {
        return sum;
    }

    public static void main(String[] args) {
        IChild ic = new IChild();
        ic.addAll(new int[]{1,2,3});
        System.out.println(ic.getSum());
    }
}
