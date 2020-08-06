package com.spartann.foodplus.common.blocks.trees;

import com.spartann.foodplus.common.items.FruitTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class BlockFruit extends Block {

    public static final EnumProperty<FruitTypes> FRUIT_TYPE = EnumProperty.create("fruit", FruitTypes.class);

    private static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(3, 7, 3, 4, 14, 13),
            Block.makeCuboidShape(3, 0, 2, 13, 7, 3),
            Block.makeCuboidShape(3, 0, 13, 13, 7, 14),
            Block.makeCuboidShape(4, 7, 12, 12, 14, 13),
            Block.makeCuboidShape(4, 7, 3, 12, 14, 4),
            Block.makeCuboidShape(4, 14, 4, 12, 15, 12),
            Block.makeCuboidShape(2, 0, 3, 3, 7, 13),
            Block.makeCuboidShape(13, 0, 3, 14, 7, 13),
            Block.makeCuboidShape(12, 7, 3, 13, 14, 13)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public BlockFruit() {
        super(Block.Properties.create(Material.ORGANIC).notSolid().doesNotBlockMovement());
        this.setDefaultState(this.getStateContainer().getBaseState().with(FRUIT_TYPE, FruitTypes.PEAR));
    }


    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FRUIT_TYPE);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

}
