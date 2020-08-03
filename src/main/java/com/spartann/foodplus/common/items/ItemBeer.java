package com.spartann.foodplus.common.items;

import com.spartann.foodplus.FoodPlusMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemBeer extends ItemFoodPlus {

    private static final String NBT_TAG = "fullness";

    public ItemBeer(Properties properties) {
        super(properties, null);
        this.addPropertyOverride(new ResourceLocation(FoodPlusMod.MOD_ID, "status"), (stack, world, livingEntity) -> {
            if (isFull(stack))
                return 0;
            else
                return 1;
        });
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        if(isFull(stack)) return 20;
        return super.getUseDuration(stack);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            ItemStack full = new ItemStack(this, 1);
            setFullness(full, true);
            items.add(full);
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (isFull(stack)) {
            setFullness(stack, false);
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (this.isFood()) {
            ItemStack itemstack = playerIn.getHeldItem(handIn);
            if (!isFull(itemstack)) return ActionResult.resultFail(itemstack);
            if (playerIn.canEat(this.getFood().canEatWhenFull())) {
                playerIn.setActiveHand(handIn);
                return ActionResult.resultConsume(itemstack);
            } else {
                return ActionResult.resultFail(itemstack);
            }
        } else {
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));
        }
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        if (isFull(stack)) {
            return new TranslationTextComponent("item." + FoodPlusMod.MOD_ID + ".beer.full");
        } else {
            return new TranslationTextComponent("item." + FoodPlusMod.MOD_ID + ".beer.empty");
        }
    }

    public static void setFullness(ItemStack stack, boolean full) {
        CompoundNBT tag = stack.getOrCreateTag();
        tag.putBoolean(NBT_TAG, full);
    }

    public static boolean isFull(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        if (!tag.contains(NBT_TAG)) return false;
        return tag.getBoolean(NBT_TAG);
    }


}
