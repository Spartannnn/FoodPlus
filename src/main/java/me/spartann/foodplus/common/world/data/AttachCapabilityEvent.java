package me.spartann.foodplus.common.world.data;

import me.spartann.foodplus.FoodPlusMod;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FoodPlusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AttachCapabilityEvent {

    @SubscribeEvent
    public void attachToPlayer(AttachCapabilitiesEvent<Entity> event) {
        event.addCapability(new ResourceLocation(FoodPlusMod.MOD_ID, "player_thirst_cap"), new PlayerThirstStorageProvider());
    }

}
