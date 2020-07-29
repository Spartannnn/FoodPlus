package me.spartann.foodplus.common.event;

import me.spartann.foodplus.FoodPlusMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FoodPlusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerTick {

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {


    }


}
