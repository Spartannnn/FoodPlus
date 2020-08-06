package com.spartann.foodplus.common.world.features;

import com.spartann.foodplus.common.items.FruitTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Random;

public class SaplingFruitTree extends Tree {

    private FruitTypes type;

    public SaplingFruitTree(FruitTypes type) {
        this.type = type;
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<?> chunkGeneratorIn, BlockPos pos, BlockState blockStateIn, Random random) {
        if (world.getBlockState(pos.down()).getBlock().equals(Blocks.DIRT)
                || world.getBlockState(pos.down()).isIn(Tags.Blocks.DIRT) || world.getBlockState(pos.down()).equals(Blocks.DIRT.getDefaultState())) {
            generateTree(world, pos);
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return null;
    }

    public void generateTree(IWorld world, BlockPos pos) {
        BlockState trunk = type.getLog().getDefaultState();
        BlockState leaves = type.getLeaves().getDefaultState();
        BlockState fruit = FruitTypes.getFruitState(type);

        world.setBlockState(pos.up(0), trunk, 3);
        for (int i = 1; i < 6; i++) {
            if (world.getBlockState(pos.up(i)).getMaterial().isReplaceable())
                world.setBlockState(pos.up(i), trunk, 3);
        }
        //Layer Fruit
        if (world.getBlockState(pos.up(2).north()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(2).north(), fruit, 3);//fruit
        if (world.getBlockState(pos.up(2).south()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(2).south(), fruit, 3);//fruit
        if (world.getBlockState(pos.up(2).east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(2).east(), fruit, 3);//fruit
        if (world.getBlockState(pos.up(2).west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(2).west(), fruit, 3);//fruit

        //Layer 1
        if (world.getBlockState(pos.up(3).north()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).north(), leaves, 3);
        if (world.getBlockState(pos.up(3).north().north()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).north().north(), leaves, 3);


        if (world.getBlockState(pos.up(3).north().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).north().west(), leaves, 3);
        if (world.getBlockState(pos.up(3).north().north().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).north().north().west(), leaves, 3);
        if (world.getBlockState(pos.up(2).north().north().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(2).north().north().west(), fruit, 3);//fruit
        if (world.getBlockState(pos.up(3).north().west().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).north().west().west(), leaves, 3);
        if (world.getBlockState(pos.up(2).north().west().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(2).north().west().west(), fruit, 3);//fruit
        if (world.getBlockState(pos.up(3).north().north().west().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).north().north().west().west(), leaves, 3);

        if (world.getBlockState(pos.up(3).north().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).north().east(), leaves, 3);
        if (world.getBlockState(pos.up(3).north().north().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).north().north().east(), leaves, 3);
        if (world.getBlockState(pos.up(2).north().north().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(2).north().north().east(), fruit, 3);//fruit
        if (world.getBlockState(pos.up(3).north().east().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).north().east().east(), leaves, 3);
        if (world.getBlockState(pos.up(2).north().east().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(2).north().east().east(), fruit, 3);//fruit
        if (world.getBlockState(pos.up(3).north().north().east().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).north().north().east().east(), leaves, 3);

        if (world.getBlockState(pos.up(3).south()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).south(), leaves, 3);
        if (world.getBlockState(pos.up(3).south().south()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).south().south(), leaves, 3);

        if (world.getBlockState(pos.up(3).south().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).south().west(), leaves, 3);
        if (world.getBlockState(pos.up(3).south().south().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).south().south().west(), leaves, 3);
        if (world.getBlockState(pos.up(2).south().south().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(2).south().south().west(), fruit, 3);//fruit
        if (world.getBlockState(pos.up(3).south().west().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).south().west().west(), leaves, 3);
        if (world.getBlockState(pos.up(2).south().west().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(2).south().west().west(), fruit, 3);//fruit
        if (world.getBlockState(pos.up(3).south().south().west().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).south().south().west().west(), leaves, 3);

        if (world.getBlockState(pos.up(3).south().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).south().east(), leaves, 3);
        if (world.getBlockState(pos.up(3).south().south().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).south().south().east(), leaves, 3);
        if (world.getBlockState(pos.up(2).south().south().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(2).south().south().east(), fruit, 3);//fruit
        if (world.getBlockState(pos.up(3).south().east().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).south().east().east(), leaves, 3);
        if (world.getBlockState(pos.up(2).south().east().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(2).south().east().east(), fruit, 3);//fruit
        if (world.getBlockState(pos.up(3).south().south().east().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).south().south().east().east(), leaves, 3);

        if (world.getBlockState(pos.up(3).east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).east(), leaves, 3);
        if (world.getBlockState(pos.up(3).east().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).east().east(), leaves, 3);

        if (world.getBlockState(pos.up(3).west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).west(), leaves, 3);
        if (world.getBlockState(pos.up(3).west().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(3).west().west(), leaves, 3);

        //Layer 2
        if (world.getBlockState(pos.up(4).north()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).north(), leaves, 3);
        if (world.getBlockState(pos.up(4).north().north()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).north().north(), leaves, 3);


        if (world.getBlockState(pos.up(4).north().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).north().west(), leaves, 3);
        if (world.getBlockState(pos.up(4).north().north().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).north().north().west(), leaves, 3);
        if (world.getBlockState(pos.up(4).north().west().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).north().west().west(), leaves, 3);
        //if (world.getBlockState(pos.up(4).north().north().west().west()).getMaterial().isReplaceable())
        //	world.setBlockState(pos.up(4).north().north().west().west(), leaves, 3);

        if (world.getBlockState(pos.up(4).north().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).north().east(), leaves, 3);
        if (world.getBlockState(pos.up(4).north().north().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).north().north().east(), leaves, 3);
        if (world.getBlockState(pos.up(4).north().east().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).north().east().east(), leaves, 3);
        //if (world.getBlockState(pos.up(4).north().north().east().east()).getMaterial().isReplaceable())
        //	world.setBlockState(pos.up(4).north().north().east().east(), leaves, 3);

        if (world.getBlockState(pos.up(4).south()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).south(), leaves, 3);
        if (world.getBlockState(pos.up(4).south().south()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).south().south(), leaves, 3);

        if (world.getBlockState(pos.up(4).south().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).south().west(), leaves, 3);
        if (world.getBlockState(pos.up(4).south().south().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).south().south().west(), leaves, 3);
        if (world.getBlockState(pos.up(4).south().west().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).south().west().west(), leaves, 3);
        //if (world.getBlockState(pos.up(4).south().south().west().west()).getMaterial().isReplaceable())
        //	world.setBlockState(pos.up(4).south().south().west().west(), leaves, 3);

        if (world.getBlockState(pos.up(4).south().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).south().east(), leaves, 3);
        if (world.getBlockState(pos.up(4).south().south().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).south().south().east(), leaves, 3);
        if (world.getBlockState(pos.up(4).south().east().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).south().east().east(), leaves, 3);
        //if (world.getBlockState(pos.up(4).south().south().east().east()).getMaterial().isReplaceable())
        //	world.setBlockState(pos.up(4).south().south().east().east(), leaves, 3);

        if (world.getBlockState(pos.up(4).east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).east(), leaves, 3);
        if (world.getBlockState(pos.up(4).east().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).east().east(), leaves, 3);

        if (world.getBlockState(pos.up(4).west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).west(), leaves, 3);
        if (world.getBlockState(pos.up(4).west().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(4).west().west(), leaves, 3);

        //Layer 3
        if (world.getBlockState(pos.up(5).north()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(5).north(), leaves, 3);
        if (world.getBlockState(pos.up(5).north().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(5).north().west(), leaves, 3);
        if (world.getBlockState(pos.up(5).north().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(5).north().east(), leaves, 3);
        if (world.getBlockState(pos.up(5).south()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(5).south(), leaves, 3);
        if (world.getBlockState(pos.up(5).south().west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(5).south().west(), leaves, 3);
        if (world.getBlockState(pos.up(5).south().east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(5).south().east(), leaves, 3);
        if (world.getBlockState(pos.up(5).east()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(5).east(), leaves, 3);
        if (world.getBlockState(pos.up(5).west()).getMaterial().isReplaceable())
            world.setBlockState(pos.up(5).west(), leaves, 3);
        //Layer 4
        if (world.getBlockState(pos.up(6)).getMaterial().isReplaceable())
            world.setBlockState(pos.up(6), leaves, 3);


    }
}
