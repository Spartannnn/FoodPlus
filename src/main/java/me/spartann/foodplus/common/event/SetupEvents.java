package me.spartann.foodplus.common.event;

import me.spartann.foodplus.common.world.data.ThirstStorage;
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

        CapabilityManager.INSTANCE.register(ThirstStorage.class, new Capability.IStorage<ThirstStorage>() {
            @Override
            public INBT writeNBT(Capability<ThirstStorage> capability, ThirstStorage instance, Direction side) {
                return null;
            }

            @Override
            public void readNBT(Capability<ThirstStorage> capability, ThirstStorage instance, Direction side, INBT nbt) {
            }

        }, ThirstStorage::new);

        TreeFeatureGen.generateTrees();
        OreGen.generateOre();
    }

}
