package me.spartann.foodplus.common.blocks.trees;

import com.google.common.collect.Lists;
import me.spartann.foodplus.common.loottable.ILootTableMultiDropsData;
import me.spartann.foodplus.common.registries.ModItems;
import me.spartann.foodplus.common.util.helper.LootConditionHelper;
import net.minecraft.block.LeavesBlock;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;

import java.util.List;

public abstract class AbstractFPLeavesBlock extends LeavesBlock implements ILootTableMultiDropsData {

    public static final float DEFAULT_DROP_CHANCE = 0.35F;

    public AbstractFPLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public List<ILootCondition.IBuilder> lootConditions() {
        return Lists.newArrayList(SurvivesExplosion.builder(), LootConditionHelper.matchTool(ModItems.HARVEST_TOOL.get()), RandomChance.builder(DEFAULT_DROP_CHANCE));
    }

}
