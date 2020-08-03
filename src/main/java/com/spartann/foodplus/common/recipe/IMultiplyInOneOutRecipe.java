package com.spartann.foodplus.common.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.Ingredient;

public interface IMultiplyInOneOutRecipe<W extends IInventory> extends IModRecipe<W> {

    @Override
    default boolean canFit(int width, int height) {
        return false;
    }

    Ingredient[] getInputs();

}
