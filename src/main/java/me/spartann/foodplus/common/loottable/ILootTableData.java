package me.spartann.foodplus.common.loottable;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

import java.util.List;

public interface ILootTableData {

    NonNullList<Item> getDrops();

    Block dataBlock();

    List<ILootCondition.IBuilder> lootConditions();

}
