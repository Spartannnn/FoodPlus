package com.spartann.foodplus.common.blocks.trees.leaves;

import com.spartann.foodplus.common.blocks.trees.AbstractFPLeavesBlock;
import com.spartann.foodplus.common.registries.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;

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


}
