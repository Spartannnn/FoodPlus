package com.spartann.foodplus.common.tile;

import com.spartann.foodplus.common.container.ContainerSandwichMaker;
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

public class TileSandwichMaker extends TileContainer{

    public TileSandwichMaker() {
        super(ModTileEntities.SANDWICH_MAKER_TILE.get(), 3);
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

    @Override
    public ITextComponent getDisplayName() {
        return TextComponentUtil.translationTextComponent("container.sandwich_maker");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new ContainerSandwichMaker(p_createMenu_1_, p_createMenu_2_, this);
    }
}
