package me.spartann.foodplus.data;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.registries.ModBlocks;
import me.spartann.foodplus.common.registries.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;

public class LanguagesDataGen extends LanguageProvider {

    public LanguagesDataGen(DataGenerator gen) {
        super(gen, FoodPlusMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.items();
        this.blocks();
    }

    private void items() {
        this.addItem(ModItems.TEST_FOOD, "Test Food");
    }

    private void blocks() {
        this.addBlock(ModBlocks.PEAR_LOG, "Pear Log");
        this.addBlock(ModBlocks.PEAR_LEAVES, "Pear Leaves");
        this.addBlock(ModBlocks.PEAR_SAPLING, "Pear Sapling");
    }


}
