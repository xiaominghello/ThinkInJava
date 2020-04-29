package net.mindview.util;

/**
 * @Author Administrator
 * @Date 2020/4/29 16:56
 */
public class BasicGenerator<T> implements Generator<T> {
    private Class<T> type;

    public BasicGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next() {
        try {
            // Assumes type is a public class:
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Produce a Default generator given a type token
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<>(type);
    }
}

