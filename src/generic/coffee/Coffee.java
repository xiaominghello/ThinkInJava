package generic.coffee;

/**
 * @Author shenxiaowei
 * @Date 2020-05-03 15:19
 */
public class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}
