package a.javalogic.chapter7.wrapperclass;

import org.junit.Test;

/**
 * 包装类
 * 包装类是一个类，内部有一个实例变量，保存对于的基本类型的值，
 * 这个类一般还有一些静态方法，静态变量和实例方法，以方便对数据进行操作。
 *
 * 包装类都是不可变类，所谓不可变类是指实例对象一旦创建，就没有办法修改了。通过如下方式强制实现：
 * 1。所有包装类都是声明为了final，不能被继承
 * 2。内部基本类型值是私有的，且声明为了final
 * 3。没有定义setter方法
 * 不可变使得程序更为简单安全，尤其是多线程的环境下。
 *
 * @author nuc8
 * @date 2020/5/22 10:27 上午
 */
public class WrapperClassTest {

    @Test
    public void test() {
        boolean b = false;
        // 包装类.valueOf 装箱
        Boolean bobj1 = Boolean.valueOf(b);
        // xxxValue  拆箱
        boolean b1 = bobj1.booleanValue();

        // Java1.5以后引入了自动装箱和自动拆箱
        Boolean bobj2 = b;
        // xxxValue  拆箱
        boolean b2 = bobj2;

        // 每种包装类也有构造函数
        // new 每次都会创建一个新对象，而除了Float和Double外的其他包装类，都会缓存包装类对象
        // 减少使用new 会节省空间，提升性能。
        // Java9开始，这些构造方法被标记为过时了。
        // 推荐使用静态的valueOf()方法
        Boolean aFalse = new Boolean("false");
        System.out.println(aFalse.booleanValue());
    }
}
