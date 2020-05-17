package a.javalogic.chapter1;

import java.util.Scanner;

/**
 *
 * @author nuc8
 * @date 2020/5/17 10:33 上午
 */
public class DoWhileDemo {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String password = "123456";
        String next;
        do {
            System.out.println("please input password");
            next = reader.next();
        } while (!password.equalsIgnoreCase(next));
        System.out.println("correct");
        reader.close();
    }
}
