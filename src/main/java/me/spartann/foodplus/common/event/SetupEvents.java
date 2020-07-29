package me.spartann.foodplus.common.event;

import me.spartann.foodplus.common.world.data.ThirstStorageCapability;
import me.spartann.foodplus.common.world.gen.OreGen;
import me.spartann.foodplus.common.world.gen.TreeFeatureGen;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class SetupEvents {

    public static void clientSetup(final FMLClientSetupEvent event) {

    }

    public static void commonSetup(final FMLCommonSetupEvent event) {

        CapabilityManager.INSTANCE.register(ThirstStorageCapability.class, new Capability.IStorage<ThirstStorageCapability>() {
            @Override
            public INBT writeNBT(Capability<ThirstStorageCapability> capability, ThirstStorageCapability instance, Direction side) {
                return null;
            }

            @Override
            public void readNBT(Capability<ThirstStorageCapability> capability, ThirstStorageCapability instance, Direction side, INBT nbt) {
            }

        }, ThirstStorageCapability::new);

        TreeFeatureGen.generateTrees();
        OreGen.generateOre();
    }

}
