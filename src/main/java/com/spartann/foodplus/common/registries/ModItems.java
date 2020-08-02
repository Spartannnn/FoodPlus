package com.spartann.foodplus.common.registries;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.group.FoodPlusGroup;
import com.spartann.foodplus.common.items.*;
import com.spartann.foodplus.common.items.juicer.JuiceBottleItem;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, FoodPlusMod.MOD_ID);

    //FOOD:
    public static final RegistryObject<Item> JUICE = ITEMS.register("juice", () -> new JuiceBottleItem(new Item.Properties().group(FoodPlusGroup.INSTANCE).maxStackSize(12)));


    //FRUITS:
    public static final RegistryObject<Item> PEAR_FRUIT = ITEMS.register("pear_fruit", () -> new BaseFoodItem(new Item.Properties().group(FoodPlusGroup.INSTANCE), BaseFoodItem.FRUIT_FOOD));
    public static final RegistryObject<Item> CHERRY_FRUIT = ITEMS.register("cherry_fruit", () -> new BaseFoodItem(new Item.Properties().group(FoodPlusGroup.INSTANCE), BaseFoodItem.FRUIT_FOOD));
    public static final RegistryObject<Item> MANGO_FRUIT = ITEMS.register("mango_fruit", () -> new BaseFoodItem(new Item.Properties().group(FoodPlusGroup.INSTANCE), BaseFoodItem.FRUIT_FOOD));
    public static final RegistryObject<Item> BANANA_FRUIT = ITEMS.register("banana_fruit", () -> new BaseFoodItem(new Item.Properties().group(FoodPlusGroup.INSTANCE), BaseFoodItem.FRUIT_FOOD));

    //TOOLS:
    public static final RegistryObject<Item> HARVEST_TOOL = ITEMS.register("harvest_tool", () -> new HarvesterToolItem(new Item.Properties().group(FoodPlusGroup.INSTANCE).maxStackSize(1)));
    public static final RegistryObject<Item> MJOLNIR = ITEMS.register("mjolnir", () -> new MjolnirItem(new Item.Properties().group(FoodPlusGroup.INSTANCE)));

    //BLOCK ITEMS
    public static final RegistryObject<Item> HOP_SEED = ITEMS.register("hop_seed", () -> new BaseBlockItem(ModBlocks.HOP_CROP.get(), new Item.Properties()));

    //Kitchen Util:
    public static final RegistryObject<Item> SALT = ITEMS.register("salt", () -> new SaltItem(new Item.Properties().group(FoodPlusGroup.INSTANCE)));

    public static NonNullList<Item> getFruits() {
        NonNullList<Item> fruits = NonNullList.create();
        fruits.add(PEAR_FRUIT.get());
        fruits.add(CHERRY_FRUIT.get());
        fruits.add(MANGO_FRUIT.get());
        return fruits;
    }


}