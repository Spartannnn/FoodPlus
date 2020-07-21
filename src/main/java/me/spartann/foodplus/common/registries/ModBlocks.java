package me.spartann.foodplus.common.registries;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.blocks.trees.FPLogBlock;
import me.spartann.foodplus.common.blocks.trees.FPSaplingBlock;
import me.spartann.foodplus.common.blocks.trees.leaves.PearLeaveBlock;
import me.spartann.foodplus.common.world.features.PearTree;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, FoodPlusMod.MOD_ID);

    //=================================TREES=================================
    //LOGS
    public static final RegistryObject<Block> PEAR_LOG = register("pear_log", () -> new FPLogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_LOG)));

    //LEAVES (FRUITS)
    public static final RegistryObject<Block> PEAR_LEAVES = register("pear_leaves", () -> new PearLeaveBlock(Block.Properties.from(Blocks.OAK_LEAVES)));

    //SAPLING
    public static final RegistryObject<Block> PEAR_SAPLING = register("pear_sapling", () -> new FPSaplingBlock(PearTree::new, Block.Properties.from(Blocks.OAK_SAPLING)));


    private static <T extends Block> RegistryObject<T> register(String registryName, Supplier<? extends T> supplier) {
        return BLOCKS.register(registryName, supplier);
    }


}
