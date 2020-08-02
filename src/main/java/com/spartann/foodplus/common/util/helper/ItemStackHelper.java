package com.spartann.foodplus.common.util.helper;

import com.spartann.foodplus.common.items.ItemBaseFood;
import net.minecraft.item.ItemStack;

public class ItemStackHelper {

    public static boolean isFruit(ItemStack itemStack) {
        if (itemStack == null) return false;
        return itemStack.getItem() instanceof ItemBaseFood && itemStack.getItem().isFood() && itemStack.getItem().getFood().equals(ItemBaseFood.FRUIT_FOOD);
    }

}
