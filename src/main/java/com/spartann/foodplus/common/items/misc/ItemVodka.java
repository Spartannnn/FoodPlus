package com.spartann.foodplus.common.items.misc;

import com.google.common.collect.Lists;
import com.spartann.foodplus.common.items.ItemFoodPlus;
import com.spartann.foodplus.common.registries.ModSounds;
import com.spartann.foodplus.common.util.TextComponentUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemVodka extends ItemFoodPlus {

    public ItemVodka(Properties properties) {
        super(properties, () -> Lists.newArrayList(TextComponentUtil.stringTextComponent("RUSSIA", TextFormatting.RED, TextFormatting.BOLD)));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (entityLiving instanceof PlayerEntity)
            worldIn.playSound(((PlayerEntity) entityLiving), ((PlayerEntity) entityLiving).getPosition(), ModSounds.SOVIET_ANTHEM.get(), SoundCategory.MUSIC, 1.0F, 1.0F);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
