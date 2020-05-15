package emumerated.shangguigu;

/**
 * 使用enum关键字定义枚举类
 * 说明：定义的枚举类默认继承于java.lang.Enum类
 *
 * @author nuc8
 * @date 2020/5/16 5:38 上午
 */
public class SeasonTest1 {
    public static void main(String[] args) {

        /**
         * 枚举类默认继承于java.lang.Enum类
         */
        System.out.println(Season2.class.getSuperclass());

        /**
         * values():返回所有的枚举类对象构成的数组
         * 在创建枚举类时，编译器会默认给你继承Enum类。然而Enum类并没有values方法。
         * 通过反射可以了解到values是由编译器添加的
         */
        Season2[] values = Season2.values();
        for (Season2 season : values) {
            /**
             * toString():返回枚举类对象的名称
             */
            System.out.println(season);
            season.show();
        }

        /**
         * valueOf(String objName):返回枚举类中对象名是objName的对象。
         * 如果没有objName的枚举类对象，则抛异常：IllegalArgumentException
         */
        Season2 spring = Season2.valueOf("SPRING");
        System.out.println(spring);
        Season2 spring2 = Season2.valueOf("SPRING2");
    }
}

interface info {
    void show();
}

/**
 * 使用enum关键字枚举类
 */
enum Season2 implements info {
    /**
     * 1.提供当前枚举类的对象，多个对象之间用","隔开，末尾对象";"结束
     */
    SPRING("春天", "春暖花开") {
        @Override
        public void show() {
            System.out.println("春天在哪里？");
        }
    },
    SUMMER("夏天", "夏日炎炎") {
        @Override
        public void show() {
            System.out.println("夏天真热？");
        }
    },
    AUTUMN("秋天", "秋高气爽") {
        @Override
        public void show() {
            System.out.println("秋天大丰收");
        }
    },
    WINTER("冬天", "冰天雪地") {
        @Override
        public void show() {
            System.out.println("大约在冬季");
        }
    };

    /**
     * 2.声明Season对象的属性:private final修饰
     */
    private final String seasonName;
    private final String seasonDesc;

    Season2(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    /**
     * 3.其他诉求：获取枚举类对象的属性
     * @return
     */
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

}