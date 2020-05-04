package net.mindview.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 目录实用工具
 * local() 方法产生由本地目录中的文件构成的File对象数组
 * walk()  方法产生给的目录下由整个目录树中所有文件构成的List<File>
 *
 *
 * @Author shenxiaowei
 * @Date 2020-05-02 11:39
 */
public final class Directory {
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, final String regex) {
        return local(new File(path), regex);
    }

    // A two-tuple for returning a pair of objects:
    // 用于返回一对对象的两个元组
    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<>();
        public List<File> dirs = new ArrayList<>();

        // The default iterable element is the file list:
        // 默认的可迭代元素是文件列表：
        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            // addAll(Collection<? extends E> c)
            // 添加指定 collection 中的所有元素到此列表的结尾，顺序是指定 collection 的迭代器返回这些元素的顺序（可选操作）。
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        @Override
        public String toString() {
            return "dirs: " + PPrint.pformat(dirs) +
                    "\n\nfiles: " + PPrint.pformat(files);
//            return String.format("dirs: %s\n\nfiles: %s", dirs.toString(), files.toString());
        }
    }

    public static TreeInfo walk(String start, String regex) {
        return recurseDirs(new File(start), regex);
    }

    public static TreeInfo walk(File start, String regex) {
        return recurseDirs(start, regex);
    }

    public static TreeInfo walk(File start) {
        return recurseDirs(start, ".*");
    }

    public static TreeInfo walk(String start) {
        return recurseDirs(new File(start), ".*");
    }

    // recurse 递归
    static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        for (File item : startDir.listFiles()) {
            if (item.isDirectory()) {
                // add(E e)
                // 向列表的尾部添加指定的元素（可选操作）。
                result.dirs.add(item);
                // 递归
                result.addAll(recurseDirs(item, regex));
            } else {
                if (item.getName().matches(regex)) {
                    result.files.add(item);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(walk("."));
        } else {
            for (String arg : args) {
                System.out.println(walk(arg));
            }
        }
    }
}
