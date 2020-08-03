package com.spartann.foodplus.common.container;

import com.spartann.foodplus.common.registries.ModContainers;
import com.spartann.foodplus.common.tile.TileBurgerMaker;
import com.spartann.foodplus.common.util.helper.ContainerHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBurgerMaker extends ContainerFoodPlus<TileBurgerMaker> {

    public ContainerBurgerMaker(int id, PlayerInventory playerInventory, PacketBuffer data) {
        this(id, playerInventory, ContainerHelper.getTileEntity(TileBurgerMaker.class, playerInventory, data));
    }

    public ContainerBurgerMaker(int id, PlayerInventory playerInventory, TileBurgerMaker tileBurgerMaker) {
        super(ModContainers.BURGER_MAKER.get(), id, playerInventory, tileBurgerMaker);

        this.addSlot(new SlotItemHandler(tileBurgerMaker.inventory, 0, 11, 12));
        this.addSlot(new SlotItemHandler(tileBurgerMaker.inventory, 1, 39, 12));
        this.addSlot(new SlotItemHandler(tileBurgerMaker.inventory, 2, 11, 43));
        this.addSlot(new SlotItemHandler(tileBurgerMaker.inventory, 3, 39, 43));
        this.addSlot(new SlotItemHandler(tileBurgerMaker.inventory, 4, 121, 29));

        this.playerInventory(8, 76);
        this.playerHotbar(8, 134);

    }


}
