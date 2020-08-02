package com.spartann.foodplus.data;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.registries.ModBlocks;
import com.spartann.foodplus.common.registries.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.fml.RegistryObject;

import java.util.Objects;

public class ItemModelDataGen extends ItemModelProvider {

    public ItemModelDataGen(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        ModItems.ITEMS.getEntries().forEach(itemRo -> {
            String name = itemRo.get().getRegistryName().getPath();
            if (isBlockModel(name)) {
                this.withExistingParent(name, FoodPlusMod.MOD_ID + ":block/" + name);
            } else {
                if (!name.equals(ModItems.JUICE.get().getRegistryName().getPath())) {
                    this.withExistingParent(name, "item/generated").texture("layer0", getTextureRL(name));
                }
            }
        });
    }

    private boolean isBlockModel(String name) {
        for (RegistryObject<Block> ro : ModBlocks.BLOCKS.getEntries())
            if (Objects.requireNonNull(ro.get().getRegistryName()).getPath().equals(name))
                return true;
        return false;
    }

    private ResourceLocation getTextureRL(String name) {
        return new ResourceLocation(FoodPlusMod.MOD_ID, "items/" + name);
    }


}
