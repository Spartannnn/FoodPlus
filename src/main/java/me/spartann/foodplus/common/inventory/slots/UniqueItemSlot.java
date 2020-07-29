package me.spartann.foodplus.common.inventory.slots;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class UniqueItemSlot extends SlotItemHandler {

    private final NonNullList<Item> uniqueItems;

    public UniqueItemSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, NonNullList<Item> uniqueItems) {
        super(itemHandler, index, xPosition, yPosition);
        this.uniqueItems = uniqueItems;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        for(Item item : uniqueItems)
            if(item.getDefaultInstance().isItemEqual(stack))
                return true;
        return false;
    }
}
