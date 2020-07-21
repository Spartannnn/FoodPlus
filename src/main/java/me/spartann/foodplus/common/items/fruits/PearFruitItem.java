package me.spartann.foodplus.common.items.fruits;

import me.spartann.foodplus.common.items.BaseFoodItem;
import net.minecraft.item.Food;

public class PearFruitItem extends BaseFoodItem {

    public PearFruitItem(Properties properties) {
        super(properties, new Food.Builder().hunger(4).saturation(0.2F).build());
    }
}
