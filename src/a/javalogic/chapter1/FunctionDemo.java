package a.javalogic.chapter1;

/**
 * 函数定义
 * 修饰符 返回值类型 函数名字(参数类型 参数名字, ...) {
 *     操作;
 *     return 返回值;
 * }
 * 函数调用
 * 函数名字(参数类型 参数名字, ...);
 * 函数名字();
 * 参数传递实际上是给参数赋值。
 *
 * 程序从main函数开始执行，碰到函数调用的时候，会跳转进函数内部，
 * 函数调用了其他函数，会接着进入其他函数，函数返回后会继续执行调用后面的语句，
 * 返回到main函数并且main函数没有要执行的语句后程序结束。
 *
 * 函数重栽：
 *    同一个类里，函数可以重名，但是参数不能完全一样，即要么参数个数不同，
 *    要么参数个数相同但至少有一个参数类型不一样。
 *
 * @author nuc8
 * @date 2020/5/17 11:52 上午
 */
public class FunctionDemo {
    public static int sum(int a, int b) {
        return a + b;
    }

    public static void print3Lines() {
        int lines = 3;
        for (int i = 0; i < lines; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int a = 3;
        int b = 3;
        int sum = sum(a, b);
        System.out.println(sum);
        print3Lines();
        System.out.println(sum);
    }
}
