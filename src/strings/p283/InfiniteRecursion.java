package strings.p283;

import java.util.ArrayList;

/**
 * 无限递归
 * 打印出对象的内存地址
 *
 * @Author shenxiaowei
 * @Date 2020-05-03 15:40
 */
public class InfiniteRecursion {
    @Override
    public String toString() {
//        return " InfiniteRecursion address: " + this + "\n";
        return " InfiniteRecursion address: " + super.toString() + "\n";
    }

    public static void main(String[] args) {
        ArrayList<InfiniteRecursion> v = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            v.add(new InfiniteRecursion());
        }
        System.out.println(v);
    }
}
