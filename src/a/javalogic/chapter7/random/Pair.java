package a.javalogic.chapter7.random;

/**
 * @author nuc8
 * @date 2020/5/22 2:38 下午
 */
public class Pair {
    Object item;
    int weight;

    public Pair(Object item, int weight) {
        this.item = item;
        this.weight = weight;
    }

    public Object getItem() {
        return item;
    }

    public int getWeight() {
        return weight;
    }
}
