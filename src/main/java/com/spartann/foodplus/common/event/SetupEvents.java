package com.spartann.foodplus.common.event;

import com.spartann.foodplus.client.render.BurgerMakerTileEntityRenderer;
import com.spartann.foodplus.common.registries.ModTileEntities;
import com.spartann.foodplus.common.world.gen.OreGen;
import com.spartann.foodplus.common.world.gen.TreeFeatureGen;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class SetupEvents {

    public static void clientSetup(final FMLClientSetupEvent event) {

        ClientRegistry.bindTileEntityRenderer(ModTileEntities.BURGER_MAKER_TILE.get(), BurgerMakerTileEntityRenderer::new);

    }

    public static void commonSetup(final FMLCommonSetupEvent event) {


        TreeFeatureGen.generateTrees();
        OreGen.generateOre();
    }

}
