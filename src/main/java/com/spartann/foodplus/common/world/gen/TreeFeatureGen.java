package com.spartann.foodplus.common.world.gen;

import com.spartann.foodplus.common.registries.ModFeatures;
import com.spartann.foodplus.common.world.features.FruitTree;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;

public class TreeFeatureGen {

    public static void generateTrees() {
        BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST).forEach(biome -> biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, fruitTree(0.1F)));
        BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE).forEach(biome -> biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, fruitTree(0.15F)));
    }

    private static ConfiguredFeature<?, ?> fruitTree(float chance) {
        return ModFeatures.FRUIT_TREE.get().withConfiguration(FruitTree.randomConfig())
                .withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, chance, 1)));
    }

}
