package a.javalogic.chapter7.random;

import org.junit.Test;

import java.util.Random;

/**
 * @author nuc8
 * @date 2020/5/22 2:27 下午
 */
public class RandomTest {

    @Test
    public void test() {
        // 种子决定了随机产生的序列，种子相同，产生的随机数序列就是相同的
        Random random = new Random(47);
        for (int i = 0; i < 5; i++) {
            // 种子是47，输出结果是 58 55 93 61 61、
            // 这个程序无论执行多少遍，在哪执行，输出结果都是相同的
            // 指定种子是为了实现可重复的随机
            System.out.print(random.nextInt(100) + " ");
        }
    }
}
