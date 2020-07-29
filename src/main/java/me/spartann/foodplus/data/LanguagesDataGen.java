package me.spartann.foodplus.data;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.registries.ModBlocks;
import me.spartann.foodplus.common.registries.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

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
        this.addItem(ModItems.HARVEST_TOOL, "Harvesting Tool");
        this.addItem(ModItems.PEAR_FRUIT, "Pear");
        this.addItem(ModItems.CHERRY_FRUIT, "Cherry");
        this.addItem(ModItems.MANGO_FRUIT, "Mango");
        this.addItem(ModItems.SALT, "Salt");
    }

    private void blocks() {
        this.addBlock(ModBlocks.PEAR_LOG, "Pear Log");
        this.addBlock(ModBlocks.PEAR_LEAVES, "Pear Leaves");
        this.addBlock(ModBlocks.PEAR_SAPLING, "Pear Sapling");
        this.addBlock(ModBlocks.CHERRY_LOG, "Cherry Log");
        this.addBlock(ModBlocks.CHERRY_LEAVES, "Cherry Leaves");
        this.addBlock(ModBlocks.CHERRY_SAPLING, "Cherry Sapling");
        this.addBlock(ModBlocks.MANGO_LOG, "Mango Log");
        this.addBlock(ModBlocks.MANGO_LEAVES, "Mango Leaves");
        this.addBlock(ModBlocks.MANGO_SAPLING, "Mango Sapling");
        this.addBlock(ModBlocks.SALT_ORE, "Salt Ore");
        this.addBlock(ModBlocks.HOP_CROP, "Hop");
    }


}
