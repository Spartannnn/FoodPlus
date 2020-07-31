package me.spartann.foodplus.common.blocks;

import me.spartann.foodplus.common.registries.ModTileEntities;
import me.spartann.foodplus.common.tile.JuicerBlockTile;
import net.minecraft.tileentity.TileEntity;

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
