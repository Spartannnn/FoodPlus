package com.spartann.foodplus.common.entities;

import com.spartann.foodplus.common.registries.ModEntityTypes;
import com.spartann.foodplus.common.registries.ModItems;
import com.spartann.foodplus.common.util.TextComponentUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BananaProjectileEntity extends ProjectileItemEntity {

    public BananaProjectileEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public BananaProjectileEntity(World world, LivingEntity livingEntity) {
        super(ModEntityTypes.BANANA_PROJECTILE.get(), livingEntity, world);
    }

    public BananaProjectileEntity(World world, double x, double y, double z) {
        super(ModEntityTypes.BANANA_PROJECTILE.get(), x, y, z, world);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BANANA_FRUIT.get();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult) result;
            Entity entity = entityRayTraceResult.getEntity();
            if (entity instanceof LivingEntity) {
                if (entity instanceof ServerPlayerEntity) {
                    entity.sendMessage(TextComponentUtil.stringTextComponent("Oh noooo. You got hittet by a banana. You monkey", TextFormatting.YELLOW));
                    return;
                }
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.attackEntityFrom(DamageSource.GENERIC, 1.0F);
            }
        }
    }
}
