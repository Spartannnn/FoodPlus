package me.spartann.foodplus.common.world.features;

import me.spartann.foodplus.common.items.FruitTypes;
import me.spartann.foodplus.common.registries.ModBlocks;
import me.spartann.foodplus.common.registries.ModFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class FruitTree extends Tree {

    public static final TreeFeatureConfig PEAR_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.PEAR_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(ModBlocks.PEAR_LEAVES.get().getDefaultState()), new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2)
            .foliageHeight(3).ignoreVines().setSapling((net.minecraftforge.common.IPlantable) ModBlocks.PEAR_SAPLING.get()).build();

    public static final TreeFeatureConfig CHERRY_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.CHERRY_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(ModBlocks.CHERRY_LEAVES.get().getDefaultState()), new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2)
            .foliageHeight(3).ignoreVines().setSapling((net.minecraftforge.common.IPlantable) ModBlocks.CHERRY_SAPLING.get()).build();

    private FruitTypes fruitTypes;

    public FruitTree(FruitTypes fruitType) {
        this.fruitTypes = fruitType;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        switch (fruitTypes) {
            case CHERRY:
                return ModFeatures.FRUIT_TREE.get().withConfiguration(CHERRY_TREE_CONFIG);
            case PEAR:
                return ModFeatures.FRUIT_TREE.get().withConfiguration(PEAR_TREE_CONFIG);
        }
        return ModFeatures.FRUIT_TREE.get().withConfiguration(PEAR_TREE_CONFIG);
    }

    private static final Random RANDOM = new Random();

    public static TreeFeatureConfig randomConfig() {
        int i = RANDOM.nextInt(FruitTypes.values().length);
        switch (i) {
            case 0:
                return PEAR_TREE_CONFIG;
            case 1:
                return CHERRY_TREE_CONFIG;
        }
        return PEAR_TREE_CONFIG;
    }

}
