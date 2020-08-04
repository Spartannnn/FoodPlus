package com.spartann.foodplus.common.event;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.registries.ModItems;
import com.spartann.foodplus.common.util.helper.ItemStackHelper;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = FoodPlusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DropsEvent {

    private static final Random RANDOM = new Random();

    public static void onEntityDrop(LivingDropsEvent event) {
        if(event.getEntity() instanceof PigEntity) {
            System.out.println("DEBUG");
            if(RANDOM.nextFloat() <= 0.25F)
                ItemStackHelper.dropItems(event.getEntity().getEntityWorld(), event.getEntity().getPosition(), new ItemStack(ModItems.HAM.get(), RANDOM.nextInt(3) + 1));
            if(RANDOM.nextFloat() <= 0.20F)
                ItemStackHelper.dropItems(event.getEntity().getEntityWorld(), event.getEntity().getPosition(), new ItemStack(ModItems.SALAMI.get(), RANDOM.nextInt(3) + 1));
        } else if(event.getEntity() instanceof CowEntity) {
            if(RANDOM.nextFloat() <= 0.30)
                ItemStackHelper.dropItems(event.getEntity().getEntityWorld(), event.getEntity().getPosition(), new ItemStack(ModItems.SASUAGE.get(), RANDOM.nextInt(3) + 1));
            if(RANDOM.nextFloat() <= 0.10)
                ItemStackHelper.dropItems(event.getEntity().getEntityWorld(), event.getEntity().getPosition(), new ItemStack(ModItems.SALAMI.get(), RANDOM.nextInt(3) + 1));
        }
    }

}
