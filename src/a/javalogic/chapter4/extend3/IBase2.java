package a.javalogic.chapter4.extend3;

/**
 * 对于需要实现接口的具体类而言，有两个选择：
 *  一个是现实接口，自己实现全部方法；
 *  另一个是继承抽象类，然后根据需要重写方法。继承的好处是复用代码，只重写需要的部分即可，需要编写的代码比较少，容易实现。
 *  不过如果这个具体类已经有父类了，那就只能选择实现接口了。
 *
 * @author nuc8
 * @date 2020/5/21 4:24 下午
 */
public class IBase2 extends AbstractAdder{
    private static final int MAX_NUM = 1000;
    private int[] arr = new int[MAX_NUM];
    private int count;

    @Override
    public void add(int number) {
        if (count < MAX_NUM) {
            arr[count++] = number;
        }
    }
}
