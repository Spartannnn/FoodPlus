package me.spartann.foodplus.common.recipe;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public interface IThreeInOneOutRecipe extends IRecipe<RecipeWrapper> {

    @Override
    default boolean canFit(int width, int height) {
        return false;
    }

    int getSecondsUntilFinish();

    Ingredient[] getInputs();

}
