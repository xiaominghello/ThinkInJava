package io.p525file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 进一步改写，定义一个作为list()参数的匿名内部类
 *
 * @Author shenxiaowei
 * @Date 2020-05-02 10:51
 */
public class DirList3 {
    public static void main(String[] args) {
        final String[] args2 = new String[]{"\\.\\w*"};
        File path = new File(".");
        String[] list;
        if (args2.length == 0) {
            list = path.list();
        } else {
            list = path.list(new FilenameFilter() {
                private Pattern pattern = Pattern.compile(args2[0]);
                @Override
                public boolean accept(File dir, String name) {
                    return pattern.matcher(name).matches();
                }
            });
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem :
                list) {
            System.out.println(dirItem);
        }
    }
}
