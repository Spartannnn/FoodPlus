package com.spartann.foodplus.common.registries;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.recipe.IShapedBurgerRecipe;
import com.spartann.foodplus.common.recipe.beerbrewer.BeerBrewerRecipe;
import com.spartann.foodplus.common.recipe.beerbrewer.IBeerBrewerRecipe;
import com.spartann.foodplus.common.recipe.juicer.IJuicerRecipe;
import com.spartann.foodplus.common.recipe.juicer.JuicerRecipe;
import com.spartann.foodplus.common.recipe.serializer.MultiplayinOneOutSerializer;
import com.spartann.foodplus.common.recipe.serializer.OneInOneOutSerializer;
import com.spartann.foodplus.common.recipe.serializer.ShapedBurgerSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModRecipeTypes {

    public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZERS = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, FoodPlusMod.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<IBeerBrewerRecipe>> BEER_BREWER_SERIALIZER = registerSerializer(IBeerBrewerRecipe.TYPE_ID, () -> new MultiplayinOneOutSerializer<>(BeerBrewerRecipe::new));
    public static final RegistryObject<IRecipeSerializer<IJuicerRecipe>> JUICER_SERIALIZER = registerSerializer(IJuicerRecipe.TYPE_ID, () -> new OneInOneOutSerializer<>(JuicerRecipe::new));
    public static final RegistryObject<ShapedBurgerSerializer> SHAPED_BURGER_SERIALIZER = SERIALIZERS.register(IShapedBurgerRecipe.TYPE_ID.getPath(), ShapedBurgerSerializer::new);

    public static final IRecipeType<IBeerBrewerRecipe> BEER_BREWER_RECIPE_TYPE = registerType(IBeerBrewerRecipe.TYPE_ID);
    public static final IRecipeType<IJuicerRecipe> JUICER_RECIPE_TYPE = registerType(IJuicerRecipe.TYPE_ID);
    public static final IRecipeType<IShapedBurgerRecipe> SHAPED_BURGER_RECIPE_TYPE = registerType(IShapedBurgerRecipe.TYPE_ID);

    private static <T extends IRecipeType<?>> T registerType(ResourceLocation id) {
        return (T) Registry.register(Registry.RECIPE_TYPE, id, new RecipeType<>());
    }

    private static <T extends IRecipe<?>, S extends IRecipeSerializer<T>> RegistryObject<IRecipeSerializer<T>> registerSerializer(ResourceLocation id, Supplier<? extends S> supplier) {
        return SERIALIZERS.register(id.getPath(), supplier);
    }

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }
}
