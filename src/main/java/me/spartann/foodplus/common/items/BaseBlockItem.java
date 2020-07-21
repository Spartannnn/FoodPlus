package me.spartann.foodplus.common.items;

import me.spartann.foodplus.common.group.FoodPlusGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class BaseBlockItem extends BlockItem {

    public BaseBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder.group(FoodPlusGroup.INSTANCE));
    }
}
