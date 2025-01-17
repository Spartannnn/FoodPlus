package com.spartann.foodplus.common.event;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.client.gui.ScreenBeerBrewer;
import com.spartann.foodplus.client.gui.ScreenBurgerMaker;
import com.spartann.foodplus.client.gui.ScreenJuicer;
import com.spartann.foodplus.client.gui.ScreenSandwichMaker;
import com.spartann.foodplus.common.entities.BananaProjectileEntity;
import com.spartann.foodplus.common.items.juice.JuiceBottleColor;
import com.spartann.foodplus.common.registries.ModBlocks;
import com.spartann.foodplus.common.registries.ModContainers;
import com.spartann.foodplus.common.registries.ModEntityTypes;
import com.spartann.foodplus.common.registries.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = FoodPlusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(ModBlocks.PEAR_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.HOP_CROP.get(), RenderType.getCutout());

        ScreenManager.registerFactory(ModContainers.JUICER_CONTAINER.get(), ScreenJuicer::new);
        ScreenManager.registerFactory(ModContainers.BEER_BREWER.get(), ScreenBeerBrewer::new);
        ScreenManager.registerFactory(ModContainers.BURGER_MAKER.get(), ScreenBurgerMaker::new);
        ScreenManager.registerFactory(ModContainers.SANDWICH_MAKER.get(), ScreenSandwichMaker::new);

        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BANANA_PROJECTILE.get(), manager -> {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            return new SpriteRenderer<>(manager, itemRenderer);
        });

    }

    @SubscribeEvent
    public static void onColorRegister(ColorHandlerEvent.Item event) {
        event.getItemColors().register(new JuiceBottleColor(), ModItems.JUICE.get());
    }

}