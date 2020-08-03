package com.spartann.foodplus.common.recipe.sandwichmaker;

import com.spartann.foodplus.common.recipe.ISandwichMakerRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public abstract class AbstractSandwichRecipe implements ISandwichMakerRecipe {

    protected ItemStack output;
    protected NonNullList<Ingredient> ingredients;
    protected ResourceLocation recipeId;

    public AbstractSandwichRecipe(ICraftingRecipe recipe) {
        this.output = recipe.getRecipeOutput();
        this.ingredients = recipe.getIngredients();
        this.recipeId = recipe.getId();
    }


    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return this.getRecipeOutput();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public int getWorkingTime() {
        return 0;
    }

}
