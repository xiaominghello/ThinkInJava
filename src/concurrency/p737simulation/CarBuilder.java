package concurrency.p737simulation;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 *
 * 创建机器人并启动，机器人会加入池中，并wait进入等待状态
 * 底盘创建好后，会加入汽车队列，组装者结束阻塞状态，开始组装，即向机器人池雇佣机器人
 * 池里有对应可用的机器人，则把机器人分配给组装者，并notify唤醒该等待机器人  机器人加入池中/归还池中后wait, 和 雇佣调用 notifyAll 配合使用
 * 机器人执行完后，自动归还池中，并重新进入等待状态
 *
 * 机器人池的状态：
 *      线程调用雇佣时，1.如果有机器人，则雇佣，雇佣线程返回
 *                    2.如果没有对应类型的机器人，则wait，即雇佣线程进入等待
 *      当有机器人归还时，notifyAll唤醒等待的雇佣线程，递归调用，重新判断是否满足雇佣线程的要求，即上述 1，2
 *      此处 雇佣wait 归还notifyAll成对
 *
 * @Author Administrator
 * @Date 2020/4/30 21:36
 */
public class CarBuilder {
    public static void main(String[] args) throws InterruptedException {
        CarQueue
                chassisQueue = new CarQueue(),
                finishingQueue = new CarQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        // 初始化机器人池
        RobotPool robotPool = new RobotPool();
        // 创建3个机器人并启动，并加入机器人池
        exec.execute(new EngineRobot(robotPool));
        exec.execute(new DriveTrainRobot(robotPool));
        exec.execute(new WheelRobot(robotPool));
        // 创建组装者
        exec.execute(new Assembler(chassisQueue, finishingQueue, robotPool));
        // 汽车组装好通报
        exec.execute(new Reporter(finishingQueue));
        // 创建底盘创造者
        exec.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();

    }
}

/**
 * 每辆汽车，从创建底盘开始，紧接着要安装发动机，车箱，轮子
 */
class Car {
    private final int id;
    private boolean
            engine = false,
            driveTrain = false,
            wheels = false;

    public Car(int id) {
        this.id = id;
    }

    public Car() {
        id = -1;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void addEngine() {
        engine = true;
    }

    public synchronized void addDriveTrain() {
        driveTrain = true;
    }

    public synchronized void addWheels() {
        wheels = true;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", engine=" + engine +
                ", driveTrain=" + driveTrain +
                ", wheels=" + wheels +
                '}';
    }
}

/**
 * 汽车队列
 */
class CarQueue extends LinkedBlockingQueue<Car> {
}

/**
 * 底盘创建
 */
class ChassisBuilder implements Runnable {
    private CarQueue carQueue;
    private int counter = 0;

    public ChassisBuilder(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    /**
     * 每隔一段时间，创建一辆只有底盘的汽车
     */
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                // Make chassis:
                Car c = new Car(counter++);
                System.out.println("ChassisBuilder created " + c);
                // Insert into queue
                carQueue.put(c);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted ChassisBuilder");
        }
        System.out.println("ChassisBuilder off");
    }
}

/**
 * 组装者
 */
class Assembler implements Runnable {
    private CarQueue
                chassisQueue,
                finishingQueue;
    private Car car;
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;

    public Assembler(CarQueue chassisQueue, CarQueue finishingQueue, RobotPool robotPool) {
        this.chassisQueue = chassisQueue;
        this.finishingQueue = finishingQueue;
        this.robotPool = robotPool;
    }

    public Car car() {
        return car;
    }

    public CyclicBarrier barrier() {
        return barrier;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until chassis is available:
                car = chassisQueue.take();
                // Hire robots to perform work:
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                robotPool.hire(WheelRobot.class, this);
                // Until the robots finish
                barrier.await();
                // Put car into finishingQueue for further work
                finishingQueue.put(car);
            }
        } catch(InterruptedException e) {
            System.out.println("Exiting Assembler via interrupt");
        } catch(BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        System.out.println("Assembler off");
    }
}

class Reporter implements Runnable {
    private CarQueue carQueue;

    public Reporter(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(carQueue.take());
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting Reporter via interrupt");
        }
        System.out.println("Reporter off");
    }
}

/**
 * 机器人抽象类
 */
abstract class AbstractRobot implements Runnable {
    private final RobotPool robotPool;

    public AbstractRobot(RobotPool robotPool) {
        this.robotPool = robotPool;
    }
    protected Assembler assembler;

    /**
     * 分配雇佣者
     *
     * @param assembler 雇佣者
     * @return          机器人
     */
    public AbstractRobot assignAssembler(Assembler assembler) {
        this.assembler = assembler;
        return this;
    }

    private boolean engage = false;

    /**
     * 更改雇佣状态
     * 并唤醒自己
     */
    public synchronized void engage() {
        engage = true;
        notifyAll();
    }

    /**
     * The part of run() that's different for each robot:
     */
    abstract protected void performService();

    /**
     * 机器人默认关机
     */
    @Override
    public void run() {
        try {
            // Wait until needed
            powerDown();
            while (!Thread.interrupted()) {
                performService();
                assembler.barrier().await();
                // We're done with that job...
                // 机器人执行完后清除状态，关机，归还机器人池
                powerDown();
            }
        } catch(InterruptedException e) {
            System.out.println("Exiting " + this + " via interrupt");
        } catch(BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        System.out.println(this + " off");
    }

    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        // Disconnect from the Assembler
        assembler = null;
        // Put ourselves back in the available pool:
        robotPool.release(this);
        // Power down
        while (!engage) {
            wait();
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

class EngineRobot extends AbstractRobot {
    public EngineRobot(RobotPool robotPool) {
        super(robotPool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing engine");
        assembler.car().addEngine();
    }
}

class DriveTrainRobot extends AbstractRobot {
    public DriveTrainRobot(RobotPool robotPool) {
        super(robotPool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing DriverTrain");
        assembler.car().addDriveTrain();
    }
}

class WheelRobot extends AbstractRobot {
    public WheelRobot(RobotPool robotPool) {
        super(robotPool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing Wheels");
        assembler.car().addWheels();
    }
}

class RobotPool {
    /**
     *  Quietly prevents identical entries:
     */
    private final Set<AbstractRobot> pool = new HashSet<>();

    /**
     * 归还机器人池
     * 唤醒自己
     *
     * @param robot 机器人
     */
    public synchronized void add(AbstractRobot robot) {
        pool.add(robot);
        notifyAll();
    }

    /**
     * 雇佣机器人
     *
     * @param robotType     机器人类型
     * @param d             雇佣者
     * @throws InterruptedException 异常
     */
    public synchronized void hire(Class<? extends AbstractRobot> robotType, Assembler d) throws InterruptedException {
        for (AbstractRobot r :
                pool) {
            if (r.getClass().equals(robotType)) {
                pool.remove(r);
                r.assignAssembler(d);
                // Power it up to do the task
                r.engage();
                return;
            }
        }
        // None available
        // 没有对应机器人可以雇佣时等待
        wait();
        // Try again, recursively
        // 递归调用雇佣
        hire(robotType, d);
    }

    // 机器人执行完后归还机器人池
    public synchronized void release(AbstractRobot robot) {
        add(robot);
    }
}

















