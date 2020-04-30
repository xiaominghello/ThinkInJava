package emumerated.menu;

import net.mindview.util.Enums;

/**
 * @Author Administrator
 * @Date 2020/4/30 20:06
 */
public enum Course {
    //
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);
    private final Food[] values;

    private Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return Enums.random(values);
    }
}