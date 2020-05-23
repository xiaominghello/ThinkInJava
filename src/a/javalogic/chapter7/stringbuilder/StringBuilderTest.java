package a.javalogic.chapter7.stringbuilder;

import org.junit.Test;

/**
 * @author nuc8
 * @date 2020/5/22 11:12 上午
 */
public class StringBuilderTest {
    @Test
    public void test() {
        StringBuilder sb = new StringBuilder();
        // 扩展策略：乘2再加2，扩展后长度还不够，才使用count+len（内部数组长度+添加字符串的长度）
        // 在不知道最终需要多长的情况下，指数扩展是一种常见的策略，广泛应用于各种内存分配相关的计算机程序中。
        sb.append("老马说编程");
        sb.append("，探索编程本质");
        // 基于内部数组新建了一个String，
        // 注意，这个String构造方法不会直接用value数组，而会新建一个，
        // new String(value, 0, count);
        // 以保证String的不可变性。
        System.out.println(sb.toString());

        // string 的 + 和 += 运算符，Java编译器会转化为StringBuilder的append
        // 在循环内部，每一次+=操作，都会生成一个StringBuilder
        // 所以在复杂的情况，尤其是有循环的时候，应该直接使用StringBuilder
        String hello = "hello";
        for (int i = 0; i < 3; i++) {
            hello += ",world";
        }
        System.out.println(hello);
    }
}
