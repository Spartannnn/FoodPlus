package me.spartann.foodplus.common.world.gen;

import me.spartann.foodplus.common.registries.ModFeatures;
import me.spartann.foodplus.common.world.features.PearTree;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;

public class TreeFeatureGen {

    public static void generateTrees() {
        BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS).forEach(biome -> {
            ConfiguredFeature<?, ?> cf = ModFeatures.PEAR_TREE.get().withConfiguration(PearTree.PEAR_TREE_CONFIG);
            cf.withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, 0.5F, 1)));
            biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, cf);
        });
    }

}
