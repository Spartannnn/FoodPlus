package me.spartann.foodplus.common.items;

import me.spartann.foodplus.common.registries.ModBlocks;
import me.spartann.foodplus.common.registries.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

public enum FruitTypes {

    PEAR(ModBlocks.PEAR_LOG.get(), ModBlocks.PEAR_LEAVES.get(), (IPlantable) ModBlocks.PEAR_SAPLING.get()),
    CHERRY(ModBlocks.CHERRY_LOG.get(), ModBlocks.CHERRY_LEAVES.get(), (IPlantable) ModBlocks.CHERRY_SAPLING.get()),
    MANGO(ModBlocks.MANGO_LOG.get(), ModBlocks.MANGO_LEAVES.get(), (IPlantable) ModBlocks.MANGO_SAPLING.get());

    private Block log;
    private Block leaves;
    private IPlantable sapling;

    FruitTypes(Block log, Block leaves, IPlantable sapling) {
        this.log = log;
        this.leaves = leaves;
        this.sapling = sapling;
    }


    public Block getLeaves() {
        return leaves;
    }

    public Block getLog() {
        return log;
    }

    public IPlantable getSapling() {
        return sapling;
    }

}
