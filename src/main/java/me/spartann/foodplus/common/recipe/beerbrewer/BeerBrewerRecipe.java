package me.spartann.foodplus.common.recipe.beerbrewer;

import me.spartann.foodplus.common.registries.ModRecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;
import java.util.Arrays;

public class BeerBrewerRecipe implements IBeerBrewerRecipe {

    private final ResourceLocation id;
    private final ItemStack output;
    private final Ingredient[] inputs;
    private final int secondsUntilFinish;

    public BeerBrewerRecipe(ResourceLocation id, Ingredient[] inputs, ItemStack output, int secondsUntilFinish) {
        this.id = id;
        this.output = output;
        this.inputs = inputs;
        this.secondsUntilFinish = secondsUntilFinish;
    }

    @Override
    public boolean matches(RecipeWrapper inv, @Nullable World worldIn) {
        boolean flag = false;
        for (int i = 0; i < inputs.length; i++)
            flag = inputs[i].getMatchingStacks()[0].isItemEqual(inv.getStackInSlot(i));

        return flag;
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
        return ModRecipeTypes.BEER_BREWER_SERIALIZER.get();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.addAll(Arrays.asList(inputs));
        return ingredients;
    }

    @Override
    public Ingredient[] getInputs() {
        return inputs;
    }

    @Override
    public int getWorkingTime() {
        return secondsUntilFinish * 20;
    }
}
