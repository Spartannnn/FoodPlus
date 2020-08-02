package com.spartann.foodplus.common.recipe.serializer;

import com.google.gson.JsonObject;
import com.spartann.foodplus.common.recipe.IOneInOneOutRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class OneInOneOutSerializer<T extends IOneInOneOutRecipe<RecipeWrapper>> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private IFactory<? extends IRecipe<?>> factory;

    public OneInOneOutSerializer(IFactory<T> factory) {
        this.factory = factory;
    }

    @Override
    public T read(ResourceLocation recipeId, JsonObject json) {
        Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
        return (T) this.factory.create(recipeId, input, output, JSONUtils.getInt(json, "secondsUntilFinish"));
    }

    @Nullable
    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {
        Ingredient input = Ingredient.read(buffer);
        ItemStack output = buffer.readItemStack();
        return (T) this.factory.create(recipeId, input, output, buffer.readInt());
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        recipe.getInput().write(buffer);
        buffer.writeItemStack(recipe.getRecipeOutput());
        buffer.writeInt(recipe.getWorkingTime());
    }

    public interface IFactory<T extends IOneInOneOutRecipe<RecipeWrapper>> {
        T create(ResourceLocation recipeId, Ingredient input, ItemStack output, int secondsUntilFinish);
    }
}
