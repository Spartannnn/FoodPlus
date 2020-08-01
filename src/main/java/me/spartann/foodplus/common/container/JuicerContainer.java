package me.spartann.foodplus.common.container;

import me.spartann.foodplus.common.registries.ModContainers;
import me.spartann.foodplus.common.tile.JuicerTile;
import me.spartann.foodplus.common.util.helper.ContainerHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class JuicerContainer extends FPContainer<JuicerTile> {

    public JuicerContainer(int id, PlayerInventory playerInventory, PacketBuffer data) {
        this(id, playerInventory, ContainerHelper.getTileEntity(JuicerTile.class, playerInventory, data));
    }

    public JuicerContainer(int id, PlayerInventory playerInventory, JuicerTile tile) {
        super(ModContainers.JUICER_CONTAINER.get(), id, playerInventory, tile);

        this.addSlot(new SlotItemHandler(tile.inventory, 0, 27, 21));
        this.addSlot(new SlotItemHandler(tile.inventory, 1, 124, 21));

        this.playerInventory(8, 69);
        this.playerHotbar(8, 127);

    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(final PlayerEntity player, final int index) {
        ItemStack returnStack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            final ItemStack slotStack = slot.getStack();
            returnStack = slotStack.copy();

            final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
            if (index < containerSlots) {
                if (!mergeItemStack(slotStack, containerSlots, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!mergeItemStack(slotStack, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }
            if (slotStack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (slotStack.getCount() == returnStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return returnStack;
    }
}
