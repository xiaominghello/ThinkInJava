package concurrency.p722;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @Author Administrator
 * @Date 2020/4/29 15:18
 */
public class Pool<T> {
    private final int size;
    private final List<T> items = new ArrayList<T>();
    private final boolean[] checkedOut;
    private final Semaphore available;

    public Pool(Class<T> classObject, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        // 初始化计数信号量，大小和对象池大小相同
        available = new Semaphore(size, true);
        for (int i = 0; i < size; i++) {
            try {
                items.add(classObject.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public T checkOut() throws InterruptedException {
        // 从计数信号量获取许可，blocking until one is available
        available.acquire();
        return getItem();
    }

    public void checkIn(T x) {
        if (releaseItem(x)) {
            // 签入对象成功，归还一个许可给计数信号量
            available.release();
        }
    }

    private synchronized T getItem() {
        // 拿到许可，则从池中签出一个对象，同时改变对应下标的签出标记
        for (int i = 0; i < size; i++) {
            if (!checkedOut[i]) {
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }

    private synchronized boolean releaseItem(T item) {
        // 签入一个对象
        int index = items.indexOf(item);
        if (index == -1) {
            return false;
        }
        if (checkedOut[index]) {
            checkedOut[index] = false;
            return true;
        }
        return false;
    }
}
