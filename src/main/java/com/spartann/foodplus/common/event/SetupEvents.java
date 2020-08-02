package com.spartann.foodplus.common.event;

import com.spartann.foodplus.common.world.gen.OreGen;
import com.spartann.foodplus.common.world.gen.TreeFeatureGen;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class SetupEvents {

    public static void clientSetup(final FMLClientSetupEvent event) {

    }

    public static void commonSetup(final FMLCommonSetupEvent event) {


        TreeFeatureGen.generateTrees();
        OreGen.generateOre();
    }

}
