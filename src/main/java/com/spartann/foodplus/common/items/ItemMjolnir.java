package com.spartann.foodplus.common.items;

import com.google.common.collect.Lists;
import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.client.particles.data.ScaledColoredParticleData;
import com.spartann.foodplus.common.io.ParticleMessage;
import com.spartann.foodplus.common.registries.ModParticleTypes;
import com.spartann.foodplus.common.util.TextComponentUtil;
import com.spartann.foodplus.common.util.helper.ModMathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ItemMjolnir extends ItemFoodPlus {

    public ItemMjolnir(Properties properties) {
        super(properties, () -> Lists.newArrayList(TextComponentUtil.translationTextComponent("mjolnir.desc")));
    }

    private void performAttack(World world, PlayerEntity player) {
        Vec3d look = player.getLookVec();
        Vec3d eyes = player.getEyePosition(0);
        List<LivingEntity> entities = getEntities(eyes, look, look.distanceTo(eyes), world, player);
        for (LivingEntity entity : entities) {
            entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(player, null), 15.0f);
        }
    }

    protected List<LivingEntity> getEntities(Vec3d pos, Vec3d direction, double range, World world, PlayerEntity player) {
        List<LivingEntity> entities = Lists.newArrayList();
        Vec3d vec1 = pos.add(direction.scale(range / 5));
        AxisAlignedBB aabb1 = new AxisAlignedBB(vec1.add(range / 5, range / 5, range / 5), vec1.subtract(range / 5, range / 5, range / 5));
        Vec3d vec2 = pos.add(direction.scale(3 * range / 5));
        AxisAlignedBB aabb2 = new AxisAlignedBB(vec2.add(2 * range / 5, 2 * range / 5, 2 * range / 5), vec2.subtract(2 * range / 5, 2 * range / 5, 2 * range / 5));

        Predicate<Entity> filter = entity -> entity != null && entity.canBeCollidedWith() && entity.isAlive() && !entity.isSpectator() && entity instanceof LivingEntity && entity.getUniqueID() != player.getUniqueID();

        entities.addAll(world.getEntitiesInAABBexcluding(null, aabb1, filter).stream().map(e -> (LivingEntity) e).collect(Collectors.toList()));
        entities.addAll(world.getEntitiesInAABBexcluding(null, aabb2, filter).stream().map(e -> (LivingEntity) e).collect(Collectors.toList()));

        return entities;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.areEyesInFluid(FluidTags.WATER)) return ActionResult.resultPass(playerIn.getHeldItem(handIn));
        Vec3d look = playerIn.getLookVec();
        Vec3d eyes = playerIn.getPositionVec().add(0, playerIn.getEyeHeight(), 0);
        Vec3d startpos = handIn == Hand.MAIN_HAND ? eyes.add(ModMathHelper.rotateY(look.mul(0.3, 0.3, 0.3), -20))
                : eyes.add(ModMathHelper.rotateY(look.mul(0.3, 0.3, 0.3), 20));

        this.performAttack(worldIn, playerIn);
        if (worldIn.isRemote) {
            worldIn.addParticle(new ScaledColoredParticleData(ModParticleTypes.LIGHTNING, true, 0xAFC6FF, 1), startpos.x, startpos.y, startpos.z, look.x, look.y, look.z);
        } else {
            ParticleMessage particleMessage = new ParticleMessage(startpos, look);
            FoodPlusMod.CHANNEL_INSTANCE.sendToServer(particleMessage);
        }
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }
}
