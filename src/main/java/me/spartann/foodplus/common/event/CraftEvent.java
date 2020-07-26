package me.spartann.foodplus.common.event;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.items.BaseFoodItem;
import me.spartann.foodplus.common.items.juicer.JuicerPlateItem;
import me.spartann.foodplus.common.items.juicer.juice.JuiceBottleFlavour;
import me.spartann.foodplus.common.items.juicer.juice.JuiceBottleFullness;
import me.spartann.foodplus.common.items.juicer.juice.JuiceBottleItem;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = FoodPlusMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CraftEvent {

    @SubscribeEvent
    public static void onCraft(PlayerEvent.ItemCraftedEvent event) {
        ItemStack crafted = event.getCrafting();
        if (crafted.getItem() instanceof JuiceBottleItem) {
            if (event.getInventory() instanceof CraftingInventory) {
                CraftingInventory craftingInventory = (CraftingInventory) event.getInventory();
                JuiceBottleFlavour flavour = null;
                for (int i = 0; i < craftingInventory.getSizeInventory(); i++) {
                    ItemStack stack = event.getInventory().getStackInSlot(i);
                    if (!stack.isEmpty() && stack.getItem() instanceof JuicerPlateItem) {
                        stack.getItem().damageItem(stack, 1, event.getEntityLiving(), (livingEntity -> {
                        }));
                    }
                    if (!stack.isEmpty() && stack.getItem() instanceof BaseFoodItem) {
                        BaseFoodItem baseFoodItem = (BaseFoodItem) stack.getItem();
                        if (baseFoodItem.isFood() && baseFoodItem.getFood().equals(BaseFoodItem.FRUIT_FOOD)) {
                            String name = Objects.requireNonNull(baseFoodItem.getRegistryName()).getPath();
                            name = name.replace("_fruit", "");
                            flavour = JuiceBottleFlavour.byName(name);
                        }
                    }
                }
                if (flavour != null) {
                    JuiceBottleItem.setFlavour(crafted, flavour);
                    JuiceBottleItem.setFullness(crafted, JuiceBottleFullness.FULL);
                } else
                    throw new IllegalStateException("Unable to find bottle flavour");
            }

        }
    }


}
