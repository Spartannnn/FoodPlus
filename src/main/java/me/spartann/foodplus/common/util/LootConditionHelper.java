package me.spartann.foodplus.common.util;

import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.MatchTool;

public class LootConditionHelper {

    public static ILootCondition.IBuilder matchTool(Item tool) {
        return MatchTool.builder(ItemPredicate.Builder.create().item(tool));
    }

}
