package me.spartann.foodplus.common.container;


import me.spartann.foodplus.common.tile.BasicItemHolderTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;

public abstract class BasicFPContainer<T extends BasicItemHolderTile & INamedContainerProvider> extends Container {

    protected T tile;
    protected final IWorldPosCallable canInteract;
    protected IItemHandlerModifiable itemHandler;

    public BasicFPContainer(@Nullable ContainerType<?> type, int id, T tile) {
        super(type, id);
        this.tile = tile;
        this.itemHandler = tile.getItemHandler();
        this.canInteract = IWorldPosCallable.of(tile.getWorld(), tile.getPos());
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

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteract, playerIn, tile.getBlockState().getBlock());
    }

    public T getTile() {
        return tile;
    }

    protected ItemStack quickMove(int slotId, int minIndex) {
        if (slotId < 0 || slotId >= this.inventorySlots.size())
            throw new IndexOutOfBoundsException("SlotID not found: " + slotId);
        Slot slot = this.inventorySlots.get(slotId);
        if (!slot.getHasStack())
            return ItemStack.EMPTY;
        ItemStack stack = slot.getStack();
        ItemStack stack1 = this.itemHandler.extractItem(slotId, stack.getCount(), false);
        if (this.mergeItemStack(stack1, minIndex, this.inventorySlots.size(), true))
            return ItemStack.EMPTY;
        return ItemStack.EMPTY;
    }

}
