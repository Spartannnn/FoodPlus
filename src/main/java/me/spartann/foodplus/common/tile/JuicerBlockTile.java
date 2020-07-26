package me.spartann.foodplus.common.tile;

import me.spartann.foodplus.common.container.JuicerContainer;
import me.spartann.foodplus.common.items.BaseFoodItem;
import me.spartann.foodplus.common.items.juicer.juice.JuiceBottleFlavour;
import me.spartann.foodplus.common.items.juicer.juice.JuiceBottleFullness;
import me.spartann.foodplus.common.items.juicer.juice.JuiceBottleItem;
import me.spartann.foodplus.common.recipe.juicer.JuicerRecipe;
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

public class JuicerBlockTile extends BasicItemHolderTile implements ITickableTileEntity, INamedContainerProvider {

    private int ticksUntilFinish = 200;
    private int workTicks = 0;
    private boolean flag = false;

    public JuicerBlockTile() {
        super(ModTileEntities.JUICER_TILE.get(), 2);
    }

    @Override
    public void tick() {
        if (isRunning())
            this.workTicks--;

        if (this.workTicks <= 0 && canCraft() && this.flag)
            this.craft(this.getRecipe(this.getFruit()));

        if (!isRunning())
            if (canCraft()) {
                workTicks = ticksUntilFinish;
                this.flag = true;
            }
    }

    private void craft(JuicerRecipe recipe) {
        if (recipe != null) {
            ItemStack output = recipe.getRecipeOutput();
            ItemStack input = this.getFruit();
            String name = input.getItem().getRegistryName().getPath();
            name = name.replace("_fruit", "");
            JuiceBottleFlavour flavour = JuiceBottleFlavour.byName(name);
            JuiceBottleItem.setFlavour(output, flavour);
            JuiceBottleItem.setFullness(output, JuiceBottleFullness.FULL);
            if(this.getOutput().getCount() != 1)
                output.setCount(this.getOutput().getCount() + 1);
            this.getFruit().shrink(1);
            this.inventory.setStackInSlot(1, output);
            this.workTicks = 0;
            this.flag = false;
        }
    }

    public int getWorkTicks() {
        return workTicks;
    }

    private boolean isRunning() {
        return this.workTicks > 0;
    }

    public ItemStack getFruit() {
        return this.inventory.getStackInSlot(0);
    }

    public ItemStack getOutput() {
        return this.inventory.getStackInSlot(1);
    }

    private boolean canCraft() {
        return !this.getFruit().isEmpty() && this.getFruit().isFood() && this.getFruit().getItem().getFood().equals(BaseFoodItem.FRUIT_FOOD);
    }

    private JuicerRecipe getRecipe(ItemStack stack) {
        if (stack == null) return null;

        Set<IRecipe<?>> recipes = CraftingHelper.findRecipesByType(ModRecipeTypes.JUICER_RECIPE_TYPE);
        for (IRecipe<?> r : recipes) {
            JuicerRecipe recipe = (JuicerRecipe) r;
            if (recipe.matches(new RecipeWrapper(this.inventory), this.world)) {
                return recipe;
            }
        }
        return null;
    }


    @Override
    public ITextComponent getDisplayName() {
        return TextComponentUtil.translationTextComponent("container.juicer");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new JuicerContainer(p_createMenu_1_, p_createMenu_2_, this);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("ticksUnitlFinish", this.ticksUntilFinish);
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.ticksUntilFinish = compound.getInt("tickUntilDry");
    }
}
