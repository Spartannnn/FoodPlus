package me.spartann.foodplus.data;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.registries.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import java.util.Objects;

public class BlockModelDataGen extends BlockModelProvider {

    public BlockModelDataGen(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.registerLog(ModBlocks.PEAR_LOG);
        this.registerLog(ModBlocks.CHERRY_LOG);
        this.registerLog(ModBlocks.MANGO_LOG);

        this.registerLeaves(ModBlocks.PEAR_LEAVES);
        this.registerLeaves(ModBlocks.CHERRY_LEAVES);
        this.registerLeaves(ModBlocks.MANGO_LEAVES);

        this.registerSapling(ModBlocks.PEAR_SAPLING);
        this.registerSapling(ModBlocks.CHERRY_SAPLING);
        this.registerSapling(ModBlocks.MANGO_SAPLING);

        this.cubeAll(this.registryName(ModBlocks.JUICER), getTexture(this.registryName(ModBlocks.JUICER)));
        this.cubeAll(this.registryName(ModBlocks.SALT_ORE), getTexture(this.registryName(ModBlocks.SALT_ORE)));


    }

    private void registerLog(RegistryObject<Block> ro) {
        String name = this.registryName(ro);
        this.cubeColumn(name, getTexture(name), getTexture(name + "_top"));
    }

    private void registerLeaves(RegistryObject<Block> ro) {
        String name = this.registryName(ro);
        this.cubeAll(name, getTexture(name));
    }

    private void registerSapling(RegistryObject<Block> ro) {
        String name = this.registryName(ro);
        this.cross(name, getTexture(name));
    }

    private String registryName(RegistryObject<Block> ro) {
        return Objects.requireNonNull(ro.get().getRegistryName()).getPath();
    }

    private ResourceLocation getTexture(String name) {
        return new ResourceLocation(FoodPlusMod.MOD_ID, "blocks/" + name);
    }
}
