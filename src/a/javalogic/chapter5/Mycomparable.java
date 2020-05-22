package a.javalogic.chapter5;

/**
 * 接口：
 * 当类型并不重要，重要的是能力的情况下，使用接口
 *
 * 接口的继承：
 * 接口可以多继承
 *
 * @author nuc8
 * @date 2020/5/21 8:19 上午
 */
public interface Mycomparable extends IBase1,IBase2  {
    /**
     * 接口的变量：
     * 不需要加修饰符，加不加都是public static final
     */
    int A = 0;

    /**
     * 接口的方法：
     * 不需要加修饰符，加不加都是public abstract
     *
     * 和另一个对象比较
     * @param other 另一个参与比较的对象
     * @return -1 表示自己小于参数对象，0表示相同，1表示自己大于参数对象
     */
    int compareTo(Object other);
}

