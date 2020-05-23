package a.javalogic.chapter7.date;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Calendar 操作比较繁琐
 * DateFormat/SimpleDateFormat 线程不安全
 *
 * @author nuc8
 * @date 2020/5/22 2:08 下午
 */
public class DateTest {
    @Test
    public void test() {
        Date date = new Date();
        System.out.println(date);

        // 时区
        TimeZone tz = TimeZone.getDefault();
        System.out.println(tz.getID());
        // 国家/地区
        Locale locale = Locale.getDefault();
        System.out.println(locale.toString());

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.SECOND));
        System.out.println(calendar.get(Calendar.MILLISECOND));
    }

    @Test
    public void addTest2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 59);
        calendar.add(Calendar.MINUTE, 3);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(calendar.getTime()));
    }

    /**
     * roll方法不影响时间范围更大的字段值
     * Adds the specified (signed) amount to the specified calendar field without changing larger fields.
     */
    @Test
    public void rollTest2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 59);
        calendar.roll(Calendar.MINUTE, 3);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a E");
        System.out.println(sdf.format(calendar.getTime()));
    }

    @Test
    public void dateFormatTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 01, 01, 14, 14, 14);
        System.out.println(DateFormat.getDateTimeInstance().format(calendar.getTime()));
        System.out.println(DateFormat.getDateInstance().format(calendar.getTime()));
        System.out.println(DateFormat.getTimeInstance().format(calendar.getTime()));
    }
}
