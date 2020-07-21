package me.spartann.foodplus.common.items;

import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class BaseFoodItem extends Item {


    public BaseFoodItem(Properties properties, Food food) {
        super(properties.food(food));
    }

    public BaseFoodItem(Properties properties) {
        this(properties, Foods.COOKED_BEEF);
    }


}
