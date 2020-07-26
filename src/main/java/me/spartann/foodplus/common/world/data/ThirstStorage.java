package me.spartann.foodplus.common.world.data;

import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;

public class ThirstStorage {

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

        @CapabilityInject(ThirstStorage.class)
        public static net.minecraftforge.common.capabilities.Capability<ThirstStorage> THIRST_CAP;

        public static LazyOptional<ThirstStorage> getWorldPressure(final World world) {
            return world.getCapability(THIRST_CAP, null);
        }

        public static net.minecraftforge.common.capabilities.Capability<ThirstStorage> get() {
            return THIRST_CAP;
        }

    }
}
