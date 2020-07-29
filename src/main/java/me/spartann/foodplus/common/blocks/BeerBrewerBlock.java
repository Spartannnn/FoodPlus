package me.spartann.foodplus.common.blocks;

import me.spartann.foodplus.common.registries.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class BeerBrewerBlock extends FPContainerBlock {

    private static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(12, 8, 13, 13, 9, 14),
            Block.makeCuboidShape(3, 0, 3, 4, 8, 4),
            Block.makeCuboidShape(12, 0, 3, 13, 8, 4),
            Block.makeCuboidShape(12, 0, 12, 13, 8, 13),
            Block.makeCuboidShape(13, 8, 13, 14, 16, 14),
            Block.makeCuboidShape(2, 8, 13, 3, 16, 14),
            Block.makeCuboidShape(2, 8, 2, 3, 16, 3),
            Block.makeCuboidShape(13, 8, 2, 14, 16, 3),
            Block.makeCuboidShape(3, 0, 12, 4, 8, 13),
            Block.makeCuboidShape(4, 0, 13, 12, 8, 14),
            Block.makeCuboidShape(3, 8, 1, 13, 16, 2),
            Block.makeCuboidShape(3, 8, 14, 13, 16, 15),
            Block.makeCuboidShape(4, 0, 2, 12, 8, 3),
            Block.makeCuboidShape(2, 0, 4, 3, 8, 12),
            Block.makeCuboidShape(13, 0, 4, 14, 8, 12),
            Block.makeCuboidShape(14, 8, 3, 15, 16, 13),
            Block.makeCuboidShape(1, 8, 3, 2, 16, 13),
            Block.makeCuboidShape(2, 8, 3, 3, 9, 4),
            Block.makeCuboidShape(3, 8, 2, 4, 9, 3),
            Block.makeCuboidShape(12, 8, 2, 13, 9, 3),
            Block.makeCuboidShape(13, 8, 3, 14, 9, 4),
            Block.makeCuboidShape(13, 8, 12, 14, 9, 13),
            Block.makeCuboidShape(3, 8, 13, 4, 9, 14),
            Block.makeCuboidShape(2, 8, 12, 3, 9, 13)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public BeerBrewerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public TileEntity create() {
        return ModTileEntities.BEER_BREWER_TILE.get().create();
    }

}
