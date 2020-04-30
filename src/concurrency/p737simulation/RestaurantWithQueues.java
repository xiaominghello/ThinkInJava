package concurrency.p737simulation;

import emumerated.menu.Course;
import emumerated.menu.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 饭店仿真：
 *
 * 1.客户选菜
 * 2.客户下单
 * 2.主厨做菜    饭店维护一个有序的菜单队列 LinkedBlockingDeque,  主厨有菜时做，没菜阻塞
 * 3.服务员上菜  每个服务员维护一个自己的菜单队列 LinkedBlockingQueue, 菜好了上，没好阻塞
 * 4.客户吃菜    客户维护一个SynchronousQueue，这是一个没有内部容量的阻塞队列，每个put()都需要等待一个take(),每个take()都要等待一个put()
 *            服务员上菜，客户才能吃，吃完了，客户才可以继续点菜，服务员才可以继续上菜
 * 5.客户再点菜
 * 依次循环
 *
 * @Author Administrator
 * @Date 2020/4/30 15:34
 */
public class RestaurantWithQueues {
    public static void main(String[] args) throws Exception {
        args = new String[]{"5"};
        ExecutorService exec = Executors.newCachedThreadPool();
        Restaurant restaurant = new Restaurant(exec, 5, 2);
        exec.execute(restaurant);
        if (args.length > 0) {
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        } else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}

class Order {
    private static int counter = 8;
    private final int id = counter++;
    private final Customer2 customer2;
    private final WaitPerson waitPerson;
    private final Food food;

    public Order(Customer2 customer2, WaitPerson waitPerson, Food food) {
        this.customer2 = customer2;
        this.waitPerson = waitPerson;
        this.food = food;
    }

    public Food item() {
        return food;
    }

    public Customer2 getCustomer2() {
        return customer2;
    }

    public WaitPerson getWaitPerson() {
        return waitPerson;
    }

    @Override
    public String toString() {
        return "Order: " + id + " item: " + food + " for：" + customer2 + " served by: " + waitPerson;
    }
}

class Plate {
    private final Order order;
    private final Food food;

    public Plate(Order order, Food food) {
        this.order = order;
        this.food = food;
    }

    public Order getOrder() {
        return order;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return food.toString();
    }
}

class Customer2 implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final WaitPerson waitPerson;
    private final SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();

    public Customer2(WaitPerson waitPerson) {
        this.waitPerson = waitPerson;
    }

    /**
     * 传菜给客户，客户吃完了上一道菜菜可以再上一道菜，不然阻塞
     *
     * @param p                        盘子
     * @throws InterruptedException    异常
     */
    public void deliver(Plate p) throws InterruptedException {
        // Only blocks if customer is still eating the previous course:
        placeSetting.put(p);
    }

    /**
     * 顾客随机点菜，点完阻塞，上菜后继续执行，依次点开胃菜，主菜，甜点，咖啡
     */
    @Override
    public void run() {
        for (Course course :
                Course.values()) {
            Food food = course.randomSelection();
            try {
                waitPerson.placeOrder(this, food);
                System.out.println(this + "order " + food);
                // Blocks until course has been delivered:
                System.out.println(this + "eating " + placeSetting.take());
            } catch (InterruptedException e) {
                System.out.println(this + "waiting for " + course + " interrupted");
                break;
            }
        }
        System.out.println(this + "finished meal, leaving");
    }

    @Override
    public String toString() {
        return "Customer " + id + " ";
    }
}

class WaitPerson implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant restaurant;
    BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<>();

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * 顾客下单
     *
     * @param customer2 顾客
     * @param food      食物
     */
    public void placeOrder(Customer2 customer2, Food food) {
        try {
            // Shouldn't actually block because this is a LinkedBlockingQueue with no size limit:
            restaurant.orders.put(new Order(customer2, this, food));
        } catch (InterruptedException e) {
            System.out.println(this + " placeOrder interrupted");
        }
    }

    /**
     * 服务员的任务就是上菜，有菜时上菜，没菜时阻塞
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                // Blocks until a course is ready
                Plate plate = filledOrders.take();
                System.out.println(this + "received " + plate + " delivering to " + plate.getOrder().getCustomer2());
                plate.getOrder().getCustomer2().deliver(plate);
            } catch (InterruptedException e) {
                System.out.println(this + " interrupted");
            }
        }
        System.out.println(this + " off duty");
    }

    @Override
    public String toString() {
        return "WaitPerson " + id + " ";
    }
}

class Chef implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant restaurant;
    private static final Random random = new Random(47);

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * 主厨的任务就是炒菜，有订单时做，没有时阻塞
     */
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until an order appears:
                Order order = restaurant.orders.take();
                Food requestedItem = order.item();
                // Time to prepare order:
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                Plate plate = new Plate(order, requestedItem);
                order.getWaitPerson().filledOrders.put(plate);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " off duty");
    }

    @Override
    public String toString() {
        return "Chef " + id + " ";
    }
}

class Restaurant implements Runnable {
    private final List<WaitPerson> waitPersons = new ArrayList<>();
    private List<Chef> chefs = new ArrayList<>();
    private final ExecutorService exec;
    private static final Random random = new Random(47);
    BlockingQueue<Order> orders = new LinkedBlockingQueue<>();

    public Restaurant(ExecutorService exec, int nWaitPersons, int nChefs) {
        this.exec = exec;
        for (int i = 0; i < nWaitPersons; i++) {
            WaitPerson waitPerson = new WaitPerson(this);
            waitPersons.add(waitPerson);
            exec.execute(waitPerson);
        }

        for (int i = 0; i < nChefs; i++) {
            Chef chef = new Chef(this);
            chefs.add(chef);
            exec.execute(chef);
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 定时生成客户并随机分配服务员
                WaitPerson wp = waitPersons.get(random.nextInt(waitPersons.size()));
                Customer2 c = new Customer2(wp);
                exec.execute(c);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Restaurant interrupted");
        }
        System.out.println("Restaurant closing");
    }
}











