package io.p533inputoutput;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 其他流的使用
 * 1.标准的输入、输出流 System.in System.out
 * 2.打印流 PrintStream 和PrintWriter 提供了一系列重载的print() 和 println()
 * 3.数据流 DataInputStream DataOutputStream
 *
 * @author nuc8
 * @date 2020/5/12 9:29 下午
 */
public class OtherStreamTest {
    /**
     *  1.标准的输入、输出流
     *     1.1
     *     System.in:标准的输入流，默认从键盘输入
     *     System.out:标准的输出流，默认从控制台输出
     *     1.2
     *     System类的setIn(InputStream is) / setOut(PrintStream ps)方式重新指定输入和输出的流。
     *
     *     1.3练习：
     *     从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续进行输入操作，
     *     直至当输入“e”或者“exit”时，退出程序。
     *
     *     方法一：使用Scanner实现，调用next()返回一个字符串
     *     方法二：使用System.in实现。System.in  --->  转换流 ---> BufferedReader的readLine()
     *
     * @param args
     */
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            while (true) {
                System.out.println("请输入字符串：");
                String s = br.readLine();
                if ("e".equalsIgnoreCase(s) || "exit".equalsIgnoreCase(s)) {
                    System.out.println("程序结束");
                    break;
                }
                s = s.toUpperCase();
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  把标准的输出流改成输入到文件
     *  标准输出流正常是输出到控制台
     *  通过设置指定输出流，可以把内容输入到指定的文件
     */
    @Test
    public void test2() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File("src/io/p533inputoutput/PrintStream.txt"));
            ps = new PrintStream(fos, true);
            if (ps != null) {
                System.setOut(ps);
            }
            for (int i = 0; i < 255; i++) {
                System.out.print(i + ",");
                if (i % 50 == 0) {
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    /**
     * 3. 数据流
     *     3.1 DataInputStream 和 DataOutputStream
     *     3.2 作用：用于读取或写出基本数据类型的变量或字符串
     *
     *     练习：将内存中的字符串、基本数据类型的变量写出到文件中。
     *
     */
    @Test
    public void test3() {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream("src/io/p533inputoutput/dataOutputStream.txt"));
            dos.writeUTF("我有一个梦想");
            dos.flush();
            dos.writeInt(18);
            dos.flush();
            dos.writeBoolean(true);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将文件中存储的基本数据类型变量和字符串读取到内存中，保存在变量中。
     *
     *     注意点：读取不同类型的数据的顺序要与当初写入文件时，保存的数据的顺序一致！
     */
    @Test
    public void test4() {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream("src/io/p533inputoutput/dataOutputStream.txt"));
            String name = dis.readUTF();
            int age = dis.readInt();
            boolean isMale = dis.readBoolean();
            System.out.println(name);
            System.out.println(age);
            System.out.println(isMale);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
