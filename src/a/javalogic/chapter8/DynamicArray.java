package a.javalogic.chapter8;

import java.util.Arrays;
import java.util.Random;

/**
 * @author nuc8
 * @date 2020/5/22 4:15 下午
 */
public class DynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public DynamicArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity= elementData.length;
        if (oldCapacity >= minCapacity) {
            return;
        }
        int newCapacity = oldCapacity * 2;
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public void add(E e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
    }

    public E get(int index) {
        return (E) elementData[index];
    }

    public Object[] getElementData() {
        return elementData;
    }

    public int size() {
        return size;
    }

    public E set(int index, E element) {
        E oldValue = get(index);
        elementData[index] = element;
        return oldValue;
    }

    public static void main(String[] args) {
        DynamicArray<Double> arr = new DynamicArray<>();
        Random random = new Random();
        int size = 1 + random.nextInt(100);
        for (int i = 0; i < size; i++) {
            arr.add(Math.random());
        }
        Double d = arr.get(random.nextInt(size));
        System.out.println(Arrays.toString(arr.getElementData()));
        System.out.println(d);
    }
}
