package com.spartann.foodplus.common.registries;

import com.google.common.collect.Sets;
import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.blocks.BeerBrewerBlock;
import com.spartann.foodplus.common.blocks.JuicerBlock;
import com.spartann.foodplus.common.blocks.SaltOreBlock;
import com.spartann.foodplus.common.blocks.crops.HopBlock;
import com.spartann.foodplus.common.blocks.trees.AbstractFPLeavesBlock;
import com.spartann.foodplus.common.blocks.trees.FPLogBlock;
import com.spartann.foodplus.common.blocks.trees.FPSaplingBlock;
import com.spartann.foodplus.common.blocks.trees.leaves.BananaLeaveBlock;
import com.spartann.foodplus.common.blocks.trees.leaves.CherryLeaveBlock;
import com.spartann.foodplus.common.blocks.trees.leaves.MangoLeaveBlock;
import com.spartann.foodplus.common.blocks.trees.leaves.PearLeaveBlock;
import com.spartann.foodplus.common.items.FruitTypes;
import com.spartann.foodplus.common.world.features.FruitTree;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, FoodPlusMod.MOD_ID);

    //=================================TREES=================================
    //LOGS
    public static final RegistryObject<Block> PEAR_LOG = register("pear_log", () -> new FPLogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> CHERRY_LOG = register("cherry_log", () -> new FPLogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> MANGO_LOG = register("mango_log", () -> new FPLogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> BANANA_LOG = register("banana_log", () -> new FPLogBlock(MaterialColor.WOOD, Block.Properties.from(Blocks.OAK_LOG)));

    //LEAVES (FRUITS)
    public static final RegistryObject<Block> PEAR_LEAVES = register("pear_leaves", () -> new PearLeaveBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> CHERRY_LEAVES = register("cherry_leaves", () -> new CherryLeaveBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> MANGO_LEAVES = register("mango_leaves", () -> new MangoLeaveBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> BANANA_LEAVES = register("banana_leaves", () -> new BananaLeaveBlock(Block.Properties.from(Blocks.OAK_LEAVES)));

    //SAPLING
    public static final RegistryObject<Block> PEAR_SAPLING = register("pear_sapling", () -> new FPSaplingBlock(() -> new FruitTree(FruitTypes.PEAR), Block.Properties.from(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> CHERRY_SAPLING = register("cherry_sapling", () -> new FPSaplingBlock(() -> new FruitTree(FruitTypes.CHERRY), Block.Properties.from(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> MANGO_SAPLING = register("mango_sapling", () -> new FPSaplingBlock(() -> new FruitTree(FruitTypes.MANGO), Block.Properties.from(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> BANANA_SAPLING = register("banana_sapling", () -> new FPSaplingBlock(() -> new FruitTree(FruitTypes.BANANA), Block.Properties.from(Blocks.OAK_SAPLING)));

    //Kitchen Util:
    public static final RegistryObject<Block> JUICER = register("juicer", () -> new JuicerBlock(Block.Properties.from(Blocks.FURNACE)));
    public static final RegistryObject<Block> BEER_BREWER = register("beer_brewer", () -> new BeerBrewerBlock(Block.Properties.from(Blocks.FURNACE)));

    //Ore Block:
    public static final RegistryObject<Block> SALT_ORE = register("salt_ore", () -> new SaltOreBlock(Block.Properties.from(Blocks.IRON_ORE)));

    //CROPS:
    public static final RegistryObject<Block> HOP_CROP = register("hop_crop", () -> new HopBlock(Block.Properties.create(Material.PLANTS).notSolid().doesNotBlockMovement().tickRandomly()));

    private static <T extends Block> RegistryObject<T> register(String registryName, Supplier<? extends T> supplier) {
        return BLOCKS.register(registryName, supplier);
    }

    public static Set<Block> getAllLeaves() {
        Set<Block> set = Sets.newHashSet();
        BLOCKS.getEntries().stream().filter(blockR -> blockR.get() instanceof AbstractFPLeavesBlock).forEach(blockR -> {
            set.add(blockR.get());
        });

        return set;
    }


}
