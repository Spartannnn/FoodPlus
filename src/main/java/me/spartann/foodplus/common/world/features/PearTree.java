package me.spartann.foodplus.common.world.features;

import me.spartann.foodplus.common.registries.ModBlocks;
import me.spartann.foodplus.common.registries.ModFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class PearTree extends Tree {

    public static final TreeFeatureConfig PEAR_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.PEAR_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(ModBlocks.PEAR_LEAVES.get().getDefaultState()), new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2)
            .foliageHeight(3).ignoreVines().setSapling((net.minecraftforge.common.IPlantable) ModBlocks.PEAR_SAPLING.get()).build();


    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return ModFeatures.PEAR_TREE.get().withConfiguration(PEAR_TREE_CONFIG);
    }


}
