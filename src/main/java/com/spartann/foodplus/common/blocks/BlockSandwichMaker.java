package com.spartann.foodplus.common.blocks;

import com.spartann.foodplus.common.registries.ModTileEntities;
import com.spartann.foodplus.common.tile.TileSandwichMaker;
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

public class BlockSandwichMaker extends BlockContainer<TileSandwichMaker> {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BlockSandwichMaker(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH));
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public TileEntity create() {
        return ModTileEntities.SANDWICH_MAKER_TILE.get().create();
    }

    @Override
    public Class<TileSandwichMaker> tileEntityClass() {
        return TileSandwichMaker.class;
    }
}
