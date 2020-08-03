package com.spartann.foodplus.common.container;

import com.spartann.foodplus.common.inventory.slot.SlotCraftingResult;
import com.spartann.foodplus.common.recipe.ISandwichMakerRecipe;
import com.spartann.foodplus.common.registries.ModContainers;
import com.spartann.foodplus.common.registries.ModRecipeTypes;
import com.spartann.foodplus.common.tile.TileSandwichMaker;
import com.spartann.foodplus.common.util.helper.ContainerHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerSandwichMaker extends FoodPlusCraftingContainer<ISandwichMakerRecipe, TileSandwichMaker> {

    public ContainerSandwichMaker(int id, PlayerInventory playerInventory, PacketBuffer data) {
        this(id, playerInventory, ContainerHelper.getTileEntity(TileSandwichMaker.class, playerInventory, data));
    }

    public ContainerSandwichMaker(int id, PlayerInventory playerInventory, TileSandwichMaker tileSandwichMaker) {
        super(ModContainers.SANDWICH_MAKER.get(), id, playerInventory, tileSandwichMaker, 1);

        this.addSlot(new SlotCraftingResult(this.resultHandler, playerInventory.player, this, 0, 129, 128));

        this.addSlot(new SlotItemHandler(tileSandwichMaker.inventory, 0, 23, 19));
        this.addSlot(new SlotItemHandler(tileSandwichMaker.inventory, 1, 41, 19));
        this.addSlot(new SlotItemHandler(tileSandwichMaker.inventory, 2, 32, 37));

        this.playerInventory(8, 76);
        this.playerHotbar(8, 134);

    }

    @Override
    public IRecipeType<ISandwichMakerRecipe> recipeType() {
        return ModRecipeTypes.SHAPED_SANDWICH_RECIPE_TYPE;
    }
}
