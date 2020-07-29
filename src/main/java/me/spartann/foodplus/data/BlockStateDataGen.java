package me.spartann.foodplus.data;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.registries.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import java.util.Objects;

public class BlockStateDataGen extends BlockStateProvider {

    public BlockStateDataGen(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.axisBlock((RotatedPillarBlock) ModBlocks.PEAR_LOG.get(), getTexture(ModBlocks.PEAR_LOG), getExtendedTexture(ModBlocks.PEAR_LOG, "_top"));
        this.axisBlock((RotatedPillarBlock) ModBlocks.CHERRY_LOG.get(), getTexture(ModBlocks.CHERRY_LOG), getExtendedTexture(ModBlocks.CHERRY_LOG, "_top"));
        this.axisBlock((RotatedPillarBlock) ModBlocks.MANGO_LOG.get(), getTexture(ModBlocks.MANGO_LOG), getExtendedTexture(ModBlocks.MANGO_LOG, "_top"));

        this.simpleBlock(ModBlocks.PEAR_LEAVES.get(), models().getExistingFile(getModelPath(ModBlocks.PEAR_LEAVES)));
        this.simpleBlock(ModBlocks.CHERRY_LEAVES.get(), models().getExistingFile(getModelPath(ModBlocks.CHERRY_LEAVES)));
        this.simpleBlock(ModBlocks.MANGO_LEAVES.get(), models().getExistingFile(getModelPath(ModBlocks.MANGO_LEAVES)));

        this.simpleBlock(ModBlocks.PEAR_SAPLING.get(), models().getExistingFile(getModelPath(ModBlocks.PEAR_SAPLING)));
        this.simpleBlock(ModBlocks.CHERRY_SAPLING.get(), models().getExistingFile(getModelPath(ModBlocks.CHERRY_SAPLING)));
        this.simpleBlock(ModBlocks.MANGO_SAPLING.get(), models().getExistingFile(getModelPath(ModBlocks.MANGO_SAPLING)));

        this.simpleBlock(ModBlocks.JUICER.get(), models().getExistingFile(getModelPath(ModBlocks.JUICER)));
        this.simpleBlock(ModBlocks.SALT_ORE.get(), models().getExistingFile(getModelPath(ModBlocks.SALT_ORE)));

    }

    private String getBlockName(RegistryObject<Block> ro) {
        return Objects.requireNonNull(ro.get().getRegistryName()).getPath();
    }

    private ResourceLocation getTexture(RegistryObject<Block> ro) {
        return new ResourceLocation(FoodPlusMod.MOD_ID, "blocks/" + getBlockName(ro));
    }

    private ResourceLocation getExtendedTexture(RegistryObject<Block> ro, String suffix) {
        return new ResourceLocation(FoodPlusMod.MOD_ID, "blocks/" + getBlockName(ro) + suffix);
    }

    private ResourceLocation getModelPath(RegistryObject<Block> ro) {
        return new ResourceLocation(FoodPlusMod.MOD_ID, "block/" + getBlockName(ro));
    }
}
