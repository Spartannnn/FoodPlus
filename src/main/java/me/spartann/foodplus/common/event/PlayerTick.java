package me.spartann.foodplus.common.event;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.food.IFoodDurability;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = FoodPlusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerTick {

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
        if(event.phase == TickEvent.Phase.END) {
            PlayerInventory playerInventory = event.player.inventory;
            if(containsIFoodDurabilityItem(playerInventory)) {
                List<IFoodDurability> foodDurabilityItems = getIFoodDurabilityItems(playerInventory);
                for(IFoodDurability iFoodDurability : foodDurabilityItems) {
                    
                }

            }
        }
    }

    private static List<IFoodDurability> getIFoodDurabilityItems(PlayerInventory playerInventory) {
        List<IFoodDurability> list = new ArrayList<>();
        for(ItemStack item : playerInventory.mainInventory)
            if(IFoodDurability.hasFoodDurability(item.getItem()))
                list.add((IFoodDurability) item.getItem());
        return list;
    }

    private static boolean containsIFoodDurabilityItem(PlayerInventory playerInventory) {
        for(ItemStack item : playerInventory.mainInventory) {
            if(IFoodDurability.hasFoodDurability(item.getItem()))
                return true;
        }
        return false;
    }

}
