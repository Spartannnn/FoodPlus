package com.spartann.foodplus.common.tile;

import com.spartann.foodplus.common.container.ContainerBurgerMaker;
import com.spartann.foodplus.common.recipe.IModRecipe;
import com.spartann.foodplus.common.recipe.burgermaker.IBurgerMakerRecipe;
import com.spartann.foodplus.common.registries.ModRecipeTypes;
import com.spartann.foodplus.common.registries.ModTileEntities;
import com.spartann.foodplus.common.util.MethodsUtil;
import com.spartann.foodplus.common.util.TextComponentUtil;
import com.spartann.foodplus.common.util.helper.CraftingHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.function.BooleanSupplier;

public class TileBurgerMaker extends TileRecipe<IBurgerMakerRecipe> {

    public TileBurgerMaker() {
        super(ModTileEntities.BURGER_MAKER_TILE.get(), 5);
    }

    @Override
    public IRecipeType<IBurgerMakerRecipe> getRecipeType() {
        return ModRecipeTypes.BURGER_MAKER_RECIPE_TYPE;
    }

    @Override
    public boolean canCraft() {
        return !MethodsUtil.containsEmptyElement(this.getCurrentInputItems());
    }

    @Override
    public void craft(@Nullable IBurgerMakerRecipe recipe) {
        if(recipe != null) {
            ItemStack output = recipe.getRecipeOutput();
            boolean canInsert = this.inventory.insertItem(4, output, false).isEmpty();
            if (!canInsert)
                this.idle = true;
            else
                this.finishCrafting();
            this.markDirty();
        }
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{0, 1, 2, 4};
    }

    @Override
    public boolean notIdle() {
        ItemStack output = this.inventory.getStackInSlot(4);
        return output.isEmpty() || output.getCount() < output.getMaxStackSize();
    }

    @Override
    public void readData(CompoundNBT nbt) {

    }

    @Override
    public CompoundNBT writeData(CompoundNBT nbt) {
        return null;
    }

    @Override
    public BooleanSupplier isItemValid(int slot, ItemStack stack) {
        Set<IModRecipe<RecipeWrapper>> recipes = CraftingHelper.findModRecipesByType(this.getRecipeType(), this.getWorld());
        switch (slot) {
            case 0:
            case 1:
            case 2:
            case 3:
                return this.checkItem(slot, stack);
            case 4:
                for (IModRecipe<RecipeWrapper> modRecipe : recipes)
                    if (modRecipe instanceof IBurgerMakerRecipe) {
                        IBurgerMakerRecipe rec = (IBurgerMakerRecipe) modRecipe;
                        if (rec.getRecipeOutput().isItemEqual(stack))
                            return () -> true;
                    }

        }
        return null;
    }

    private BooleanSupplier checkItem(int index, ItemStack stack) {
        Set<IModRecipe<RecipeWrapper>> recipes = CraftingHelper.findModRecipesByType(this.getRecipeType(), this.getWorld());
        for (IModRecipe<RecipeWrapper> modRecipe : recipes)
            if (modRecipe instanceof IBurgerMakerRecipe) {
                IBurgerMakerRecipe rec = (IBurgerMakerRecipe) modRecipe;
                if (rec.getInputs()[index].getMatchingStacks()[0].isItemEqual(stack))
                    return () -> true;
            }
        return null;
    }

    @Override
    public ITextComponent getDisplayName() {
        return TextComponentUtil.translationTextComponent("burger_maker");
    }

    public ItemStack[] getInventoryInputItems() {
        ItemStack[] stacks = new ItemStack[4];
        for(int i = 0; i < 4; i++)
            stacks[i] = this.inventory.getStackInSlot(i);
        return stacks;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new ContainerBurgerMaker(p_createMenu_1_, p_createMenu_2_, this);
    }
}
