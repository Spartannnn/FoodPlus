package me.spartann.foodplus.common.registries;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.group.FoodPlusGroup;
import me.spartann.foodplus.common.items.BaseBlockItem;
import me.spartann.foodplus.common.items.BaseFoodItem;
import me.spartann.foodplus.common.items.HarvesterToolItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, FoodPlusMod.MOD_ID);

    //FOOD:
    public static final RegistryObject<Item> TEST_FOOD = ITEMS.register("test_food", () -> new BaseFoodItem(new Item.Properties().group(FoodPlusGroup.INSTANCE).maxStackSize(20)));

    //FRUITS:
    public static final RegistryObject<Item> PEAR_FRUIT = ITEMS.register("pear_fruit", () -> new BaseFoodItem(new Item.Properties().group(FoodPlusGroup.INSTANCE)));
    public static final RegistryObject<Item> CHERRY_FRUIT = ITEMS.register("cherry_fruit", () -> new BaseFoodItem(new Item.Properties().group(FoodPlusGroup.INSTANCE)));

    //TOOLS:
    public static final RegistryObject<Item> HARVEST_TOOL = ITEMS.register("harvest_tool", () -> new HarvesterToolItem(new Item.Properties().group(FoodPlusGroup.INSTANCE).maxStackSize(1)));

    //BLOCK ITEMS
    public static final RegistryObject<Item> PEAR_LOG = ITEMS.register("pear_log", () -> new BaseBlockItem(ModBlocks.PEAR_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> PEAR_LEAVES = ITEMS.register("pear_leaves", () -> new BaseBlockItem(ModBlocks.PEAR_LEAVES.get(), new Item.Properties()));
    public static final RegistryObject<Item> PEAR_SAPLING = ITEMS.register("pear_sapling", () -> new BaseBlockItem(ModBlocks.PEAR_SAPLING.get(), new Item.Properties()));


}
