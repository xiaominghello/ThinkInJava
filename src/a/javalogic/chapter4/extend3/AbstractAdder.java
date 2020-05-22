package a.javalogic.chapter4.extend3;

/**
 * 抽象类和接口是配合而非替代关系，它们经常一起使用，接口声明能力，抽象类提供默认实现，
 * 实现全部或部分方法，一个接口经常有一个对应的抽象类。
 *
 * @author nuc8
 * @date 2020/5/21 4:23 下午
 */
public abstract class AbstractAdder implements IAdd{
    @Override
    public void addAll(int[] numbers) {
        for (int num : numbers) {
            add(num);
        }
    }
}
