package com.spartann.foodplus.common.recipe.burgermaker;

import com.spartann.foodplus.common.inventory.ModCraftingInventory;
import com.spartann.foodplus.common.registries.ModRecipeTypes;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class ShapedBurgerRecipe extends AbstractBurgerRecipe {

    private int height;
    private int width;

    public ShapedBurgerRecipe(ShapedRecipe shaped) {
        super(shaped);
        this.height = shaped.getHeight();
        this.width = shaped.getWidth();
    }

    private ModCraftingInventory getCraftingInv(RecipeWrapper inv) {
        NonNullList<ItemStack> list = NonNullList.create();
        for (int i = 0; i < 4; i++) list.add(inv.getStackInSlot(i));
        return new ModCraftingInventory(null, 2, 2, list);
    }

    @Override
    public boolean matches(RecipeWrapper inv, World worldIn) {
        ModCraftingInventory craftingGridIn = getCraftingInv(inv);

        for (int i = 0; i <= craftingGridIn.getWidth() - this.width; ++i) {
            for (int j = 0; j <= craftingGridIn.getHeight() - this.height; ++j) {
                if (this.checkMatch(craftingGridIn, i, j, true)) {
                    return true;
                }
                if (this.checkMatch(craftingGridIn, i, j, false)) {
                    return true;
                }
            }
        }

        return false;
    }


    private boolean checkMatch(CraftingInventory craftingInventory, int slotx, int sloty, boolean mirrored) {
        for (int i = 0; i < craftingInventory.getWidth(); ++i) {
            for (int j = 0; j < craftingInventory.getHeight(); ++j) {
                int k = i - slotx;
                int l = j - sloty;

                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
                    if (mirrored) {
                        ingredient = this.ingredients.get(this.width - k - 1 + l * this.width);
                    } else {
                        ingredient = this.ingredients.get(k + l * this.width);
                    }
                }
                if (!ingredient.test(craftingInventory.getStackInSlot(i + j * craftingInventory.getWidth()))) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return this.getRecipeOutput();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output;
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.SHAPED_BURGER_SERIALIZER.get();
    }

    public ShapedRecipe convertShaped() {
        return new ShapedRecipe(recipeId, "", 2, 2, ingredients, output);
    }
}
