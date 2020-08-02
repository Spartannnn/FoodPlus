package com.spartann.foodplus.common.container;

import com.spartann.foodplus.common.registries.ModContainers;
import com.spartann.foodplus.common.tile.BeerBrewerTile;
import com.spartann.foodplus.common.util.helper.ContainerHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.SlotItemHandler;

public class BeerBrewerContainer extends FPContainer<BeerBrewerTile> {

    public BeerBrewerContainer(int id, PlayerInventory playerInventory, PacketBuffer data) {
        this(id, playerInventory, ContainerHelper.getTileEntity(BeerBrewerTile.class, playerInventory, data));
    }

    public BeerBrewerContainer(int id, PlayerInventory playerInventory, BeerBrewerTile tile) {
        super(ModContainers.BEER_BREWER.get(), id, playerInventory, tile);

        this.addSlot(new SlotItemHandler(tile.inventory, 0, 18, 34));
        this.addSlot(new SlotItemHandler(tile.inventory, 1, 36, 34));
        this.addSlot(new SlotItemHandler(tile.inventory, 2, 27, 52));
        this.addSlot(new SlotItemHandler(tile.inventory, 3, 128, 39)); //<- Output

        this.playerInventory(8, 103);
        this.playerHotbar(8, 161);

    }
}
