package me.spartann.foodplus.common.recipe.juicer;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.recipe.IOneInOneOutRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;

public interface IJuicerRecipe extends IOneInOneOutRecipe<RecipeWrapper> {

    ResourceLocation TYPE_ID = new ResourceLocation(FoodPlusMod.MOD_ID, "juicer");

    @Nonnull
    @Override
    default IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getValue(TYPE_ID).get();
    }
}
