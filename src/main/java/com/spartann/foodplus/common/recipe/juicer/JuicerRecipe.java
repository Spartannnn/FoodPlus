package com.spartann.foodplus.common.recipe.juicer;

import com.spartann.foodplus.common.registries.ModRecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;

public class JuicerRecipe implements IJuicerRecipe {

    private final ResourceLocation id;
    private final ItemStack output;
    private final Ingredient input;
    private final int secondsUntilFinish;

    public JuicerRecipe(ResourceLocation id, Ingredient input, ItemStack output, int secondsUntilFinish) {
        this.id = id;
        this.output = output;
        this.input = input;
        this.secondsUntilFinish = secondsUntilFinish;
    }

    @Override
    public boolean matches(RecipeWrapper inv, @Nullable World worldIn) {
        return input.test(inv.getStackInSlot(0)) || input.getMatchingStacks()[0].isItemEqual(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return this.output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.JUICER_SERIALIZER.get();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(null, input);
    }

    @Override
    public Ingredient getInput() {
        return input;
    }

    @Override
    public int getWorkingTime() {
        return secondsUntilFinish;
    }
}
