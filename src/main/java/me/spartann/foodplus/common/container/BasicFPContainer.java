package me.spartann.foodplus.common.container;


import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;

import javax.annotation.Nullable;

public abstract class BasicFPContainer extends Container {

    protected BasicFPContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }

    protected void playerInventory(PlayerInventory playerInventory, int startX, int startY) {
        for (int k = 0; k < 3; ++k) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, startX + i1 * 18, startY + k * 18));
            }
        }
    }

    protected void playerHotbar(PlayerInventory playerInventory, int startX, int y) {
        for (int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, startX + l * 18, y));
        }
    }

}
