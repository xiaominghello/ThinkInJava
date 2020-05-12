package io.p533inputoutput;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 测试FileInputStream和FileOutputStream的使用
 *
 * 结论：
 * 1. 对于文本文件(.txt,.java,.c,.cpp)，使用字符流处理
 * 2. 对于非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt,...)，使用字节流处理
 *
 * @author nuc8
 * @date 2020/5/12 4:09 下午
 */
public class FileInputOutputStreamTest {

    /**
     * 使用字节流FileInputStream处理文本文件，可能出现乱码。
     * 中文字符占2个字节，非中文占一个字节
     * 当我们读取中文时，由于是字节流读取数据，这时候就会读到某个中文字符的一半，buffer满了，此时控制台输出就会产生乱码。
     * 如果用FileInputStream读取，用FileOutputStream输出，不控制台打印，就不会乱码
     */
    @Test
    public void testFileInputStream() {
        FileInputStream fis = null;
        try {
            File file = new File("src/io/p533inputoutput/hello.txt");
            fis = new FileInputStream(file);
            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                String s = new String(buffer, 0, len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 复制
     */
    @Test
    public void testFileInputOutputStream() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
//            File srcFile = new File("src/io/p533inputoutput/1.jpg");
//            File destFile = new File("src/io/p533inputoutput/2.jpg");

            File srcFile = new File("src/io/p533inputoutput/hello.txt");
            File destFile = new File("src/io/p533inputoutput/hello2.txt");

            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void copyFile(String srcPath, String destPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCopyFile() {
        String srcPath = "/Users/SXW/Downloads/漫画算法小灰的算法之旅[www.j9p.com].pdf";
        String destPath = "/Users/SXW/Downloads/漫画算法小灰的算法之旅.pdf";
        long start = System.currentTimeMillis();
        copyFile(srcPath, destPath);
        long end = System.currentTimeMillis();
        System.out.println("复制用时：" + (end - start) + "毫秒");
    }
}
