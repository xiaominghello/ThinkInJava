package strings.p309;

import java.util.Scanner;

/**
 * Java SE5 新增了Scanner类，它可以大大减轻扫描输入的工作负担。
 * Scanner的构造器可以接受任何类型的输入对象。
 * 有了Scanner，所有的输入，分词以及翻译的操作都隐藏在不同类型的next方法中。
 *
 * @Author shenxiaowei
 * @Date 2020-05-03 21:42
 */
public class Betterread {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(SimpleRead.input);
        System.out.println("What is your name");
        String name = stdin.nextLine();
        System.out.println(name);
        System.out.println(
                "How old are you? What is your favourite double?");
        System.out.println("(input: <age> <double>");
        int age = stdin.nextInt();
        double favorite = stdin.nextDouble();
        System.out.println(age);
        System.out.println(favorite);
        System.out.printf("Hi %s.\n", name);
        System.out.printf("In 5 years you will be %d.\n", age + 5);
        System.out.printf("My favourite double is %f.", favorite / 2);

    }
}
