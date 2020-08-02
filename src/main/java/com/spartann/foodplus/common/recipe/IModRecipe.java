package com.spartann.foodplus.common.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;

public interface IModRecipe<W extends IInventory> extends IRecipe<W> {

    int getWorkingTime();

}
