package com.spartann.foodplus.common.food;

import net.minecraft.item.Food;

public class FoodList {

    public static final Food FRUIT_FOOD = build(4, 1.2F);
    public static final Food JUICE = build(8, 0.7F);
    public static final Food BEER = build(6, 2.0F);
    public static final Food NUGGETS = build(4, 0.5F);
    public static final Food SUSHI = build(10, 2.5F);
    public static final Food FRIES = build(4, 0.4F);
    public static final Food CHOCOLATE = build(4, 0.8F);
    public static final Food SANDWICH = build(10, 2.5F);
    public static final Food CHEESE = build(4, 0.5F);
    public static final Food SALAMI = build(4, 0.5F);
    public static final Food HAM = build(6, 1.0F, true, false, false);
    public static final Food SASUAGE = build(6, 1.0F);


    private static Food build(int hunger, float saturation, boolean meat, boolean alwaysEdible, boolean fastEat) {
        Food.Builder builder = new Food.Builder();
        builder.hunger(hunger);
        builder.saturation(saturation);
        if (meat)
            builder.meat();
        if (alwaysEdible)
            builder.setAlwaysEdible();
        if (fastEat)
            builder.fastToEat();
        return builder.build();
    }

    private static Food build(int hunger, float saturation) {
        return build(hunger, saturation, false, false, false);
    }

}
