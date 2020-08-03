package com.spartann.foodplus.common.tile;

import com.spartann.foodplus.common.container.ContainerBurgerMaker;
import com.spartann.foodplus.common.registries.ModTileEntities;
import com.spartann.foodplus.common.util.FunctionalIntReferenceHolder;
import com.spartann.foodplus.common.util.TextComponentUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.BooleanSupplier;

public class TileBurgerMaker extends TileContainer {


    public TileBurgerMaker() {
        super(ModTileEntities.BURGER_MAKER_TILE.get(), 4);
    }

    @Override
    public ITextComponent getDisplayName() {
        return TextComponentUtil.translationTextComponent("burger_maker");
    }

    public ItemStack[] getInventoryInputItems() {
        ItemStack[] stacks = new ItemStack[4];
        for (int i = 0; i < 4; i++)
            stacks[i] = this.inventory.getStackInSlot(i);
        return stacks;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new ContainerBurgerMaker(p_createMenu_1_, p_createMenu_2_, this);
    }

    @Override
    public void readData(CompoundNBT nbt) {

    }

    @Override
    public CompoundNBT writeData(CompoundNBT nbt) {
        return null;
    }

    @Override
    public List<FunctionalIntReferenceHolder> getIntReferenceHolder() {
        return null;
    }

    @Override
    public BooleanSupplier isItemValid(int slot, ItemStack stack) {
        return null;
    }
}
