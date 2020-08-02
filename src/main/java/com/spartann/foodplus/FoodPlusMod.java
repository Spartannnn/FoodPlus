package com.spartann.foodplus;

import com.spartann.foodplus.common.event.SetupEvents;
import com.spartann.foodplus.common.group.FoodPlusGroup;
import com.spartann.foodplus.common.registries.*;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(value = FoodPlusMod.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class FoodPlusMod {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "foodplus";
    public static final String VERSION = "1.0.0.0";

    public FoodPlusMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        //Register methods
        ModTileEntities.TILE_ENTITIES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModRecipeTypes.SERIALIZERS.register(modEventBus);
        ModContainers.CONTAINERS.register(modEventBus);


        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        SetupEvents.commonSetup(event);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        SetupEvents.clientSetup(event);
    }

    @SubscribeEvent
    public static void blockItemsRegister(RegistryEvent.Register<Item> items) {
        IForgeRegistry<Item> registry = items.getRegistry();

        ModBlocks.BLOCKS.getEntries().stream().filter(block -> !(block.get() instanceof CropsBlock)).map(RegistryObject::get).forEach(block -> {
            Item.Properties properties = new Item.Properties().group(FoodPlusGroup.INSTANCE);
            BlockItem item = new BlockItem(block, properties);
            item.setRegistryName(block.getRegistryName());
            registry.register(item);
        });
    }


}
