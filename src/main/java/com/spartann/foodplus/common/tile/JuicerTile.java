package com.spartann.foodplus.common.tile;

import com.spartann.foodplus.common.container.JuicerContainer;
import com.spartann.foodplus.common.items.BaseFoodItem;
import com.spartann.foodplus.common.items.juicer.JuiceBottleFlavour;
import com.spartann.foodplus.common.items.juicer.JuiceBottleFullness;
import com.spartann.foodplus.common.items.juicer.JuiceBottleItem;
import com.spartann.foodplus.common.recipe.juicer.IJuicerRecipe;
import com.spartann.foodplus.common.registries.ModRecipeTypes;
import com.spartann.foodplus.common.registries.ModTileEntities;
import com.spartann.foodplus.common.util.TextComponentUtil;
import com.spartann.foodplus.common.util.helper.ItemStackHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;
import java.util.function.BooleanSupplier;

public class JuicerTile extends RecipeTile<IJuicerRecipe> {

    public JuicerTile() {
        super(ModTileEntities.JUICER_TILE.get(), 2);
    }

    @Override
    public IRecipeType<IJuicerRecipe> getRecipeType() {
        return ModRecipeTypes.JUICER_RECIPE_TYPE;
    }

    @Override
    public boolean canCraft() {
        return !this.inventory.getStackInSlot(0).isEmpty() && this.inventory.getStackInSlot(0).getItem() instanceof BaseFoodItem && this.inventory.getStackInSlot(0).isFood()
                && this.inventory.getStackInSlot(0).getItem().getFood().equals(BaseFoodItem.FRUIT_FOOD);
    }

    @Override
    public void craft(@Nullable IJuicerRecipe recipe) {
        if (recipe != null) {
            ItemStack input = recipe.getInput().getMatchingStacks()[0];
            ItemStack stack = recipe.getRecipeOutput();
            String name = input.getItem().getRegistryName().getPath();
            name = name.replace("_fruit", "");
            JuiceBottleFlavour flavour = JuiceBottleFlavour.byName(name);
            JuiceBottleItem.setFlavour(stack, flavour);
            JuiceBottleItem.setFullness(stack, JuiceBottleFullness.FULL);
            boolean canInsert = this.inventory.insertItem(1, stack, false).isEmpty();
            if (!canInsert)
                this.idle = true;
            else
                this.finishCrafting();

        }
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{0};
    }

    @Override
    public BooleanSupplier isItemValid(int slot, ItemStack stack) {
        switch (slot) {
            case 0:
                return () -> ItemStackHelper.isFruit(stack);
            case 1:
                return () -> stack.getItem() instanceof JuiceBottleItem;
        }

        return null;
    }

    @Override
    public boolean notIdle() {
        ItemStack output = this.inventory.getStackInSlot(1);
        if (output.isEmpty())
            return true;
        return output.getCount() < output.getMaxStackSize();
    }

    @Override
    public void readData(CompoundNBT nbt) {

    }

    @Override
    public CompoundNBT writeData(CompoundNBT nbt) {
        return null;
    }

    @Override
    public ITextComponent getDisplayName() {
        return TextComponentUtil.translationTextComponent("juicer");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new JuicerContainer(p_createMenu_1_, p_createMenu_2_, this);
    }
}
