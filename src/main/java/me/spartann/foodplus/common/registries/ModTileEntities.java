package me.spartann.foodplus.common.registries;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.tile.JuicerBlockTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, FoodPlusMod.MOD_ID);

    public static final RegistryObject<TileEntityType<JuicerBlockTile>> JUICER_TILE = TILE_ENTITIES.register("juicer_tile",
            () -> TileEntityType.Builder.create(JuicerBlockTile::new, ModBlocks.JUICER.get()).build(null));

}
