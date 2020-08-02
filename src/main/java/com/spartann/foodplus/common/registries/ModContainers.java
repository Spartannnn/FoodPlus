package com.spartann.foodplus.common.registries;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.container.BeerBrewerContainer;
import com.spartann.foodplus.common.container.JuicerContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, FoodPlusMod.MOD_ID);

    public static final RegistryObject<ContainerType<JuicerContainer>> JUICER_CONTAINER = register("juicer_container", JuicerContainer::new);
    public static final RegistryObject<ContainerType<BeerBrewerContainer>> BEER_BREWER = register("beer_brewer_container", BeerBrewerContainer::new);

    private static <T extends Container> RegistryObject<ContainerType<T>> register(String name, IContainerFactory<T> factory) {
        return CONTAINERS.register(name, () -> IForgeContainerType.create(factory));
    }

}
