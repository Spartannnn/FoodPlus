package com.spartann.foodplus.common.util.helper;

import com.google.common.collect.Sets;
import com.spartann.foodplus.common.recipe.IModRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CraftingHelper {

    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> type, World world) {
        return world != null ? world.getRecipeManager().getRecipes().stream().filter(r -> r.getType() == type).collect(Collectors.toSet()) : Collections.emptySet();
    }

    public static <T extends IInventory, R extends IRecipe<T>> Set<IModRecipe<T>> findModRecipesByType(IRecipeType<R> type, World world) {
        Set<IRecipe<?>> recipes = world.getRecipeManager().getRecipes().stream().filter(r -> r.getType() == type).collect(Collectors.toSet());
        Set<IModRecipe<T>> modRecipes = Sets.newHashSet();
        for (IRecipe<?> rec : recipes) {
            if (rec instanceof IModRecipe)
                modRecipes.add((IModRecipe<T>) rec);
        }
        return modRecipes;
    }

    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> type) {
        @SuppressWarnings("resource")
        ClientWorld world = Minecraft.getInstance().world;
        return world != null ? world.getRecipeManager().getRecipes().stream().filter(r -> r.getType() == type).collect(Collectors.toSet()) : Collections.emptySet();
    }

    public static Set<ItemStack> getAllRecipeInputs(IRecipeType<?> type, World world) {
        Set<ItemStack> out = new HashSet<>();
        Set<IRecipe<?>> recipes = findRecipesByType(type, world);
        for (IRecipe<?> recipe : recipes) {
            NonNullList<Ingredient> inputs = recipe.getIngredients();
            inputs.forEach(i -> {
                for (ItemStack stack : i.getMatchingStacks()) out.add(stack);
            });
        }
        return out;
    }

    public static RecipeWrapper trimEmptySlots(RecipeWrapper inv) {
        IItemHandlerModifiable out = new ItemStackHandler();
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty()) {
                out.insertItem(i, stack, false);
            }
        }

        return new RecipeWrapper(out);
    }


}
