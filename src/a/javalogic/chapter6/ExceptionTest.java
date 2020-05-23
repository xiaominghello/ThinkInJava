package a.javalogic.chapter6;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

/**
 * 出现异常，就启用异常处理机制，首先创建一个异常对象，这是里类NullPointerException的对象，
 * 然后查找看谁能处理这个异常，在示例代码中，没有代码能处理这个异常，因此Java启用默认处理机制，
 * 即打印 异常栈信息 到屏幕，并推出程序。
 *
 * 异常栈信息就包括了从异常发生点到最上层调用者的轨迹，还包括行号。
 *
 * @author nuc8
 * @date 2020/5/22 9:41 上午
 */
public class ExceptionTest {
    public static void main(String[] args) {
        test();
        System.out.println("end");

    }

    public static void test(){
        try {
            String s = null;
            s.indexOf("a");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        // RuntimeException及其子类，Error及其子类 实际是未受检异常
//        throw new NullPointerException();
        // Exception的其他子类和Exception自身是受检异常
        // 区别在于，对于受检异常，Java会强制要求程序员进行处理，否则会有编译错误。要么声明抛出，要么捕获
        // 对于受检异常，不可以抛出而不声明(throws),但可以声明抛出实际不不抛出
//        throw new Exception();
    }
}
