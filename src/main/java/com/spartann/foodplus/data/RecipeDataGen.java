package com.spartann.foodplus.data;

import com.spartann.foodplus.common.registries.ModItems;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

import java.util.function.Consumer;

public class RecipeDataGen extends RecipeProvider {

    public RecipeDataGen(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        this.registerStandardRecipes(consumer);
    }

    private void registerStandardRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModItems.HARVEST_TOOL.get())
                .key('F', Items.FLINT).key('I', Items.IRON_INGOT).key('S', Items.STICK)
                .patternLine(" FS")
                .patternLine(" SI")
                .patternLine("S  ")
                .addCriterion("has_item", hasItem(itemPredicate(Items.STICK), itemPredicate(Items.IRON_INGOT), itemPredicate(Items.STICK))).build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(Items.WHEAT), ModItems.MALT.get(), 2.5F, 5)
                .addCriterion("has_item", hasItem(itemPredicate(Items.WHEAT))).build(consumer);
    }


    private ItemPredicate itemPredicate(IItemProvider item) {
        return ItemPredicate.Builder.create().item(item).build();
    }

}
