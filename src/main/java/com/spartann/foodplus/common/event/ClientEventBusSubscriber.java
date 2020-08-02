package com.spartann.foodplus.common.event;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.client.gui.BeerBrewerScreen;
import com.spartann.foodplus.client.gui.JuicerScreen;
import com.spartann.foodplus.common.items.juice.JuiceBottleColor;
import com.spartann.foodplus.common.registries.ModBlocks;
import com.spartann.foodplus.common.registries.ModContainers;
import com.spartann.foodplus.common.registries.ModItems;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = FoodPlusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(ModBlocks.PEAR_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.HOP_CROP.get(), RenderType.getCutout());

        ScreenManager.registerFactory(ModContainers.JUICER_CONTAINER.get(), JuicerScreen::new);
        ScreenManager.registerFactory(ModContainers.BEER_BREWER.get(), BeerBrewerScreen::new);

    }

    @SubscribeEvent
    public static void onColorRegister(ColorHandlerEvent.Item event) {
        event.getItemColors().register(new JuiceBottleColor(), ModItems.JUICE.get());
    }

}