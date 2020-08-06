package com.spartann.foodplus.common.items;

import com.spartann.foodplus.common.blocks.trees.BlockFruit;
import com.spartann.foodplus.common.registries.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.IPlantable;

public enum FruitTypes implements IStringSerializable {

    PEAR(ModBlocks.PEAR_LOG.get(), ModBlocks.PEAR_LEAVES.get(), (IPlantable) ModBlocks.PEAR_SAPLING.get()),
    CHERRY(ModBlocks.CHERRY_LOG.get(), ModBlocks.CHERRY_LEAVES.get(), (IPlantable) ModBlocks.CHERRY_SAPLING.get()),
    MANGO(ModBlocks.MANGO_LOG.get(), ModBlocks.MANGO_LEAVES.get(), (IPlantable) ModBlocks.MANGO_SAPLING.get()),
    BANANA(ModBlocks.BANANA_LOG.get(), ModBlocks.BANANA_LEAVES.get(), (IPlantable) ModBlocks.BANANA_SAPLING.get());

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


    @Override
    public String getName() {
        return this.name().toLowerCase();
    }

    public static BlockState getFruitState(FruitTypes type) {
        return ModBlocks.FRUIT.get().getDefaultState().with(BlockFruit.FRUIT_TYPE, type);
    }

}
