package io.p525file;

import net.mindview.util.Directory;

import java.io.File;
import java.io.IOException;

/**
 * 使用Directory递归查找指定目录下的指定后缀名的文件
 * 然后根据Strategy对象来处理这些目录中的文件（这是策略设计模式的另一个示例）
 * 此例的Strategy就是打印了文件名
 *
 * @Author shenxiaowei
 * @Date 2020-05-04 12:40
 */
public class ProcessFiles {
    public interface Strategy {
        void process(File file);
    }

    private Strategy strategy;
    private String ext;

    public ProcessFiles(Strategy strategy, String ext) {
        this.strategy = strategy;
        this.ext = ext;
    }

    public void start(String[] args) {
        try {
            if (args.length == 0) {
                processDirectoryTree(new File("."));
            } else {
                for (String arg : args) {
                    File fileArg = new File(arg);
                    if (fileArg.isDirectory()) {
                        processDirectoryTree(fileArg);
                    } else {
                        if (!arg.endsWith("." + ext)) {
                            arg += "." + ext;
                        }
                        strategy.process(new File(arg).getCanonicalFile());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processDirectoryTree(File root) throws IOException {
        for (File file :
                Directory.walk(root.getAbsolutePath(), ".*\\." + ext)) {
            strategy.process(file.getCanonicalFile());
        }
    }

    public static void main(String[] args) {
        new ProcessFiles(new ProcessFiles.Strategy() {
            @Override
            public void process(File file) {
                System.out.println(file);
            }
        }, "java").start(args);
    }
}
