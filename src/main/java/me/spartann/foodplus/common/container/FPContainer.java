package me.spartann.foodplus.common.container;

import me.spartann.foodplus.common.tile.ContainerTile;
import me.spartann.foodplus.common.util.FunctionalIntReferenceHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.IWorldPosCallable;

import javax.annotation.Nullable;

public class FPContainer<TILE extends ContainerTile> extends Container {

    public final TILE tile;
    protected IWorldPosCallable canInteract;
    protected PlayerInventory playerInventory;

    public FPContainer(@Nullable ContainerType<?> type, int id, PlayerInventory playerInventory, TILE tile) {
        super(type, id);
        this.tile = tile;
        this.playerInventory = playerInventory;
        this.canInteract = IWorldPosCallable.of(tile.getWorld(), tile.getPos());

        if(tile.intReferenceHolders != null && !tile.intReferenceHolders.isEmpty()) {
            for(FunctionalIntReferenceHolder intReferenceHolder : tile.intReferenceHolders)
                this.trackInt(intReferenceHolder);
        }

    }

    protected void playerInventory(int startX, int startY) {
        for (int k = 0; k < 3; ++k) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, startX + i1 * 18, startY + k * 18));
            }
        }
    }


    protected void playerHotbar(int startX, int y) {
        for (int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, startX + l * 18, y));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteract, playerIn, tile.getBlockState().getBlock());
    }
}
