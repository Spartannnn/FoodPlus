package me.spartann.foodplus.common.container;

import me.spartann.foodplus.common.inventory.slots.RecipeInputSlot;
import me.spartann.foodplus.common.inventory.slots.UniqueItemSlot;
import me.spartann.foodplus.common.recipe.beerbrewer.BeerBrewerRecipe;
import me.spartann.foodplus.common.registries.ModContainers;
import me.spartann.foodplus.common.registries.ModRecipeTypes;
import me.spartann.foodplus.common.tile.BeerBrewerTile;
import me.spartann.foodplus.common.util.MethodsUtil;
import me.spartann.foodplus.common.util.helper.ContainerHelper;
import me.spartann.foodplus.common.util.helper.CraftingHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;

public class BeerBrewerContainer extends BasicFPContainer<BeerBrewerTile> {

    public BeerBrewerContainer(int id, PlayerInventory playerInventory, PacketBuffer data) {
        this(id, playerInventory, ContainerHelper.getTileEntity(BeerBrewerTile.class, playerInventory, data));
    }

    public BeerBrewerContainer(int id, PlayerInventory playerInventory, BeerBrewerTile tile) {
        super(ModContainers.BEER_BREWER_CONTAINER.get(), id, tile);

        this.addSlot(new UniqueItemSlot(this.itemHandler, 0, 18, 34, MethodsUtil.createNNList(Items.DIRT)));
        this.addSlot(new UniqueItemSlot(this.itemHandler, 1, 36, 34, MethodsUtil.createNNList(Items.STONE)));
        this.addSlot(new UniqueItemSlot(this.itemHandler, 2, 27, 52, MethodsUtil.createNNList(Items.COBBLESTONE)));
        this.addSlot(new UniqueItemSlot(this.itemHandler, 3, 128, 39, MethodsUtil.createNNList(Items.APPLE))); //Output

        this.playerInventory(playerInventory, 8, 103);
        this.playerHotbar(playerInventory, 8, 162);

    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {
        if(slotId <= 3) {
            Slot slot = this.inventorySlots.get(slotId);
            if(slot.getHasStack() && clickTypeIn == ClickType.QUICK_MOVE) {
                return quickMove(slotId, 4);
            }
        }

        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()) {
            ItemStack clicked = slot.getStack();
            itemStack = clicked.copy();
            if(index <= 3) {
                if(!this.mergeItemStack(clicked, 4, this.inventorySlots.size(), true))
                    return ItemStack.EMPTY;
                slot.onSlotChange(clicked, itemStack);
            } else {
                BeerBrewerRecipe currentRecipe = this.tile.getCurrentRecipe();
                if(currentRecipe == null) {
                    if(CraftingHelper.containsInRecipe(clicked, ModRecipeTypes.BEER_BREWER_RECIPE_TYPE, playerIn.world)) {
                        int targetSlot = this.figureOutSlot(clicked);
                        this.itemHandler.insertItem(targetSlot, clicked, false);
                        clicked.setCount(0);
                        slot.onSlotChanged();
                        return ItemStack.EMPTY;
                    }
                } else {
                    for(int i = 0; i < currentRecipe.getInputs().length; i++) {
                        Ingredient ingredient = currentRecipe.getInputs()[i];
                        if(ingredient.getMatchingStacks()[0].isItemEqual(clicked)){
                            this.itemHandler.insertItem(i, clicked, false);
                            clicked.setCount(0);
                            slot.onSlotChanged();
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }
        }

        return itemStack;
    }

    private int figureOutSlot(ItemStack stack) {
        for(int i = 0; i < 3; i++) {
            Slot slot = this.inventorySlots.get(i);
            if(!(slot instanceof UniqueItemSlot)) return -1;
            if(slot.isItemValid(stack))
                return i;
        }
        return -1;
    }

}
