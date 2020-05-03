package strings.p289format;

/**
 * String.format()是一个static方法，它接受与Formatter.format()方法一样的参数，但返回一个String对象
 * 当你只需使用format()方法一次的时候，String.format()用起来很方便。
 *
 * @Author shenxiaowei
 * @Date 2020-05-03 21:01
 */
public class DatabaseException extends Exception {
    public DatabaseException(int transactionID, int queryID, String message) {
        super(String.format("(t%d, q%d) %s", transactionID, queryID, message));
    }

    public static void main(String[] args) {
        try {
            throw new DatabaseException(3, 7, "Write failed");
        } catch (DatabaseException e) {
            System.out.println(e);
        }
    }
}
