package strings.p283;

import java.util.Random;

/**
 * StringBuilder 是Java SE5引入的，在这之前Java用的是 StringBuffer。
 * 后者是线程安全的，因此开销也会大写。
 * 所有如果可能，建议优先采用该类，因为在大多数实现中，它比 StringBuffer 要快。
 *
 * 常用API：append(),toString(),insert(),replace(),substring(),reverse(),delete()
 *
 * @Author shenxiaowei
 * @Date 2020-05-03 15:04
 */
public class UseStringBuilder {
    public static Random random = new Random(47);

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        int count = 25;
        for (int i = 0; i < count; i++) {
            result.append(random.nextInt(100));
            result.append(", ");
        }
        // 删除最后一个逗号和空格
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }

    public static void main(String[] args) {
        UseStringBuilder usb = new UseStringBuilder();
        System.out.println(usb);
    }
}
