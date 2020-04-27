package concurrency;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author shenxiaowei
 * @Date 2020-04-26 21:49
 */
public enum State {
    /**
     * Thread state for a thread which has not yet started.
     * 尚未启动的线程的线程状态。
     */
    NEW,

    /**
     * Thread state for a runnable thread.  A thread in the runnable
     * state is executing in the Java virtual machine but it may
     * be waiting for other resources from the operating system
     * such as processor.
     * 可运行线程的运行状态。
     * 处于可运行状态的线程正在Java虚拟机中执行，但它可能正在等待来自操作系统（如处理器）的其他资源。
     */
    RUNNABLE,

    /**
     * Thread state for a thread blocked waiting for a monitor lock.
     * A thread in the blocked state is waiting for a monitor lock
     * to enter a synchronized block/method or
     * reenter a synchronized block/method after calling
     * {@link Object#wait() Object.wait}.
     * 等待监视器锁而被阻塞的线程的线程状态。
     * 处于阻塞状态的线程正在等待监视器锁
     * 来进入同步块/方法，
     * 或者
     * 重新进入同步块/方法，在调用Object.wait后。
     */
    BLOCKED,

    /**
     * Thread state for a waiting thread.
     * A thread is in the waiting state due to calling one of the
     * following methods:
     * <ul>
     *   <li>{@link Object#wait() Object.wait} with no timeout</li>
     *   <li>{@link #join() Thread.join} with no timeout</li>
     *   <li>{@link LockSupport#park() LockSupport.park}</li>
     * </ul>
     *
     * <p>A thread in the waiting state is waiting for another thread to
     * perform a particular action.
     *
     * For example, a thread that has called <tt>Object.wait()</tt>
     * on an object is waiting for another thread to call
     * <tt>Object.notify()</tt> or <tt>Object.notifyAll()</tt> on
     * that object. A thread that has called <tt>Thread.join()</tt>
     * is waiting for a specified thread to terminate.
     *
     * 状态等待线程的线程状态。
     * 由于调用以下方法之一，线程处于等待状态：
     *      ·Object.wait with no timeout
     *      ·thread.join with no timeout
     *      ·LockSupport.park
     *  处于等待状态的线程正在等待另一个线程执行特定操作。
     *  例如，调用Object.wait()的线程正在等待另一个线程调用Object.notify()或Object.notifyAll()。
     *  调用thread.join()的线程正在等待指定线程终止。
     */
    WAITING,

    /**
     * Thread state for a waiting thread with a specified waiting time.
     * A thread is in the timed waiting state due to calling one of
     * the following methods with a specified positive waiting time:
     * <ul>
     *   <li>{@link #sleep Thread.sleep}</li>
     *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
     *   <li>{@link #join(long) Thread.join} with timeout</li>
     *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
     *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
     * </ul>
     *
     * 具有指定等待时间的等待线程的线程状态。
     * 由于使用指定的正等待时间调用以下方法，线程处于定时等待状态：
     *      ·thread.sleep
     *      ·Object.wait with timeout
     *      ·thread.join with timeout
     *      ·LockSupport.parkNanos
     *      ·LockSupport.parkUntil
     */
    TIMED_WAITING,

    /**
     * Thread state for a terminated thread.
     * The thread has completed execution.
     * 状态终止的线程状态终止的线程。
     * 线程已完成执行。
     */
    TERMINATED;
}
