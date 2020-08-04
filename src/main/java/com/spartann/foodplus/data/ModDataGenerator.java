package com.spartann.foodplus.data;


import com.spartann.foodplus.FoodPlusMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = FoodPlusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {

    private static final boolean ONLY_LANG = false;

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            FoodPlusMod.LOGGER.info("Starting common data gen");
            if (!ONLY_LANG) {
                //generator.addProvider(new BlockTagsDataGen(generator));
                //generator.addProvider(new LootTablesDataGen(generator));
                generator.addProvider(new RecipeDataGen(generator));
            }
        }

        if (event.includeClient()) {
            FoodPlusMod.LOGGER.info("Starting client data gen");
            if (!ONLY_LANG) {
                //generator.addProvider(new BlockModelDataGen(generator, FoodPlusMod.MOD_ID, event.getExistingFileHelper()));
                //generator.addProvider(new BlockStateDataGen(generator, FoodPlusMod.MOD_ID, event.getExistingFileHelper()));
                //generator.addProvider(new ItemModelDataGen(generator, FoodPlusMod.MOD_ID, event.getExistingFileHelper()));
            }
            //generator.addProvider(new LanguagesDataGen(generator));
        }
    }
}
