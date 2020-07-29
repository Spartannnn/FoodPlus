package me.spartann.foodplus.data;

import me.spartann.foodplus.common.registries.ModBlocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.tags.BlockTags;

public class BlockTagsDataGen extends BlockTagsProvider {

    public BlockTagsDataGen(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerTags() {
        getBuilder(BlockTags.LOGS).add(ModBlocks.CHERRY_LOG.get(), ModBlocks.MANGO_LOG.get(), ModBlocks.PEAR_LOG.get());
        getBuilder(BlockTags.LEAVES).add(ModBlocks.CHERRY_LEAVES.get(), ModBlocks.MANGO_LEAVES.get(), ModBlocks.PEAR_LEAVES.get());
    }
}
