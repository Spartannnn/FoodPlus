package com.spartann.foodplus.common.registries;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.client.particles.LightningParticle;
import com.spartann.foodplus.client.particles.ManawaveParticle;
import com.spartann.foodplus.client.particles.ShimmerParticle;
import com.spartann.foodplus.client.particles.data.ScaledColoredParticleData;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.awt.*;

@Mod.EventBusSubscriber(modid = FoodPlusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModParticleTypes {

    @ObjectHolder(FoodPlusMod.MOD_ID + ":shimmer")
    public static ScaledColoredParticleData SHIMMER;
    @ObjectHolder(FoodPlusMod.MOD_ID + ":lightning")
    public static ScaledColoredParticleData LIGHTNING;
    @ObjectHolder(FoodPlusMod.MOD_ID + ":manawave")
    public static ScaledColoredParticleData MANAWAVE;

    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> evt) {
        IForgeRegistry<ParticleType<?>> registry = evt.getRegistry();
        registry.register(new BasicParticleType(true).setRegistryName("minecraft", "flame"));
        registry.register(new ScaledColoredParticleData(SHIMMER, true, Color.WHITE.getRGB(), 1).setRegistryName(FoodPlusMod.MOD_ID, "shimmer"));
        registry.register(new ScaledColoredParticleData(LIGHTNING, true, Color.WHITE.getRGB(), 1).setRegistryName(FoodPlusMod.MOD_ID, "lightning"));
        registry.register(new ScaledColoredParticleData(MANAWAVE, true, Color.WHITE.getRGB(), 1).setRegistryName(FoodPlusMod.MOD_ID, "manawave"));

    }

    @SuppressWarnings("resource")
    @SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent evt) {
        Minecraft.getInstance().particles.registerFactory(SHIMMER, ShimmerParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(LIGHTNING, LightningParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(MANAWAVE, ManawaveParticle.Factory::new);

    }


}
