package concurrency.p722;

/**
 * @Author Administrator
 * @Date 2020/4/29 15:27
 */
public class Fat {
    private volatile double d;
    private static int counter = 0;
    private final int id = counter++;

    public Fat() {
        int count = 10000;
        for (int i = 0; i < count; i++) {
            d += (Math.PI + Math.E) / (double) i;
        }
    }

    public void operation() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Fat id: " + id;
    }
}
