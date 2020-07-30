package me.spartann.foodplus.common.blocks;

import me.spartann.foodplus.common.registries.ModTileEntities;
import me.spartann.foodplus.common.tile.JuicerBlockTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class JuicerBlock extends FPContainerBlock<JuicerBlockTile> {

    public JuicerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity create() {
        return ModTileEntities.JUICER_TILE.get().create();
    }

    @Override
    public Class<JuicerBlockTile> tileEntityClass() {
        return JuicerBlockTile.class;
    }


}
