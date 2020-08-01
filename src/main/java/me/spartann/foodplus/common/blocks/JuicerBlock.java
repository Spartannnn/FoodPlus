package me.spartann.foodplus.common.blocks;

import me.spartann.foodplus.common.registries.ModTileEntities;
import me.spartann.foodplus.common.tile.JuicerTile;
import net.minecraft.tileentity.TileEntity;

public class JuicerBlock extends FPContainerBlock<JuicerTile> {

    public JuicerBlock(Properties properties) {
        super(properties);
    }


    @Override
    public TileEntity create() {
        return ModTileEntities.JUICER_TILE.get().create();
    }

    @Override
    public Class<JuicerTile> tileEntityClass() {
        return JuicerTile.class;
    }


}
