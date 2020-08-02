package com.spartann.foodplus.common.blocks.trees;

import com.google.common.collect.Lists;
import com.spartann.foodplus.common.loottable.ILootTableMultiDropsData;
import com.spartann.foodplus.common.registries.ModItems;
import com.spartann.foodplus.common.util.helper.LootConditionHelper;
import net.minecraft.block.LeavesBlock;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;

import java.util.List;

public abstract class BlockFoodPlusLeaves extends LeavesBlock implements ILootTableMultiDropsData {

    public static final float DEFAULT_DROP_CHANCE = 0.35F;

    public BlockFoodPlusLeaves(Properties properties) {
        super(properties);
    }

    @Override
    public List<ILootCondition.IBuilder> lootConditions() {
        return Lists.newArrayList(SurvivesExplosion.builder(), LootConditionHelper.matchTool(ModItems.HARVEST_TOOL.get()), RandomChance.builder(DEFAULT_DROP_CHANCE));
    }

}
