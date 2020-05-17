package a.javalogic.chapter1;

/**
 * for(初始化语句;循环语句;步进操作){
 *     循环体
 * }
 * 1.执行初始化指令
 * 2.检查循环体条件是否为true，如果为false，则跳转到第6步
 * 3.循环条件为真，执行循环体
 * 4.执行步进操作
 * 5.步进操作执行完成后，跳转到第2步，继续检查循环条件
 * 6.for循环后面的语句
 * @author nuc8
 * @date 2020/5/17 11:00 上午
 */
public class ForDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
