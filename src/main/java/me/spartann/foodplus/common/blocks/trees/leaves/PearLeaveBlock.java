package me.spartann.foodplus.common.blocks.trees.leaves;

import com.google.common.collect.Lists;
import me.spartann.foodplus.common.blocks.trees.AbstractFPLeavesBlock;
import me.spartann.foodplus.common.registries.ModItems;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.MatchTool;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;

import java.util.List;

public class PearLeaveBlock extends AbstractFPLeavesBlock {

    public PearLeaveBlock(Properties properties) {
        super(properties);
    }

    @Override
    public NonNullList<Item> getDrops() {
        return NonNullList.withSize(1, ModItems.PEAR_FRUIT.get());
    }

    @Override
    public Block dataBlock() {
        return this;
    }

    @Override
    public List<ILootCondition.IBuilder> lootConditions() {
        return Lists.newArrayList(SurvivesExplosion.builder(), MatchTool.builder(ItemPredicate.Builder.create().item(Items.APPLE)));
    }
}
