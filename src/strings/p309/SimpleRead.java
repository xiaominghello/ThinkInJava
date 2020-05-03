package strings.p309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * 到目前为止，从文件或标准输入读取数据还是一件相当痛苦的事情。
 * 一般的解决之道是读入一行本文，对其进行分词，然后使用Integer，Double等类的各种解析方法来解析数据
 *
 * @Author shenxiaowei
 * @Date 2020-05-03 21:33
 */
public class SimpleRead {
    public static BufferedReader input = new BufferedReader(
            new StringReader("Sir Robin of Camelot\n22 1.61803"));

    public static void main(String[] args) {
        try {
            System.out.println("What is your name");
            String name = input.readLine();
            System.out.println(name);
            System.out.println(
                    "How old are you? What is your favourite double?");
            System.out.println("(input: <age> <double>");
            String numbers = input.readLine();
            System.out.println(numbers);
            String[] numArray = numbers.split(" ");
            int age = Integer.parseInt(numArray[0]);
            double favorite = Double.parseDouble(numArray[1]);
            System.out.printf("Hi %s.\n", name);
            System.out.printf("In 5 years you will be %d.\n", age + 5);
            System.out.printf("My favourite double is %f.", favorite / 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
