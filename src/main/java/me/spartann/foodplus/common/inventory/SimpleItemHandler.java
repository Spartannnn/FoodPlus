package me.spartann.foodplus.common.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class SimpleItemHandler extends ItemStackHandler {

    public SimpleItemHandler(int size, ItemStack... stacks) {
        super(size);

        for(int i = 0; i < stacks.length; i++) {
            this.stacks.set(i, stacks[i]);
        }
    }

    public void clear() {
        for(int i = 0; i < this.getSlots(); i++) {
            this.stacks.set(i, ItemStack.EMPTY);
            this.onContentsChanged(i);
        }
    }

    public boolean isEmpty() {
        for(ItemStack stack : this.stacks) {
            if(stack.isEmpty() || stack.getItem() == Items.AIR) return true;
        }
        return false;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        super.setStackInSlot(slot, stack);
    }

    public ItemStack incrStackSize(int index, int count, @Nullable ItemStack defaultStack) {
        ItemStack stack = getStackInSlot(index);
        if(stack.isEmpty() && defaultStack != null) {
            ItemStack res = this.insertItem(index, defaultStack, false);
            this.onContentsChanged(index);
            return res;
        }
        stack.grow(count);
        this.onContentsChanged(index);
        return stack;
    }

    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = getStackInSlot(index);
        if(stack.isEmpty())
            return ItemStack.EMPTY;
        stack.shrink(count);
        this.onContentsChanged(index);
        return stack;
    }

    public void removeStackFromSlot(int index) {
        this.stacks.set(index, ItemStack.EMPTY);
        this.onContentsChanged(index);
    }

    public NonNullList<ItemStack> toNonNullList() {
        NonNullList<ItemStack> out = NonNullList.create();
        for(ItemStack stack : this.stacks) out.add(stack);
        return out;
    }

    public void setNonNullList(NonNullList<ItemStack> inv) {
        if(inv.size() == 0) return;
        if(inv.size() != this.getSlots()) throw new IndexOutOfBoundsException("NonNullList not the same size as ItemHandler!");
        for(int i = 0; i < inv.size(); i++) {
            this.stacks.set(i, inv.get(i));
        }
    }

    @Override
    public String toString() {
        return this.stacks.toString();
    }

}
