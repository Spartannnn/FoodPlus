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
            System.out.println("Starting common data gen");
            generator.addProvider(new LootTablesDataGen(generator));
        }

        if (event.includeClient()) {
            System.out.println("Starting client data gen");
            generator.addProvider(new LanguagesDataGen(generator));
        }
    }
}
