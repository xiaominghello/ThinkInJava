package strings.p283;

import generic.coffee.Coffee;
import generic.coffee.CoffeeGenerator;

import java.util.ArrayList;

/**
 * 每个类都继承自object，标准容器类也不例外，因此容器类都有toString()方法，并且覆写了该方法，
 * 使得它生成的String结果能够表达容器自身，以及容器所包含的对象
 * 例如ArrayList.toString()，它会遍历ArrayList中包含的所有对象，调用每个元素的toString()方法
 *
 * @Author shenxiaowei
 * @Date 2020-05-03 15:35
 */
public class ArrayListDisplay {
    public static void main(String[] args) {
        ArrayList<Coffee> coffees = new ArrayList<>();
        for (Coffee c : new CoffeeGenerator(10)) {
            coffees.add(c);
        }
        System.out.println(coffees);
    }
}
