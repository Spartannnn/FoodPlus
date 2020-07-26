package me.spartann.foodplus.common.world.features;

import me.spartann.foodplus.common.items.FruitTypes;
import me.spartann.foodplus.common.registries.ModFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class FruitTree extends Tree {

    private final FruitTypes fruitTypes;

    public FruitTree(FruitTypes fruitType) {
        this.fruitTypes = fruitType;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return ModFeatures.FRUIT_TREE.get().withConfiguration(buildConfig(fruitTypes));
    }

    private static final Random RANDOM = new Random();

    public static TreeFeatureConfig randomConfig() {
        return buildConfig(FruitTypes.values()[RANDOM.nextInt(FruitTypes.values().length - 1)]);
    }

    private static TreeFeatureConfig buildConfig(FruitTypes type) {
        return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(type.getLog().getDefaultState()), new SimpleBlockStateProvider(type.getLeaves().getDefaultState()),
                new BlobFoliagePlacer(2, 0)).baseHeight(4).heightRandA(2).foliageHeight(3).ignoreVines().setSapling(type.getSapling())).build();
    }

}
