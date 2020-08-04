package com.spartann.foodplus.common.util.helper;

import com.spartann.foodplus.common.food.FoodList;
import com.spartann.foodplus.common.items.ItemBaseFood;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemStackHelper {

    public static boolean isFruit(ItemStack itemStack) {
        if (itemStack == null) return false;
        return itemStack.getItem() instanceof ItemBaseFood && itemStack.getItem().isFood() && itemStack.getItem().getFood().equals(FoodList.FRUIT_FOOD);
    }

    public static void dropItems(World world, BlockPos pos, ItemStack stack) {
        ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
        itemEntity.setMotion(world.rand.nextGaussian() * (double) 0.05F, world.rand.nextGaussian() * (double) 0.05F + (double) 0.2F, world.rand.nextGaussian() * (double) 0.05F);
        world.addEntity(itemEntity);
    }

}
