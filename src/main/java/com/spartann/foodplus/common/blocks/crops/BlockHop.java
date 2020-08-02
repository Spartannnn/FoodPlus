package com.spartann.foodplus.common.blocks.crops;

import com.google.common.collect.Lists;
import com.spartann.foodplus.common.loottable.ILootTableMultiDropsData;
import com.spartann.foodplus.common.registries.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;

import java.util.List;

public class BlockHop extends CropsBlock implements ILootTableMultiDropsData {

    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 2.0D, 9.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 4.0D, 9.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 6.0D, 9.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 8.0D, 9.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 10.0D, 9.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 12.0D, 9.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 14.0D, 9.0D),
            Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D)};


    public BlockHop(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.get(AGE)];
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return ModItems.HOP_SEED.get();
    }

    @Override
    public NonNullList<Item> getDrops() {
        return NonNullList.from(null, ModItems.HOP_SEED.get(), ModItems.HOP.get());
    }

    @Override
    public Block dataBlock() {
        return this;
    }

    @Override
    public List<ILootCondition.IBuilder> lootConditions() {
        return Lists.newArrayList(SurvivesExplosion.builder(), BlockStateProperty.builder(this));
    }
}
