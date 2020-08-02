package com.spartann.foodplus.common.blocks;

import com.google.common.collect.Lists;
import com.spartann.foodplus.common.loottable.ILootTableMultiDropsData;
import com.spartann.foodplus.common.registries.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;

import java.util.List;

public class SaltOreBlock extends Block implements ILootTableMultiDropsData {

    public SaltOreBlock(Properties properties) {
        super(properties.hardnessAndResistance(3.0F, 3.0F));
    }

    @Override
    public NonNullList<Item> getDrops() {
        return NonNullList.withSize(1, ModItems.SALT.get());
    }

    @Override
    public Block dataBlock() {
        return this;
    }

    @Override
    public List<ILootCondition.IBuilder> lootConditions() {
        return Lists.newArrayList(SurvivesExplosion.builder());
    }
}
