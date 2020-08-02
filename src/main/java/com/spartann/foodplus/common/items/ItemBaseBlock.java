package com.spartann.foodplus.common.items;

import com.spartann.foodplus.common.group.FoodPlusGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class ItemBaseBlock extends BlockItem {

    public ItemBaseBlock(Block blockIn, Properties builder) {
        super(blockIn, builder.group(FoodPlusGroup.INSTANCE));
    }
}
