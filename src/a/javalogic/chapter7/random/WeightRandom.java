package a.javalogic.chapter7.random;

import java.util.Arrays;
import java.util.Random;

/**
 * @author nuc8
 * @date 2020/5/22 2:39 下午
 */
public class WeightRandom {
    private Pair[] options;
    private double[] cumulativeProbabilities;
    private Random random;

    public WeightRandom(Pair[] options) {
        this.options = options;
        this.random = new Random();
        prepare();
    }

    private void prepare() {
        int weights = 0;
        for (Pair pair : options) {
            weights += pair.getWeight();
        }
        cumulativeProbabilities = new double[options.length];
        int sum = 0;
        for (int i = 0; i < options.length; i++) {
            sum += options[i].getWeight();
            cumulativeProbabilities[i] = sum / (double)weights;
        }
    }

    public Object nextItem() {
        double randomValue = random.nextDouble();
        int index = Arrays.binarySearch(cumulativeProbabilities, randomValue);
        if (index < 0) {
            index = -index-1;
        }
        return options[index].getItem();
    }

    public static void main(String[] args) {
        Pair[] options = {new Pair("1元", 7), new Pair("2元", 2), new Pair("5元", 1)};
        WeightRandom weightRandom = new WeightRandom(options);
        for (int i = 0; i < 10; i++) {
            System.out.print(weightRandom.nextItem() + " ");
        }
    }
}
