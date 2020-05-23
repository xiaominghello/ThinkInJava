package a.javalogic.chapter8;

/**
 * Java泛型是通过擦除实现的，类定义中的类型参数如 T 会被替换为 Object，在程序运行
 * 过程中，不知道泛型的实际类型参数，比如 Pair<Integer>，运行中只知道 Pair，而不知道
 * Integer，认识到这一点是非常重要的，它有助于我们理解 Java 泛型的很多限制。
 * 既然只使用普通类和 Object 就可以，而且泛型最后也转换为了普通类，那为什么还要使用泛型呢？
 * 或者说，泛型到底有什么好处呢？
 * 好处：更好的安全性，类型安全，也就是说，通过使用泛型，开发环境和编译器能确保不会用错类型，为程序多设置了一道安全防护网。
 *      更好的可读性，使用泛型，还可以省去烦琐的强制类型转换，再加上明确的类型信息，代码可读性也会更好。
 *
 * @author nuc8
 * @date 2020/5/22 3:50 下午
 */
public class Pair<T> {
    T first;
    T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public static void main(String[] args) {
        Pair<Integer> pair = new Pair<>(1, 100);
        Integer first = pair.getFirst();
        Integer second = pair.getSecond();
        System.out.println(first);
        System.out.println(second);

        Pair<String> stringPair = new Pair<>("name", "老马");
        String strFirst = stringPair.getFirst();
        String strSecond = stringPair.getSecond();
        System.out.println(strFirst);
        System.out.println(strSecond);
    }
}
