package com.spartann.foodplus.common.registries;

import com.spartann.foodplus.common.world.features.FruitTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;

public class ModFeatures {


    public static final Feature<NoFeatureConfig> FRUIT_TREE = new FruitTreeFeature(NoFeatureConfig::deserialize);

    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().register(FRUIT_TREE.setRegistryName("test_tree_fruit"));
    }

}
