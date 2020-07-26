package me.spartann.foodplus.common.container;

import me.spartann.foodplus.common.inventory.slots.UniqueItemSlot;
import me.spartann.foodplus.common.registries.ModBlocks;
import me.spartann.foodplus.common.registries.ModContainers;
import me.spartann.foodplus.common.registries.ModItems;
import me.spartann.foodplus.common.tile.JuicerBlockTile;
import me.spartann.foodplus.common.util.helper.ContainerHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.NonNullList;

public class JuicerContainer extends BasicFPContainer {

    private final IWorldPosCallable canInteract;
    private JuicerBlockTile tile;

    public JuicerContainer(int id, PlayerInventory playerInventory, PacketBuffer data) {
        this(id, playerInventory, ContainerHelper.getTileEntity(JuicerBlockTile.class, playerInventory, data));
    }

    public JuicerContainer(int id, PlayerInventory playerInventory, JuicerBlockTile tile) {
        super(ModContainers.JUICER_CONTAINER.get(), id);
        this.canInteract = IWorldPosCallable.of(tile.getWorld(), tile.getPos());
        this.tile = tile;

        this.addSlot(new UniqueItemSlot(tile.getItemHandler(), 0, 27, 21, ModItems.getFruits()));
        this.addSlot(new UniqueItemSlot(tile.getItemHandler(), 1, 114, 22, NonNullList.withSize(1, ModItems.JUICE.get())));

        this.playerInventory(playerInventory, 8, 70);
        this.playerHotbar(playerInventory, 8, 127);

    }

    public JuicerBlockTile getTile() {
        return tile;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteract, playerIn, ModBlocks.JUICER.get());
    }
}
