package me.spartann.foodplus.common.tile;

import me.spartann.foodplus.common.recipe.beerbrewer.BeerBrewerRecipe;
import me.spartann.foodplus.common.registries.ModRecipeTypes;
import me.spartann.foodplus.common.registries.ModTileEntities;
import me.spartann.foodplus.common.util.TextComponentUtil;
import me.spartann.foodplus.common.util.helper.CraftingHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;
import java.util.Set;

public class BeerBrewerTile extends BasicItemHolderTile implements INamedContainerProvider, ITickableTileEntity {

    private int workTicks = 0;
    private int animTicks = 0;
    private boolean craftFlag = false;
    private BeerBrewerRecipe currentRecipe;

    public BeerBrewerTile() {
        super(ModTileEntities.BEER_BREWER_TILE.get(), 4, ItemStack.EMPTY);
    }

    @Override
    public void tick() {
        if (isWorking()) {
            this.workTicks--;
            this.animTicks++;
        }
        if (this.workTicks == 0 && this.craftFlag)
            this.craft(this.currentRecipe);

        if (!isWorking()) {
            if (canCraft()) {
                this.currentRecipe = getRecipe();
                if(this.currentRecipe == null)
                    throw new NullPointerException("Recipe not found");
                this.workTicks = this.currentRecipe.getSecondsUntilFinish();
                for (int i = 0; i < getInputSlots().length; i++) {
                    getInputSlots()[i].shrink(1);
                    if(getInputSlots()[i].getCount() == 0)
                        this.inventory.setStackInSlot(i, ItemStack.EMPTY);
                }
                this.craftFlag = true;
            }
        }

    }

    public boolean canCraft() {
        ItemStack[] inputSlots = getInputSlots();
        return !inputSlots[0].isEmpty() && !inputSlots[1].isEmpty() && !inputSlots[2].isEmpty();
    }

    public boolean isWorking() {
        return this.workTicks > 0;
    }

    public ItemStack[] getInputSlots() {
        return new ItemStack[]{this.inventory.getStackInSlot(0), this.inventory.getStackInSlot(1), this.inventory.getStackInSlot(2)};
    }

    public ItemStack getOutput() {
        return this.inventory.getStackInSlot(3);
    }

    public int getWorkProgress() {
        if (this.currentRecipe == null)
            return 0;
        return animTicks != 0 ? animTicks * 24 / this.currentRecipe.getSecondsUntilFinish() : 0;
    }

    public BeerBrewerRecipe getCurrentRecipe() {
        return currentRecipe;
    }

    private void craft(BeerBrewerRecipe recipe) {
        if (recipe != null) {
            ItemStack output = recipe.getRecipeOutput();
            if (this.getOutput().getCount() == 0)
                this.inventory.setStackInSlot(3, output);
            else if (this.getOutput().getCount() > 0) {
                output.setCount(this.getOutput().getCount() + 1);
                this.inventory.setStackInSlot(3, output);
            }
            this.workTicks = 0;
            this.animTicks = 0;
            this.craftFlag = false;
            this.currentRecipe = null;
        }
    }

    private BeerBrewerRecipe getRecipe() {
        Set<IRecipe<?>> recipes = CraftingHelper.findRecipesByType(ModRecipeTypes.BEER_BREWER_RECIPE_TYPE);
        for (IRecipe<?> r : recipes) {
            BeerBrewerRecipe recipe = (BeerBrewerRecipe) r;
            if (recipe.matches(new RecipeWrapper(this.inventory), this.world)) {
                return recipe;
            }
        }
        return null;

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

    public static int getWorkProgress(BeerBrewerTile beerBrewerTile) {
        return beerBrewerTile.getWorkProgress();
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        nbt.putInt("workTicks", this.workTicks);
        nbt.putInt("animTicks", this.animTicks);
        return super.write(nbt);
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);
        this.workTicks = nbt.getInt("workTicks");
        this.animTicks = nbt.getInt("animTicks");
    }
}
