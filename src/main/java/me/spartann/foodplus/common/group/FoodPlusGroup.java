package me.spartann.foodplus.common.group;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class FoodPlusGroup extends ItemGroup {

    public static final FoodPlusGroup INSTANCE = new FoodPlusGroup(ItemGroup.GROUPS.length, "foodplus_tab");

    public FoodPlusGroup(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.STONE);
    }
}
