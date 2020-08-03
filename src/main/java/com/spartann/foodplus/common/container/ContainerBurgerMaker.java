package com.spartann.foodplus.common.container;

import com.spartann.foodplus.common.inventory.slot.SlotCraftingResult;
import com.spartann.foodplus.common.recipe.IShapedBurgerRecipe;
import com.spartann.foodplus.common.registries.ModContainers;
import com.spartann.foodplus.common.registries.ModRecipeTypes;
import com.spartann.foodplus.common.tile.TileBurgerMaker;
import com.spartann.foodplus.common.util.helper.ContainerHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBurgerMaker extends FoodPlusCraftingContainer<IShapedBurgerRecipe, TileBurgerMaker> {

    public ContainerBurgerMaker(int id, PlayerInventory playerInventory, PacketBuffer data) {
        this(id, playerInventory, ContainerHelper.getTileEntity(TileBurgerMaker.class, playerInventory, data));
    }

    public ContainerBurgerMaker(int id, PlayerInventory playerInventory, TileBurgerMaker tileBurgerMaker) {
        super(ModContainers.BURGER_MAKER.get(), id, playerInventory, tileBurgerMaker, 1);

        this.addSlot(new SlotCraftingResult(resultHandler, this.player, this, 0, 121, 28));

        this.addSlot(new SlotItemHandler(tile.inventory, 0, 23, 19));
        this.addSlot(new SlotItemHandler(tile.inventory, 1, 41, 19));
        this.addSlot(new SlotItemHandler(tile.inventory, 2, 23, 37));
        this.addSlot(new SlotItemHandler(tile.inventory, 3, 41, 37));

        this.playerInventory(8, 76);
        this.playerHotbar(8, 134);

    }


    @Override
    public IRecipeType<IShapedBurgerRecipe> recipeType() {
        return ModRecipeTypes.SHAPED_BURGER_RECIPE_TYPE;
    }
}
