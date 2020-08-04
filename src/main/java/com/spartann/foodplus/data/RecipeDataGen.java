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
        this.registerCookingRecipes(consumer);
        this.registerShapelessRecipes(consumer);
    }

    private void registerStandardRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModItems.HARVEST_TOOL.get())
                .key('F', Items.FLINT).key('I', Items.IRON_INGOT).key('S', Items.STICK)
                .patternLine(" FS")
                .patternLine(" SI")
                .patternLine("S  ")
                .addCriterion("has_item", hasItem(itemPredicate(Items.STICK), itemPredicate(Items.IRON_INGOT), itemPredicate(Items.STICK))).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModItems.CHOCOLATE.get())
                .key('C', Items.COCOA_BEANS).key('M', Items.MILK_BUCKET).key('S', Items.SUGAR)
                .patternLine("CCC")
                .patternLine("SMS")
                .patternLine("CCC")
                .addCriterion("has_item", hasItem(itemPredicate(Items.COCOA_BEANS), itemPredicate(Items.MILK_BUCKET), itemPredicate(Items.SUGAR))).build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModItems.KNIFE.get())
                .key('S', Items.STICK).key('I', Items.IRON_INGOT)
                .patternLine("  I")
                .patternLine(" S ")
                .patternLine("S  ")
                .addCriterion("has_item", hasItem(itemPredicate(Items.STICK), itemPredicate(Items.IRON_INGOT))).build(consumer);
    }

    private void registerShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.CUTTET_CHICKEN.get())
                .addIngredient(ModItems.KNIFE.get()).addIngredient(Items.CHICKEN)
                .addCriterion("has_item", hasItem(itemPredicate(ModItems.KNIFE.get()), itemPredicate(Items.CHICKEN)))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.CUTTET_POTATO.get())
                .addIngredient(ModItems.KNIFE.get()).addIngredient(Items.POTATO)
                .addCriterion("has_item", hasItem(itemPredicate(ModItems.KNIFE.get()), itemPredicate(Items.POTATO)))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.CRISPS.get())
                .addIngredient(ModItems.CUTTET_POTATO.get()).addIngredient(ModItems.SALT.get())
                .addCriterion("has_item", hasItem(itemPredicate(ModItems.CUTTET_POTATO.get()), itemPredicate(ModItems.SALT.get())))
                .build(consumer);
    }

    private void registerCookingRecipes(Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(Items.WHEAT), ModItems.MALT.get(), 2.5F, 5)
                .addCriterion("has_item", hasItem(itemPredicate(Items.WHEAT))).build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ModItems.CUTTET_POTATO.get()), ModItems.FRIES.get(), 2.5F, 5)
                .addCriterion("has_item", hasItem(itemPredicate(ModItems.CUTTET_POTATO.get()))).build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ModItems.CUTTET_CHICKEN.get()), ModItems.NUGGETS.get(), 2.5F, 5)
                .addCriterion("has_item", hasItem(itemPredicate(ModItems.CUTTET_CHICKEN.get()))).build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ModItems.SASUAGE.get()), ModItems.SASUAGE_ROASTED.get(), 2.5F, 5)
                .addCriterion("has_item", hasItem(ModItems.SASUAGE.get())).build(consumer);
    }


    private ItemPredicate itemPredicate(IItemProvider item) {
        return ItemPredicate.Builder.create().item(item).build();
    }

}
