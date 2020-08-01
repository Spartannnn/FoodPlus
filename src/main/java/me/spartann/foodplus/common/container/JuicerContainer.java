package me.spartann.foodplus.common.container;

import me.spartann.foodplus.common.registries.ModContainers;
import me.spartann.foodplus.common.tile.JuicerTile;
import me.spartann.foodplus.common.util.helper.ContainerHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.SlotItemHandler;

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
}
