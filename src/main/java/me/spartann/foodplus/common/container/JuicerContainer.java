package me.spartann.foodplus.common.container;

import me.spartann.foodplus.common.inventory.slots.UniqueItemSlot;
import me.spartann.foodplus.common.items.juicer.juice.JuiceBottleItem;
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
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack clicked = slot.getStack();
            itemstack = clicked.copy();
            if (index == 0 || index == 1) {
                if (!this.mergeItemStack(clicked, 2, this.inventorySlots.size(), true))
                    return ItemStack.EMPTY;
            } else {
                if (ItemStackHelper.isFruit(clicked)) {
                    if (!this.mergeItemStack(clicked, 0, 1, false))
                        return ItemStack.EMPTY;
                }
            }
        }
        return itemstack;
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {
        if (slotId == 0 || slotId == 1) {
            Slot slot = this.inventorySlots.get(slotId);
            ItemStack held = player.inventory.getItemStack();
            if (slotId == 0) {
                if (slot.getHasStack()) {
                    ItemStack slotStack = slot.getStack();
                    if (held.isEmpty()) {
                        ItemStack toEx = this.itemHandler.extractItem(0, slotStack.getCount(), false);
                        player.inventory.setItemStack(toEx);
                        this.itemHandler.setStackInSlot(0, ItemStack.EMPTY);
                        return ItemStack.EMPTY;
                    } else {
                        if (ItemStackHelper.isFruit(held)) {
                            if (areItemsAndTagsEqual(slotStack, held)) {
                                ItemStack res = this.itemHandler.insertItem(0, held, false);
                                player.inventory.setItemStack(res);
                                return res;
                            }
                        }
                    }
                } else {
                    if (!held.isEmpty() && ItemStackHelper.isFruit(held)) {
                        this.itemHandler.setStackInSlot(0, held);
                        player.inventory.setItemStack(ItemStack.EMPTY);
                        return held;
                    }
                }
            } else {
                if (slot.getHasStack()) {
                    ItemStack slotStack = slot.getStack();
                    if (held.isEmpty()) {
                        ItemStack toEx = this.itemHandler.extractItem(1, slotStack.getCount(), false);
                        player.inventory.setItemStack(toEx);
                        this.itemHandler.setStackInSlot(1, ItemStack.EMPTY);
                        return ItemStack.EMPTY;
                    } else {
                        if (held.getItem() instanceof JuiceBottleItem) {
                            if (JuiceBottleItem.isEqualJuice(slotStack, held)) {
                                ItemStack toEx = this.itemHandler.extractItem(1, slotStack.getCount(), false);
                                if (toEx.isEmpty())
                                    return ItemStack.EMPTY;
                                ItemStack stack = held.copy();
                                int amount = stack.getCount() + toEx.getCount();
                                if (amount > 64) {
                                    stack.setCount(64);
                                    toEx.setCount(amount - 64);
                                    this.itemHandler.setStackInSlot(1, toEx);
                                    player.inventory.setItemStack(stack);
                                    return toEx;
                                } else {
                                    stack.setCount(amount);
                                    player.inventory.setItemStack(stack);
                                    return ItemStack.EMPTY;
                                }
                            }
                        }
                    }
                }
            }
        }
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }
}
