package com.spartann.foodplus.common.inventory.slot;

import net.minecraft.inventory.container.Container;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotCrafting extends SlotItemHandler {

    private final Container tooltable;

    public SlotCrafting(IItemHandler itemHandler, Container tooltable, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.tooltable = tooltable;
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        this.tooltable.onCraftMatrixChanged(null);
    }

}
