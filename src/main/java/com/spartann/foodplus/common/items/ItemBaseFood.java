package com.spartann.foodplus.common.items;

import net.minecraft.item.Food;
import net.minecraft.item.Foods;

public class ItemBaseFood extends ItemFoodPlus {

    public static final Food FRUIT_FOOD = build(4, 1.2F);
    public static final Food JUICE = build(8, 0.7F);
    public static final Food BEER = build(6, 2.0F);

    public ItemBaseFood(Properties properties, Food food) {
        super(properties.food(food), null);
    }

    public ItemBaseFood(Properties properties) {
        this(properties, Foods.COOKED_BEEF);
    }

    public static Food build(int hunger, float saturation, boolean meat, boolean alwaysEdible, boolean fastEat) {
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

    public static Food build(int hunger, float saturation) {
        return build(hunger, saturation, false, false, false);
    }


}
