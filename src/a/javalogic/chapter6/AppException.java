package a.javalogic.chapter6;

/**
 * @author nuc8
 * @date 2020/5/22 10:03 上午
 */
public class AppException extends Exception{
    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }
}
