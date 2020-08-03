package com.spartann.foodplus.common.items;

import net.minecraft.item.Food;
import net.minecraft.item.Foods;

public class ItemBaseFood extends ItemFoodPlus {

    public ItemBaseFood(Properties properties, Food food) {
        super(properties.food(food), null);
    }

    public ItemBaseFood(Properties properties) {
        this(properties, Foods.COOKED_BEEF);
    }


}
