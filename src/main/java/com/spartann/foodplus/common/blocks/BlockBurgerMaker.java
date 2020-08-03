package com.spartann.foodplus.common.blocks;

import com.spartann.foodplus.common.registries.ModTileEntities;
import com.spartann.foodplus.common.tile.TileBurgerMaker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class BlockBurgerMaker extends BlockContainer<TileBurgerMaker> {

    private static final VoxelShape SHAPE_N_S = Stream.of(
            Block.makeCuboidShape(1, 15, 0, 15, 16, 16),
            Block.makeCuboidShape(14, 0, 0, 15, 15, 1),
            Block.makeCuboidShape(1, 0, 0, 2, 15, 1),
            Block.makeCuboidShape(1, 0, 15, 2, 15, 16),
            Block.makeCuboidShape(14, 0, 15, 15, 15, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_E_W =Stream.of(
            Block.makeCuboidShape(0, 15, 1, 16, 16, 15),
            Block.makeCuboidShape(0, 0, 1, 1, 15, 2),
            Block.makeCuboidShape(0, 0, 14, 1, 15, 15),
            Block.makeCuboidShape(15, 0, 14, 16, 15, 15),
            Block.makeCuboidShape(15, 0, 1, 16, 15, 2)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BlockBurgerMaker(Properties properties) {
        super(properties);
    }

    public static Direction getDirection(BlockState state) {
        return state.get(FACING);
    }

    @Override
    public TileEntity create() {
        return ModTileEntities.BURGER_MAKER_TILE.get().create();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
            case SOUTH:
            default:
                return SHAPE_N_S;
            case WEST:
            case EAST:
                return SHAPE_E_W;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public Class<TileBurgerMaker> tileEntityClass() {
        return TileBurgerMaker.class;
    }
}
