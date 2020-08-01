package me.spartann.foodplus.common.recipe.beerbrewer;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.recipe.IThreeInOneOutRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;

public interface IBeerBrewerRecipe extends IThreeInOneOutRecipe<RecipeWrapper> {

    ResourceLocation TYPE_ID = new ResourceLocation(FoodPlusMod.MOD_ID, "beer_brewer");

    @Nonnull
    @Override
    default IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getValue(TYPE_ID).get();
    }

}
