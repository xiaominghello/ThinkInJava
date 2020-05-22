package a.javalogic.chapter4.extend3;

/**
 * 继承破坏封装性
 * 1。
 * 子类重写了基类的add和addAll方法，在添加数字的同时汇总数字，存储数字的和到实力变量sum中
 * 在使用addAll添加1，2，3，期望的输出是6，实际输出却是12
 * 可以看出，如果子类不知道基类方法的实现细节，它就不能正确地进行扩展。
 * 2。
 * 注释掉子类allAll中的遍历汇总语句后，输出为6
 * 3。
 * 但是如果基类决定修改addAll方法的实现,那么此时输出值6变成了0
 *
 * 从这个例子可以看出，子类和父类之间是细节依赖，子类扩展父类，仅仅知道父类能做什么是不够的，
 * 还需要知道父类是怎么做的，而父类的实现细节也不能随意修改，否则可能影响子类
 *
 * 即使这个依赖关系不变，封装还是可能被破坏
 * 基类Base添加一个clear方法，Child类自动拥有这个方法，
 * 然后先添加一次1，2，3，然后调用clear，再添加1，2，3，期望结果是6，输出却是12，
 * 因为Child没有重写clear方法
 *
 * 可以看出，父类不能随意增加公开方法，因为给父类增加就是给所有子类增加，
 * 而子类可能必须要重写该方法才能确保方法的正确性。
 *
 * 结论：
 * 对于子类而言，通过继续实现是没有安全保障的，因为父类修改内部实现细节，它的功能就可能会被破坏
 * 对于基类而言，让子类继续和重写方法，就可能丧失随意修改内部实现的自由。
 *
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
//        for (int i = 0; i < numbers.length; i++) {
//            sum += numbers[i];
//        }
    }

    @Override
    public void clear() {
        super.clear();
        this.sum = 0;
    }

    public long getSum() {
        return sum;
    }

    public static void main(String[] args) {
        Child c = new Child();
        c.addAll(new int[]{1,2,3});
        // 12
        System.out.println(c.getSum());

        c.clear();
        c.addAll(new int[]{1,2,3});
        System.out.println(c.getSum());
    }
}
