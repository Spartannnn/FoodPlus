package com.spartann.foodplus.data;

import com.spartann.foodplus.common.registries.ModBlocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;

public class BlockTagsDataGen extends BlockTagsProvider {

    public BlockTagsDataGen(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerTags() {
        getBuilder(BlockTags.LOGS).add(ModBlocks.CHERRY_LOG.get(), ModBlocks.MANGO_LOG.get(), ModBlocks.PEAR_LOG.get(), ModBlocks.BANANA_LOG.get());
        getBuilder(BlockTags.LEAVES).add(ModBlocks.CHERRY_LEAVES.get(), ModBlocks.MANGO_LEAVES.get(), ModBlocks.PEAR_LEAVES.get(), ModBlocks.BANANA_LEAVES.get());
        getBuilder(Tags.Blocks.ORES).add(ModBlocks.SALT_ORE.get());
    }
}
