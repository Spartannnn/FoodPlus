package com.spartann.foodplus.common.registries;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.entities.BananaProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, FoodPlusMod.MOD_ID);

    public static final RegistryObject<EntityType<BananaProjectileEntity>> BANANA_PROJECTILE = registerEntity("banana_projectile", BananaProjectileEntity::new);

    private static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, EntityType.IFactory<T> factory) {
        return ENTITIES.register(name, () -> EntityType.Builder.create(factory, EntityClassification.MISC).size(0.25F, 0.25F).build(FoodPlusMod.MOD_ID + ":" + name));
    }

}
