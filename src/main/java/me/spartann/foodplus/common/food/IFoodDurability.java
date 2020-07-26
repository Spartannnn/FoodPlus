package me.spartann.foodplus.common.food;

import net.minecraft.item.Item;

public interface IFoodDurability {

    int getMaxDurability();

    int getCurrentDurability();

    int getRemoveTicks();

    static boolean hasFoodDurability(Item item) {
        return item instanceof IFoodDurability;
    }

}
