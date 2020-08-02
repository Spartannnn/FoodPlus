package com.spartann.foodplus.common.util.helper;

import com.spartann.foodplus.common.items.BaseFoodItem;
import net.minecraft.item.ItemStack;

public class ItemStackHelper {

    public static boolean isFruit(ItemStack itemStack) {
        if (itemStack == null) return false;
        return itemStack.getItem() instanceof BaseFoodItem && itemStack.getItem().isFood() && itemStack.getItem().getFood().equals(BaseFoodItem.FRUIT_FOOD);
    }

}
