package com.spartann.foodplus.common.registries;

import com.spartann.foodplus.FoodPlusMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, FoodPlusMod.MOD_ID);

    public static final RegistryObject<SoundEvent> SOVIET_ANTHEM = SOUNDS.register("item_soviet_anthem",
            () -> new SoundEvent(new ResourceLocation(FoodPlusMod.MOD_ID, "item_soviet_anthem")));

}
