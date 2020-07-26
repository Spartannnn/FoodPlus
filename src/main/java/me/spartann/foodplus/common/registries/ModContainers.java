package me.spartann.foodplus.common.registries;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.container.JuicerContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, FoodPlusMod.MOD_ID);

    public static final RegistryObject<ContainerType<JuicerContainer>> JUICER_CONTAINER = CONTAINERS.register("juicer_container",
            () -> IForgeContainerType.create(JuicerContainer::new));

}
