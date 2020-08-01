package me.spartann.foodplus.common.tile;

import me.spartann.foodplus.common.recipe.beerbrewer.IBeerBrewerRecipe;
import me.spartann.foodplus.common.registries.ModRecipeTypes;
import me.spartann.foodplus.common.registries.ModTileEntities;
import me.spartann.foodplus.common.util.FunctionalIntReferenceHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;
import java.util.List;

public class BeerBrewerTile extends RecipeTile<IBeerBrewerRecipe> {

    public BeerBrewerTile() {
        super(ModTileEntities.BEER_BREWER_TILE.get(), 4);
    }

    @Override
    public IRecipeType<IBeerBrewerRecipe> getRecipeType() {
        return ModRecipeTypes.BEER_BREWER_RECIPE_TYPE;
    }

    @Override
    public boolean canCraft() {
        return false;
    }

    @Override
    public void craft(@Nullable IBeerBrewerRecipe recipe) {

    }

    @Override
    public int[] getInputSlots() {
        return new int[]{0, 1, 2};
    }

    @Override
    public boolean notIdle() {
        return false;
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
    public ITextComponent getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return null;
    }
}
