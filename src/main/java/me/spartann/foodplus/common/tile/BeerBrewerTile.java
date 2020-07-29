package me.spartann.foodplus.common.tile;

import me.spartann.foodplus.common.registries.ModTileEntities;
import me.spartann.foodplus.common.util.TextComponentUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class BeerBrewerTile extends BasicItemHolderTile implements INamedContainerProvider, ITickableTileEntity {

    public BeerBrewerTile() {
        super(ModTileEntities.BEER_BREWER_TILE.get(), 3 /* TODO: Change later*/, ItemStack.EMPTY);
    }

    @Override
    public ITextComponent getDisplayName() {
        return TextComponentUtil.translationTextComponent("container.beer_brewer");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return null;
    }

    @Override
    public void tick() {

    }
}
