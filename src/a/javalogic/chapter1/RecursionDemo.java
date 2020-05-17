package a.javalogic.chapter1;

/**
 * 递归函数：自己调用自己的函数
 * 计算数 n 的阶乘：
 * 0! = 1
 * n! = (n-1)! * n
 *
 * 递归函数形式上往往比较简单，但递归其实是有开销的，
 * 而且使用不当，可能会出现意外情况
 * 每一个函数调用，都需要分配额外的栈空间用于存储参数，局部变量以及返回地址，需要进行额外的入栈和出栈操作。
 * 栈空间不是无限的，如果递归的次数比较多，栈空间过深，就会抛出 Exception in thread "main" java.lang.StackOverflowError
 * 递归不可行的情况下怎么办呢？递归函数经常可以转换为非递归的形式，
 * 通过循环实现。
 *
 * @author nuc8
 * @date 2020/5/17 12:31 下午
 */
public class RecursionDemo {
    public static long factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static long factorial2(int n) {
        long result = 1;
        for (int i = 0; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(factorial2(100000));
        // Exception in thread "main" java.lang.StackOverflowError
        System.out.println(factorial(100000));
    }
}
