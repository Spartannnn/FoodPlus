package com.spartann.foodplus.common.recipe;

import com.spartann.foodplus.FoodPlusMod;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;

public interface ISandwichMakerRecipe extends IModRecipe<RecipeWrapper> {

    ResourceLocation TYPE_ID = new ResourceLocation(FoodPlusMod.MOD_ID, "sandwich_maker");

    @Nonnull
    @Override
    default IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getValue(TYPE_ID).get();
    }

    @Override
    default boolean canFit(int width, int height) {
        return false;
    }

}
