package me.spartann.foodplus.common.container;

import me.spartann.foodplus.common.inventory.slots.UniqueItemSlot;
import me.spartann.foodplus.common.registries.ModContainers;
import me.spartann.foodplus.common.registries.ModItems;
import me.spartann.foodplus.common.tile.JuicerBlockTile;
import me.spartann.foodplus.common.util.helper.ContainerHelper;
import me.spartann.foodplus.common.util.helper.ItemStackHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;

public class JuicerContainer extends BasicFPContainer<JuicerBlockTile> {

    public JuicerContainer(int id, PlayerInventory playerInventory, PacketBuffer data) {
        this(id, playerInventory, ContainerHelper.getTileEntity(JuicerBlockTile.class, playerInventory, data));
    }

    public JuicerContainer(int id, PlayerInventory playerInventory, JuicerBlockTile tile) {
        super(ModContainers.JUICER_CONTAINER.get(), id, tile);

        this.addSlot(new UniqueItemSlot(itemHandler, 0, 27, 21, ModItems.getFruits()));
        this.addSlot(new UniqueItemSlot(itemHandler, 1, 124, 21, NonNullList.withSize(1, ModItems.JUICE.get())));

        this.playerInventory(playerInventory, 8, 70);
        this.playerHotbar(playerInventory, 8, 127);

    }


    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack clicked = slot.getStack();
            itemstack = clicked.copy();
            if (index == 0 || index == 1) {
                if (!this.mergeItemStack(clicked, 2, this.inventorySlots.size(), true))
                    return ItemStack.EMPTY;
                slot.onSlotChange(clicked, itemstack);
            } else {
                if (ItemStackHelper.isFruit(clicked)) {
                    if (!this.mergeItemStack(clicked, 0, 1, false))
                        return ItemStack.EMPTY;
                }
            }
        }
        itemstack = ItemStack.EMPTY;
        return itemstack;
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {
        if (slotId == 1) {
            Slot slot = this.inventorySlots.get(slotId);
            if (slot.getHasStack() && clickTypeIn == ClickType.QUICK_MOVE) {
                return quickMove(slotId, 2);
            }
        }

        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }
}
