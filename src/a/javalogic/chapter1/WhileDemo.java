package a.javalogic.chapter1;

import java.util.Scanner;

/**
 * @author nuc8
 * @date 2020/5/17 10:13 上午
 */
public class WhileDemo {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("please input password");
        // 输入不是整数，则报错
        // Exception in thread "main" java.util.InputMismatchException
        int num = reader.nextInt();
        int password = 123456;
        while (num != password) {
            System.out.println("please input password");
            num = reader.nextInt();
        }
        System.out.println("correct");
        reader.close();
    }
}
