package emumerated.menu;

/**
 * @Author Administrator
 * @Date 2020/4/30 17:22
 */
public interface Food {
    enum Appetizer implements Food {
        // 沙拉，汤，春卷
        SALAD,
        SOUP,
        SPRING_ROLLS;
    }

    enum MainCourse implements Food {
        //
        LASAGNE,
        BURRITO,
        PAD_THAI,
        LENTILS,
        HUMMOUS, VINDALOO;
    }

    enum Dessert implements Food {
        //
        TIRAMISU,
        GELATO,
        BLACK_FOREST_CAKE,
        FRUIT,
        CREME_CARAMEL;
    }

    enum Coffee implements Food {
        //
        BLACK_COFFEE,
        DECAF_COFFEE,
        ESPRESSO,
        LATTE,
        CAPPUCCINO,
        TEA, HERB_TEA;
    }
}
