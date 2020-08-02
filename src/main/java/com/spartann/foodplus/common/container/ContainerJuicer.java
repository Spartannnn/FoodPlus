package com.spartann.foodplus.common.container;

import com.spartann.foodplus.common.registries.ModContainers;
import com.spartann.foodplus.common.tile.TileJuicer;
import com.spartann.foodplus.common.util.helper.ContainerHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerJuicer extends ContainerFoodPlus<TileJuicer> {

    public ContainerJuicer(int id, PlayerInventory playerInventory, PacketBuffer data) {
        this(id, playerInventory, ContainerHelper.getTileEntity(TileJuicer.class, playerInventory, data));
    }

    public ContainerJuicer(int id, PlayerInventory playerInventory, TileJuicer tile) {
        super(ModContainers.JUICER_CONTAINER.get(), id, playerInventory, tile);

        this.addSlot(new SlotItemHandler(tile.inventory, 0, 27, 21));
        this.addSlot(new SlotItemHandler(tile.inventory, 1, 124, 21));

        this.playerInventory(8, 69);
        this.playerHotbar(8, 127);

    }
}
