package net.mindview.util;

import java.util.Arrays;
import java.util.Collection;

/**
 * 格式化换行打印容器
 *
 * 容器默认的toString()方法会在单个行中打印容器中所有的元素，对于大型集合来说，这回变得难以阅读。
 *
 * @Author shenxiaowei
 * @Date 2020-05-02 11:48
 */
public class PPrint {
    public static String pformat(Collection<?> c) {
        if (c.size() == 0) {
            return "[]";
        }
        StringBuilder result = new StringBuilder("[");
        for (Object elem : c) {
            if (c.size() != 1) {
                result.append("\n  ");
            }
            result.append(elem);
        }
        if (c.size() != 1) {
            result.append("\n");
        }
        result.append("]");
        return result.toString();
    }

    public static void pprint(Collection<?> c) {
        System.out.println(pformat(c));
    }

    public static void pprint(Object[] c) {
        System.out.println(pformat(Arrays.asList(c)));
    }
}
