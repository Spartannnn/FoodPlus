package me.spartann.foodplus.common.blocks.trees.leaves;

import me.spartann.foodplus.common.blocks.trees.AbstractFPLeavesBlock;
import me.spartann.foodplus.common.registries.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;

public class MangoLeaveBlock extends AbstractFPLeavesBlock {

    public MangoLeaveBlock(Properties properties) {
        super(properties);
    }

    @Override
    public NonNullList<Item> getDrops() {
        return NonNullList.withSize(1, ModItems.MANGO_FRUIT.get());
    }

    @Override
    public Block dataBlock() {
        return this;
    }
}
