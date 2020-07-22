package me.spartann.foodplus.data;

import me.spartann.foodplus.common.registries.ModItems;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
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
                .patternLine("XFS")
                .patternLine("XSI")
                .patternLine("SXX")
                .key('F', Items.FLINT).key('I', Items.IRON_INGOT).key('X', Items.AIR).key('S', Items.STICK)
                .addCriterion("has_item", hasItem(itemPredicate(Items.STICK), itemPredicate(Items.IRON_INGOT), itemPredicate(Items.STICK))).build(consumer);

    }

    private ItemPredicate itemPredicate(IItemProvider item) {
        return ItemPredicate.Builder.create().item(item).build();
    }
}
