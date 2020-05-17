package date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * jdk 8之前的日期时间的API测试
 * 1. System类中currentTimeMillis();
 * 2. java.util.Date和子类java.sql.Date
 * 3. SimpleDateFormat
 * 4. Calendar
 *
 * @author nuc8
 * @date 2020/5/16 8:28 下午
 */
public class DateTimeTest {
    /**
     * System类中的currentTimeMillis()
     */
    @Test
    public void test1() {

         // 返回当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差。
         // 称为时间戳
        long time = System.currentTimeMillis();
        // 1589632452952
        System.out.println(time);
    }
    /**
     * java.util.Date类
     *            |---java.sql.Date类
     *
     *     1.两个构造器的使用
     *         >构造器一：Date()：创建一个对应当前时间的Date对象
     *         >构造器二：创建指定毫秒数的Date对象
     *     2.两个方法的使用
     *         >toString():显示当前的年、月、日、时、分、秒
     *         >getTime():获取当前Date对象对应的毫秒数。（时间戳）
     *
     *     3. java.sql.Date对应着数据库中的日期类型的变量
     *         >如何实例化
     *         >如何将java.util.Date对象转换为java.sql.Date对象
     */
    @Test
    public void test2 () {
        // 构造器一：Date()：创建一个对应当前时间的Date对象
        Date date = new Date();
        // Sat May 16 20:34:12 CST 2020
        System.out.println(date);
        // 1589632452952
        System.out.println(date.getTime());

        // 构造器二：创建指定毫秒数的Date对象
        Date date1 = new Date(1589632452952L);
        // Sat May 16 20:34:12 CST 2020
        System.out.println(date1);

        // 创建java.sql.Date对象
        java.sql.Date date2 = new java.sql.Date(1589632452952L);
        // 2020-05-16
        System.out.println(date2);

        // 如何将java.util.Date对象转换为java.sql.Date对象
        Date date3 = new Date();
        java.sql.Date date4 = new java.sql.Date(date3.getTime());
        System.out.println(date4);
    }

    @Test
    public void testSimpleDateFormat() throws ParseException {
        // 实例化SimpleDateFormat:使用默认的构造器
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Date date = new Date();
        // Sat May 16 21:02:35 CST 2020
        System.out.println(date);
        // 格式化：日期 --->字符串
        String format = simpleDateFormat.format(date);
        // 20-5-16 下午9:02
        System.out.println(format);

        // 解析：格式化的逆过程，字符串 ---> 日期
        String str = "20-5-16 下午9:02";
        Date parse = simpleDateFormat.parse(str);
        // Sat May 16 21:02:00 CST 2020
        System.out.println(parse);

        // *************按照指定的方式格式化和解析：调用带参的构造器*****************
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat1.format(date);
        // 格式化 2020-05-16 21:05:21
        System.out.println(format1);
        Date parse1 = simpleDateFormat1.parse("2020-05-16 21:05:21");
        // 解析:要求字符串必须是符合SimpleDateFormat识别的格式(通过构造器参数体现),
        // 否则，抛异常
        // Sat May 16 21:05:21 CST 2020
        System.out.println(parse1);
    }

    /**
     * 练习一：字符串"2020-09-08"转换为java.sql.Date
     */
    @Test
    public void testExer() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse("2020-09-08");
        java.sql.Date date = new java.sql.Date(parse.getTime());
        System.out.println(date);
    }

    /**
     * Calendar日历类(抽象类）的使用
     */
    @Test
    public void testCalendar() {
        //1.实例化
        //方式一：创建其子类（GregorianCalendar）的对象
        //方式二：调用其静态方法getInstance()
        Calendar calendar = Calendar.getInstance();
        // java.util.GregorianCalendar
        System.out.println(calendar.getClass().getName());

        //2.常用方法
        //get()
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        // 16
        System.out.println(days);

        //set()
        //calendar可变性
        calendar.set(Calendar.DAY_OF_MONTH, 22);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        // 22
        System.out.println(days);

        //add()
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        // 19
        System.out.println(days);

        Date date = calendar.getTime();
        System.out.println(date);

        Date date1 = new Date();
        calendar.setTime(date1);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        // 16
        System.out.println(days);

    }
}
