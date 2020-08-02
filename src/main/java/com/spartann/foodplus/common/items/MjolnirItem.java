package com.spartann.foodplus.common.items;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.client.particles.data.ScaledColoredParticleData;
import com.spartann.foodplus.common.io.ParticleMessage;
import com.spartann.foodplus.common.registries.ModParticleTypes;
import com.spartann.foodplus.common.util.helper.ModMathHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MjolnirItem extends Item {

    public MjolnirItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.areEyesInFluid(FluidTags.WATER)) return ActionResult.resultPass(playerIn.getHeldItem(handIn));
        Vec3d look = playerIn.getLookVec();
        Vec3d eyes = playerIn.getPositionVec().add(0, playerIn.getEyeHeight(), 0);
        Vec3d startpos = handIn == Hand.MAIN_HAND ? eyes.add(ModMathHelper.rotateY(look.mul(0.3, 0.3, 0.3), -20))
                : eyes.add(ModMathHelper.rotateY(look.mul(0.3, 0.3, 0.3), 20));


        if (worldIn.isRemote) {
            worldIn.addParticle(new ScaledColoredParticleData(ModParticleTypes.LIGHTNING, true, 0xAFC6FF, 1), startpos.x, startpos.y, startpos.z, look.x, look.y, look.z);
        } else {
            ParticleMessage particleMessage = new ParticleMessage(startpos, look);
            FoodPlusMod.CHANNEL_INSTANCE.sendToServer(particleMessage);
        }
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }
}
