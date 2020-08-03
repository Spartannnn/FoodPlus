package com.spartann.foodplus.common.recipe.serializer;

import com.google.gson.JsonObject;
import com.spartann.foodplus.common.recipe.burgermaker.ShapedBurgerRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ShapedBurgerSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ShapedBurgerRecipe> {

    @Override
    public ShapedBurgerRecipe read(ResourceLocation recipeId, JsonObject json) {
        ShapedRecipe shaped = IRecipeSerializer.CRAFTING_SHAPED.read(recipeId, json);
        return new ShapedBurgerRecipe(shaped);
    }

    @Override
    public ShapedBurgerRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        ShapedRecipe shaped = IRecipeSerializer.CRAFTING_SHAPED.read(recipeId, buffer);
        return new ShapedBurgerRecipe(shaped);
    }

    @Override
    public void write(PacketBuffer buffer, ShapedBurgerRecipe recipe) {
        IRecipeSerializer.CRAFTING_SHAPED.write(buffer, recipe.convertShaped());
    }

}
