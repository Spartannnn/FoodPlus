package me.spartann.foodplus.common.recipe.serializer;

import com.google.gson.JsonObject;
import me.spartann.foodplus.common.recipe.IThreeInOneOutRecipe;
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

public class ThreeInOnOutSerializer<T extends IThreeInOneOutRecipe<RecipeWrapper>> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {

    private IFactory<? extends IRecipe<?>> factory;

    public ThreeInOnOutSerializer(IFactory<T> factory) {
        this.factory = factory;
    }

    @Override
    public T read(ResourceLocation recipeId, JsonObject json) {
        Ingredient[] inputs = new Ingredient[3];
        for(int i = 0; i < inputs.length; i++) {
            inputs[i] = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input_" + i));
        }
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
        return (T) this.factory.create(recipeId, inputs, output, JSONUtils.getInt(json, "secondsUntilFinish"));
    }

    @Nullable
    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {
        Ingredient[] inputs = new Ingredient[3];
        for(int i = 0; i < inputs.length; i++)
            inputs[i] = Ingredient.read(buffer);
        ItemStack output = buffer.readItemStack();
        return (T) this.factory.create(recipeId, inputs, output, buffer.readInt());
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        for(Ingredient ingredient : recipe.getInputs()) {
            ingredient.write(buffer);
        }
        buffer.writeItemStack(recipe.getRecipeOutput());
        buffer.writeInt(recipe.getWorkingTime());
    }

    public interface IFactory<T extends IThreeInOneOutRecipe<RecipeWrapper>> {
        T create(ResourceLocation recipeId, Ingredient[] inputs, ItemStack output, int secondsUntilFinish);
    }
}
