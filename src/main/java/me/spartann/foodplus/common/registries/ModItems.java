package me.spartann.foodplus.common.registries;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.group.FoodPlusGroup;
import me.spartann.foodplus.common.items.BaseBlockItem;
import me.spartann.foodplus.common.items.BaseFoodItem;
import me.spartann.foodplus.common.items.fruits.PearFruitItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, FoodPlusMod.MOD_ID);

    //FOOD:
    public static final RegistryObject<Item> TEST_FOOD = ITEMS.register("test_food", () -> new BaseFoodItem(new Item.Properties().group(FoodPlusGroup.INSTANCE).maxStackSize(20)));
    public static final RegistryObject<Item> PEAR_FRUIT = ITEMS.register("pear_fruit", () -> new PearFruitItem(new Item.Properties().group(FoodPlusGroup.INSTANCE)));

    //BLOCK ITEMS
    public static final RegistryObject<Item> PEAR_LOG = ITEMS.register("pear_log", () -> new BaseBlockItem(ModBlocks.PEAR_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> PEAR_LEAVES = ITEMS.register("pear_leaves", () -> new BaseBlockItem(ModBlocks.PEAR_LEAVES.get(), new Item.Properties()));
    public static final RegistryObject<Item> PEAR_SAPLING = ITEMS.register("pear_sapling", () -> new BaseBlockItem(ModBlocks.PEAR_SAPLING.get(), new Item.Properties()));


}
