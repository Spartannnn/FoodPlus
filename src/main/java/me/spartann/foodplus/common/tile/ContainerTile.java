package me.spartann.foodplus.common.tile;

import me.spartann.foodplus.common.util.FunctionalIntReferenceHolder;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class ContainerTile extends TileEntity implements INamedContainerProvider {

    private static final String INVENTORY_TAG = "inventory";

    public final ItemStackHandler inventory;
    protected final LazyOptional<ItemStackHandler> inventoryCapabilityExternal;
    public List<FunctionalIntReferenceHolder> intReferenceHolders;

    public ContainerTile(TileEntityType<?> tileEntityTypeIn, int inventorySlots) {
        super(tileEntityTypeIn);
        this.inventory = new ItemStackHandler(inventorySlots) {
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                ContainerTile.this.markDirty();
            }
        };
        this.inventoryCapabilityExternal = LazyOptional.of(() -> this.inventory);
        this.intReferenceHolders = this.getIntReferenceHolder();
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventoryCapabilityExternal.cast();
        }
        return super.getCapability(cap, side);
    }

    public abstract void readData(CompoundNBT nbt);

    public abstract CompoundNBT writeData(CompoundNBT nbt);

    public abstract List<FunctionalIntReferenceHolder> getIntReferenceHolder();

    public NonNullList<ItemStack> getInventoryItems() {
        NonNullList<ItemStack> list = NonNullList.withSize(this.inventory.getSlots(), ItemStack.EMPTY);
        for(int i = 0; i < this.inventory.getSlots(); i++)
            list.add(this.inventory.getStackInSlot(i));
        return list;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.inventory.deserializeNBT(compound);
        this.readData(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.put(INVENTORY_TAG, this.inventory.serializeNBT());
        this.writeData(compound);
        return compound;
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void remove() {
        super.remove();
        inventoryCapabilityExternal.invalidate();
    }

}
