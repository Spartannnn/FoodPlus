package me.spartann.foodplus.common.world.data;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.util.Optional;

public class PlayerThirstStorageProvider implements ICapabilityProvider {

    private final LazyOptional<ThirstStorageCapability> thirst;

    public PlayerThirstStorageProvider() {
        this.thirst = LazyOptional.of(() -> new ThirstStorageCapability());
    }

    @SuppressWarnings("resource")
    public static LazyOptional<ThirstStorageCapability> getClientCapability() {
        return Optional.of(Minecraft.getInstance().player).map(p -> p.getCapability(ThirstStorageCapability.Capability.get())).orElse(null);
    }

    public static  LazyOptional<ThirstStorageCapability> getPlayerCapability(PlayerEntity player) {
        return player.getCapability(ThirstStorageCapability.Capability.get());
    }

    public static int getThirstStorage(PlayerEntity player) {
        return player.getCapability(ThirstStorageCapability.Capability.get()).map(s -> s.getThirst()).orElse(0);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        return ThirstStorageCapability.Capability.get().orEmpty(cap, this.thirst);
    }
}
