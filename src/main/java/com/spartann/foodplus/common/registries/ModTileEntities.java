package com.spartann.foodplus.common.registries;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.tile.TileBeerBrewer;
import com.spartann.foodplus.common.tile.TileJuicer;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, FoodPlusMod.MOD_ID);

    public static final RegistryObject<TileEntityType<TileJuicer>> JUICER_TILE = registerTile("juicer_tile", TileJuicer::new, ModBlocks.JUICER);
    public static final RegistryObject<TileEntityType<TileBeerBrewer>> BEER_BREWER_TILE = registerTile("beer_brewer_tile", TileBeerBrewer::new, ModBlocks.BEER_BREWER);

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> registerTile(String id, Supplier<T> supplier, RegistryObject<Block> block) {
        return TILE_ENTITIES.register(id, () -> TileEntityType.Builder.create(supplier, block.get()).build(null));
    }


}
