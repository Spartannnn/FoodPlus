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

    private final LazyOptional<ThirstStorage> thirst;

    public PlayerThirstStorageProvider() {
        this.thirst = LazyOptional.of(() -> new ThirstStorage());
    }

    @SuppressWarnings("resource")
    public static LazyOptional<ThirstStorage> getClientCapability() {
        return Optional.of(Minecraft.getInstance().player).map(p -> p.getCapability(ThirstStorage.Capability.get())).orElse(null);
    }

    public static  LazyOptional<ThirstStorage> getPlayerCapability(PlayerEntity player) {
        return player.getCapability(ThirstStorage.Capability.get());
    }

    public static int getEnergy(PlayerEntity player) {
        return player.getCapability(ThirstStorage.Capability.get()).map(s -> s.getThirst()).orElse(0);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        return ThirstStorage.Capability.get().orEmpty(cap, this.thirst);
    }
}
