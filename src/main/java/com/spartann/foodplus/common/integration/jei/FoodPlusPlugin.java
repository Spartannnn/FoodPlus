package com.spartann.foodplus.common.integration.jei;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.integration.jei.categories.JuicerCategory;
import com.spartann.foodplus.common.registries.ModItems;
import com.spartann.foodplus.common.registries.ModRecipeTypes;
import com.spartann.foodplus.common.util.helper.CraftingHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class FoodPlusPlugin implements IModPlugin {

    public static final ResourceLocation PLUGIN_UID = new ResourceLocation(FoodPlusMod.MOD_ID, "foodplus");

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(CraftingHelper.findRecipesByType(ModRecipeTypes.JUICER_RECIPE_TYPE), JuicerCategory.UID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new JuicerCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.useNbtForSubtypes(ModItems.JUICE.get());
    }
}
