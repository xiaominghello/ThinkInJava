package io.p533inputoutput;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * 一、流的分类：
 * 1.操作数据单位：字节流、字符流
 * 2.数据的流向：输入流、输出流
 * 3.流的角色：节点流、处理流
 *
 * 二、流的体系结构
 * 抽象基类         节点流（或文件流）                               缓冲流（处理流的一种）
 * InputStream     FileInputStream   (read(byte[] buffer))        BufferedInputStream (read(byte[] buffer))
 * OutputStream    FileOutputStream  (write(byte[] buffer,0,len)  BufferedOutputStream (write(byte[] buffer,0,len) / flush()
 * Reader          FileReader (read(char[] cbuf))                 BufferedReader (read(char[] cbuf) / readLine())
 * Writer          FileWriter (write(char[] cbuf,0,len)           BufferedWriter (write(char[] cbuf,0,len) / flush()
 *
 *
 * @author nuc8
 * @date 2020/5/12 8:23 上午
 */
public class FileReaderWriterTest {

    /**
     *  说明点：
     *     1. read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1
     *     2. 异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
     *     3. 读入的文件一定要存在，否则就会报FileNotFoundException。
     *
     *     一个一个字符读取
     */
    @Test
    public void testFileReader() {
        FileReader fr = null;
        try {
            // 单元测试下的相对路径是相对于module
            File file = new File("src/io/p533inputoutput/hello.txt");
            fr = new FileReader(file);
            int data;
            while ((data = fr.read()) != -1) {
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用char[]读取
     */
    @Test
    public void testFileReader2() {
        FileReader fr = null;
        try {
            File file = new File("src/io/p533inputoutput/hello.txt");
            fr = new FileReader(file);
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
//                for (int i = 0; i < cbuf.length; i++) {
//                    System.out.print(cbuf[i]);
//                }
                for (int i = 0; i < len; i++) {
                    System.out.print(cbuf[i]);
                }
//                String s = new String(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  从内存中写出数据到硬盘的文件里。
     *
     *     说明：
     *     1. 输出操作，对应的File可以不存在的。并不会报异常
     *     2. File对应的硬盘中的文件如果不存在，在输出的过程中，会自动创建此文件。
     *          File对应的硬盘中的文件如果存在：
     *                 如果流使用的构造器是：FileWriter(file,false) / FileWriter(file):对原有文件的覆盖
     *                 如果流使用的构造器是：FileWriter(file,true):不会对原有文件覆盖，而是在原有文件基础上追加内容
     *
     */
    @Test
    public void testFileWriter() {
        FileWriter fw = null;
        try {
            File file = new File("src/io/p533inputoutput/hello.txt");
            fw = new FileWriter(file, true);
            fw.write("I have a dream!\n");
            fw.write("我有一个梦想!\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 输入输出实现复制
     */
    @Test
    public void testFileReaderWriter() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            File srcFile = new File("src/io/p533inputoutput/hello.txt");
            File destFile = new File("src/io/p533inputoutput/hello2.txt");

            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                fw.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
