package strings.p289format;

import java.util.Formatter;

/**
 * 格式化说明符
 * %[argument_index$][flags][width][.precision]conversion
 *
 * 可选的 argument_index 是一个十进制整数，用于表明参数在参数列表中的位置。第一个参数由 "1$" 引用，第二个参数由 "2$" 引用，依此类推。
 *
 * 可选 flags 是修改输出格式的字符集。有效标志集取决于转换类型。 默认情况下，数据右对齐，"-"标志用来改变对齐方向
 *
 * 可选 width 是一个非负十进制整数，表明要向输出中写入的最少字符数。
 *
 * 可选 precision 是一个非负十进制整数，通常用来限制字符数。特定行为取决于转换类型。
 * 应用于String时，表示打印String时输出字符的最大数量
 * 应用于浮点数时，则表示显示的小数位数
 * 应用于整数，则报错
 *
 * 所需 conversion 是一个表明应该如何格式化参数的字符。给定参数的有效转换集取决于参数的数据类型。
 *
 * @Author shenxiaowei
 * @Date 2020-05-03 20:27
 */
public class Receipt {
    private double total = 0;
    private Formatter f = new Formatter(System.out);

    public void printTitle() {
        f.format("%-15s %5s %10s\n", "Item", "Qty", "Price");
        f.format("%1$-15s %2$5s %3$10s\n", "----", "---", "-----");
    }

    public void print(String name, int qty, double price) {
        f.format("%-15.15s %5d %10.2f\n", name, qty, price);
        total += price;
    }

    public void printTotal() {
        f.format("%-15s %5s %10.2f\n", "Tax", "", total * 0.06);
        f.format("%-15s %5s %10s\n", "", "", "-----");
        f.format("%-15s %5s %10.2f\n", "Total", "", total * 1.06);
    }

    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.printTitle();
        receipt.print("Jack's Magic Beans", 4, 4.25);
        receipt.print("Princess Peas", 3, 5.1);
        receipt.print("Three Bears Porridge", 1, 14.29);
        receipt.printTotal();
    }
}
