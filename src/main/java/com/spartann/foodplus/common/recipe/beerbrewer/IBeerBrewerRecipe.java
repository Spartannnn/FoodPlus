package com.spartann.foodplus.common.recipe.beerbrewer;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.recipe.IMultiplyInOneOutRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;

public interface IBeerBrewerRecipe extends IMultiplyInOneOutRecipe<RecipeWrapper> {

    ResourceLocation TYPE_ID = new ResourceLocation(FoodPlusMod.MOD_ID, "beer_brewer");

    @Nonnull
    @Override
    default IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getValue(TYPE_ID).get();
    }

}
