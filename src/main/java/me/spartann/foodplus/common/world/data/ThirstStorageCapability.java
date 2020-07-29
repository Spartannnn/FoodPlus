package me.spartann.foodplus.common.world.data;

import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;

public class ThirstStorageCapability {

    private int thirst = 0;
    private int maxThirst = 0;

    public void setThirst(int amount) {
        thirst = amount;
    }

    public void setMaxThirst(int amount) {
        this.maxThirst = amount;
    }

    public int getThirst() {
        return thirst;
    }

    public int getMaxThirst() {
        return maxThirst;
    }

    public static class Capability {

        @CapabilityInject(ThirstStorageCapability.class)
        public static net.minecraftforge.common.capabilities.Capability<ThirstStorageCapability> THIRST_CAP;

        public static LazyOptional<ThirstStorageCapability> getWorldPressure(final World world) {

            return world.getCapability(THIRST_CAP, null);
        }

        public static net.minecraftforge.common.capabilities.Capability<ThirstStorageCapability> get() {
            return THIRST_CAP;
        }

    }
}
