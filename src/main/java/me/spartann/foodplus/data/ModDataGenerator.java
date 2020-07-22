package me.spartann.foodplus.data;


import me.spartann.foodplus.FoodPlusMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = FoodPlusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            FoodPlusMod.LOGGER.info("Starting common data gen");
            generator.addProvider(new LootTablesDataGen(generator));
            generator.addProvider(new RecipeDataGen(generator));
        }

        if (event.includeClient()) {
            FoodPlusMod.LOGGER.info("Starting client data gen");
            generator.addProvider(new LanguagesDataGen(generator));
        }
    }
}
