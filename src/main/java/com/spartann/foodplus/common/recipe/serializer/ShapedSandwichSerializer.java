package com.spartann.foodplus.common.recipe.serializer;

import com.google.gson.JsonObject;
import com.spartann.foodplus.common.recipe.sandwichmaker.ShapedSandwichRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ShapedSandwichSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ShapedSandwichRecipe> {

    @Override
    public ShapedSandwichRecipe read(ResourceLocation recipeId, JsonObject json) {
        ShapedRecipe shaped = IRecipeSerializer.CRAFTING_SHAPED.read(recipeId, json);
        return new ShapedSandwichRecipe(shaped);
    }

    @Override
    public ShapedSandwichRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        ShapedRecipe shaped = IRecipeSerializer.CRAFTING_SHAPED.read(recipeId, buffer);
        return new ShapedSandwichRecipe(shaped);
    }

    @Override
    public void write(PacketBuffer buffer, ShapedSandwichRecipe recipe) {
        IRecipeSerializer.CRAFTING_SHAPED.write(buffer, recipe.convertShaped());
    }

}
