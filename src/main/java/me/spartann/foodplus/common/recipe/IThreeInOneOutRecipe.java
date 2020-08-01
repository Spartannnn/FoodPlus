package me.spartann.foodplus.common.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.Ingredient;

public interface IThreeInOneOutRecipe<W extends IInventory> extends IModRecipe<W> {

    @Override
    default boolean canFit(int width, int height) {
        return false;
    }

    Ingredient[] getInputs();

}
