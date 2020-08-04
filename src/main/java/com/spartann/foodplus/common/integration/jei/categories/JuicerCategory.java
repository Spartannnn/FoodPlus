package com.spartann.foodplus.common.integration.jei.categories;

import com.google.common.collect.Lists;
import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.items.juice.ItemJuiceBottle;
import com.spartann.foodplus.common.items.juice.JuiceBottleFlavour;
import com.spartann.foodplus.common.items.juice.JuiceBottleFullness;
import com.spartann.foodplus.common.recipe.juicer.IJuicerRecipe;
import com.spartann.foodplus.common.registries.ModBlocks;
import com.spartann.foodplus.common.registries.ModItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.List;

public class JuicerCategory implements IRecipeCategory<IJuicerRecipe> {

    public static final ResourceLocation UID = IJuicerRecipe.TYPE_ID;

    private IDrawable background;
    private IDrawable icon;

    public JuicerCategory(IGuiHelper guiHelper) { // 1 und 67
        this.background = guiHelper.createDrawable(new ResourceLocation(FoodPlusMod.MOD_ID, "textures/gui/container/juicer.png"), 1, 1, 175, 66);
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(ModBlocks.JUICER.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends IJuicerRecipe> getRecipeClass() {
        return IJuicerRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Juicer";
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, IJuicerRecipe iJuicerRecipe, IIngredients iIngredients) {
        IGuiItemStackGroup itemStackGroup = iRecipeLayout.getItemStacks();
        itemStackGroup.init(0, true, 122, 19);
        itemStackGroup.set(0, iIngredients.getOutputs(VanillaTypes.ITEM).get(0));

        itemStackGroup.init(1, true, 25, 19);
        itemStackGroup.set(1, iIngredients.getInputs(VanillaTypes.ITEM).get(0));
    }

    @Override
    public void setIngredients(IJuicerRecipe iJuicerRecipe, IIngredients iIngredients) {
        iIngredients.setInputs(VanillaTypes.ITEM, Arrays.asList(iJuicerRecipe.getIngredients().get(0).getMatchingStacks()));
        List<ItemStack> outputs = Lists.newArrayList();
        for (Item item : ModItems.getFruits()) {
            iIngredients.setInput(VanillaTypes.ITEM, item.getDefaultInstance());
            ItemStack output = new ItemStack(ModItems.JUICE.get(), 1);
            ItemJuiceBottle.setFlavour(output, JuiceBottleFlavour.byName(item.getRegistryName().getPath().replace("_fruit", "")));
            ItemJuiceBottle.setFullness(output, JuiceBottleFullness.FULL);
            outputs.add(output);
        }
        iIngredients.setOutputs(VanillaTypes.ITEM, outputs);
    }
}
