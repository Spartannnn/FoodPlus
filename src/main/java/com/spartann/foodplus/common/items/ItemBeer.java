package com.spartann.foodplus.common.items;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class ItemBeer extends ItemFoodPlus {

    private static final String NBT_TAG = "fullness";

    public ItemBeer(Properties properties) {
        super(properties, null);
        this.addPropertyOverride(new ResourceLocation("status"), (stack, world, livingEntity) -> {
            if (isFull(stack))
                return 0;
            else
                return 1;
        });
    }

    public static void setFullness(ItemStack stack, boolean full) {
        CompoundNBT tag = stack.getOrCreateTag();
        tag.putBoolean(NBT_TAG, full);
    }

    public static boolean isFull(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        if (!tag.contains(NBT_TAG)) return false;
        return tag.getBoolean(NBT_TAG);
    }


}
