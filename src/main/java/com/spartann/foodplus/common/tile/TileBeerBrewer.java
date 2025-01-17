package com.spartann.foodplus.common.tile;

import com.spartann.foodplus.common.container.ContainerBeerBrewer;
import com.spartann.foodplus.common.items.ItemBeer;
import com.spartann.foodplus.common.recipe.IModRecipe;
import com.spartann.foodplus.common.recipe.beerbrewer.IBeerBrewerRecipe;
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

public class TileBeerBrewer extends TileRecipe<IBeerBrewerRecipe> {

    public TileBeerBrewer() {
        super(ModTileEntities.BEER_BREWER_TILE.get(), 4);
    }

    @Override
    public IRecipeType<IBeerBrewerRecipe> getRecipeType() {
        return ModRecipeTypes.BEER_BREWER_RECIPE_TYPE;
    }

    @Override
    public boolean canCraft() {
        return !MethodsUtil.containsEmptyElement(this.getCurrentInputItems());
    }

    @Override
    public void craft(@Nullable IBeerBrewerRecipe recipe) {
        if (recipe != null) {
            ItemStack output = recipe.getRecipeOutput();
            ItemBeer.setFullness(output, true);
            boolean canInsert = this.inventory.insertItem(3, output, false).isEmpty();
            if (!canInsert)
                this.idle = true;
            else
                this.finishCrafting();
            this.markDirty();
        }
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{0, 1, 2};
    }

    @Override
    public boolean notIdle() {
        ItemStack output = this.inventory.getStackInSlot(3);
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
                return this.checkItem(slot, stack);
            case 3:
                for (IModRecipe<RecipeWrapper> modRecipe : recipes)
                    if (modRecipe instanceof IBeerBrewerRecipe) {
                        IBeerBrewerRecipe rec = (IBeerBrewerRecipe) modRecipe;
                        if (rec.getRecipeOutput().isItemEqual(stack))
                            return () -> true;
                    }
        }

        return null;
    }

    private BooleanSupplier checkItem(int index, ItemStack stack) {
        Set<IModRecipe<RecipeWrapper>> recipes = CraftingHelper.findModRecipesByType(this.getRecipeType(), this.getWorld());
        for(IModRecipe<RecipeWrapper> modRecipe : recipes)
            if(modRecipe instanceof IBeerBrewerRecipe) {
                IBeerBrewerRecipe rec = (IBeerBrewerRecipe) modRecipe;
                if(rec.getInputs()[index].getMatchingStacks()[0].isItemEqual(stack))
                    return () -> true;
            }
        return null;
    }

    @Override
    public ITextComponent getDisplayName() {
        return TextComponentUtil.translationTextComponent("beer_brewer");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new ContainerBeerBrewer(p_createMenu_1_, p_createMenu_2_, this);
    }
}
