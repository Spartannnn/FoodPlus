package com.spartann.foodplus.common.items.misc;

import com.google.common.collect.Lists;
import com.spartann.foodplus.common.entities.BananaProjectileEntity;
import com.spartann.foodplus.common.items.ItemFoodPlus;
import com.spartann.foodplus.common.util.TextComponentUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBanana extends ItemFoodPlus {

    public ItemBanana(Properties properties) {
        super(properties, () -> Lists.newArrayList(TextComponentUtil.stringTextComponent("A mysterious banana... hmmmm.. throw it!", TextFormatting.RED)));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack item = playerIn.getHeldItem(handIn);
        if(!worldIn.isRemote) {
            BananaProjectileEntity bananaEntity = new BananaProjectileEntity(worldIn, playerIn);
            bananaEntity.setItem(item);
            bananaEntity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.addEntity(bananaEntity);
        }

        if(!playerIn.abilities.isCreativeMode)
            item.shrink(1);

        return ActionResult.resultSuccess(item);
    }
}
