package com.spartann.foodplus.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class ItemApplyEffect extends ItemBaseFood {

    private final Supplier<EffectInstance> effects;

    public ItemApplyEffect(Properties properties, Food food, Supplier<EffectInstance> effects) {
        super(properties, food);
        this.effects = effects;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        entityLiving.addPotionEffect(effects.get());
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
