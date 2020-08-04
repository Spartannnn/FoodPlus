package com.spartann.foodplus.common.integration.jei.categories;

import com.google.common.collect.Lists;
import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.items.juice.ItemJuiceBottle;
import com.spartann.foodplus.common.items.juice.JuiceBottleFlavour;
import com.spartann.foodplus.common.items.juice.JuiceBottleFullness;
import com.spartann.foodplus.common.recipe.juicer.IJuicerRecipe;
import com.spartann.foodplus.common.registries.ModBlocks;
import com.spartann.foodplus.common.registries.ModItems;
import com.spartann.foodplus.common.util.MethodsUtil;
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
        List<List<ItemStack>> inList = iIngredients.getInputs(VanillaTypes.ITEM);
        List<List<ItemStack>> outList = iIngredients.getOutputs(VanillaTypes.ITEM);

        IGuiItemStackGroup itemStackGroup = iRecipeLayout.getItemStacks();
        itemStackGroup.init(0, true, 122, 19);
        itemStackGroup.set(0, outList.get(0));
        itemStackGroup.init(1, true, 25, 19);
        itemStackGroup.set(1, inList.get(0));
    }

    @Override
    public void setIngredients(IJuicerRecipe iJuicerRecipe, IIngredients iIngredients) {
        List<List<ItemStack>> inList = Lists.newArrayList();
        inList.add(MethodsUtil.convertItemsIntoStacks(ModItems.getFruits()));
        iIngredients.setInputLists(VanillaTypes.ITEM, inList);
        List<List<ItemStack>> outList = Lists.newArrayList();
        List<ItemStack> outputs = Lists.newArrayList();
        for (Item item : ModItems.getFruits()) {
            ItemStack output = new ItemStack(ModItems.JUICE.get(), 1);
            String name = item.getRegistryName().getPath();
            if(name.contains("_fruit"))
                name = name.replace("_fruit", "");
            if(name.contains("melon"))
                name = "melon";
            ItemJuiceBottle.setFlavour(output, JuiceBottleFlavour.byName(name));
            ItemJuiceBottle.setFullness(output, JuiceBottleFullness.FULL);
            outputs.add(output);
        }
        outList.add(outputs);
        iIngredients.setOutputLists(VanillaTypes.ITEM, outList);
    }
}
