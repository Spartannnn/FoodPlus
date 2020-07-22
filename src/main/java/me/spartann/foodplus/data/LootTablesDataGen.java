package me.spartann.foodplus.data;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.loottable.ILootTableMultiDropsData;
import me.spartann.foodplus.common.registries.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LootTablesDataGen extends LootTableProvider {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final DataGenerator generator;
    private Map<Block, LootTable.Builder> tablemap = new HashMap<>();

    public LootTablesDataGen(DataGenerator generator) {
        super(generator);
        this.generator = generator;
    }

    @Override
    public void act(DirectoryCache cache) {
        System.out.println("Generating Block loot tables");

        ModBlocks.BLOCKS.getEntries().stream().map(r -> r.get()).forEach(b -> {
            if (b instanceof ILootTableMultiDropsData) {
                LootTable.Builder table = tablemap.getOrDefault(b, multipleDrops((ILootTableMultiDropsData) b));
                tablemap.put(b, table);
            } else {
                LootTable.Builder table = tablemap.getOrDefault(b, selfDrop(b));
                tablemap.put(b, table);
            }
        });

        for (Map.Entry<Block, LootTable.Builder> e : this.tablemap.entrySet()) {
            Path path = getPath(this.generator.getOutputFolder(), e.getKey());
            System.out.println(path);
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(e.getValue().setParameterSet(LootParameterSets.BLOCK).build()), path);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private Path getPath(Path root, Block block) {
        return root.resolve("data/" + FoodPlusMod.MOD_ID + "/loot_tables/blocks/" + block.getRegistryName().getPath() + ".json");
    }

    public LootTable.Builder selfDrop(Block b) {
        LootEntry.Builder<?> entry = ItemLootEntry.builder(b);
        LootPool.Builder pool = LootPool.builder().name("selfdrop").rolls(ConstantRange.of(1)).addEntry(entry)
                .acceptCondition(SurvivesExplosion.builder());
        return LootTable.builder().addLootPool(pool);
    }

    public LootTable.Builder multipleDrops(ILootTableMultiDropsData data) {
        List<LootEntry.Builder<?>> entries = Lists.newArrayList();
        if(data.getDrops() == null)
            return this.selfDrop(data.dataBlock());
        data.getDrops().forEach(item -> entries.add(ItemLootEntry.builder(item)));
        if(entries.isEmpty())
            return this.selfDrop(data.dataBlock());

        List<LootPool.Builder> pools = Lists.newArrayList();
        int i = 0;
        for(LootEntry.Builder<?> entry : entries) {
            LootPool.Builder pool = LootPool.builder().name(data.dataBlock().getRegistryName().getPath() + i++).rolls(ConstantRange.of(i++));
            pool.addEntry(entry);
            for(ILootCondition.IBuilder condition : data.lootConditions())
                pool.acceptCondition(condition);
            pools.add(pool);
        }
        LootTable.Builder table = LootTable.builder();
        pools.forEach(table::addLootPool);
        return table;
    }

    @Override
    public String getName() {
        return "block loot tables";
    }
}
