package com.spartann.foodplus.common.registries;

import com.spartann.foodplus.FoodPlusMod;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, FoodPlusMod.MOD_ID);

    public static final RegistryObject<Feature<TreeFeatureConfig>> FRUIT_TREE = FEATURES.register("fruit_tree", () -> new TreeFeature(TreeFeatureConfig::func_227338_a_));


}
