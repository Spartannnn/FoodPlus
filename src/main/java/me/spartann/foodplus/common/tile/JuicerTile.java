package me.spartann.foodplus.common.tile;

import com.google.common.collect.Lists;
import me.spartann.foodplus.common.container.JuicerContainer;
import me.spartann.foodplus.common.items.BaseFoodItem;
import me.spartann.foodplus.common.items.juicer.juice.JuiceBottleFlavour;
import me.spartann.foodplus.common.items.juicer.juice.JuiceBottleFullness;
import me.spartann.foodplus.common.items.juicer.juice.JuiceBottleItem;
import me.spartann.foodplus.common.recipe.juicer.IJuicerRecipe;
import me.spartann.foodplus.common.registries.ModRecipeTypes;
import me.spartann.foodplus.common.registries.ModTileEntities;
import me.spartann.foodplus.common.util.FunctionalIntReferenceHolder;
import me.spartann.foodplus.common.util.TextComponentUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.List;

public class JuicerTile extends RecipeTile<IJuicerRecipe> {

    public JuicerTile() {
        super(ModTileEntities.JUICER_TILE_2.get(), 2);
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
            if(!canInsert)
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
    public List<FunctionalIntReferenceHolder> getIntReferenceHolder() {
        return Lists.newArrayList(new FunctionalIntReferenceHolder(() -> workingTicks, v -> workingTicks = v),
                new FunctionalIntReferenceHolder(() -> animTime, v -> animTime = v),
                new FunctionalIntReferenceHolder(() -> maxWorkTicks, v -> maxWorkTicks = v));
    }

    @Override
    public boolean notIdle() {
        ItemStack output = this.inventory.getStackInSlot(1);
        if(output.isEmpty())
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
        return TextComponentUtil.stringTextComponent("Bruh", TextFormatting.GRAY);
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new JuicerContainer(p_createMenu_1_, p_createMenu_2_, this);
    }
}
