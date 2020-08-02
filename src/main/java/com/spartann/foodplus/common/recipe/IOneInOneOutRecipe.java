package com.spartann.foodplus.common.recipe;

import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public interface IOneInOneOutRecipe<W extends RecipeWrapper> extends IModRecipe<W> {

    @Override
    default boolean canFit(int width, int height) {
        return false;
    }

    Ingredient getInput();

}
