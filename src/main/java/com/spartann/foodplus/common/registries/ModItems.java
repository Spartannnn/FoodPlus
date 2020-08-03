package com.spartann.foodplus.common.registries;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.food.FoodList;
import com.spartann.foodplus.common.group.FoodPlusGroup;
import com.spartann.foodplus.common.items.*;
import com.spartann.foodplus.common.items.juice.ItemJuiceBottle;
import net.minecraft.block.Block;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Supplier;


public class ModItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, FoodPlusMod.MOD_ID);

    //FOOD:
    public static final RegistryObject<Item> JUICE = ITEMS.register("juice", () -> new ItemJuiceBottle(new Item.Properties().group(FoodPlusGroup.INSTANCE).maxStackSize(12)));
    public static final RegistryObject<Item> BEER = register("beer", () -> new ItemBeer(new Item.Properties().group(FoodPlusGroup.INSTANCE).food(FoodList.BEER).maxStackSize(16)));
    public static final RegistryObject<Item> NUGGETS = registerFood("nuggets", FoodList.NUGGETS);
    public static final RegistryObject<Item> SUSHI = registerFood("sushi", FoodList.SUSHI);
    public static final RegistryObject<Item> FRIES = registerFood("fries", FoodList.FRIES);
    public static final RegistryObject<Item> CHOCOLATE = registerFood("chocolate", FoodList.CHOCOLATE);
    public static final RegistryObject<Item> SANDWICH = registerFood("sandwich", FoodList.SANDWICH);
    public static final RegistryObject<Item> CHEESE = registerFood("cheese", FoodList.CHEESE);
    public static final RegistryObject<Item> SALAMI = registerFood("salami", FoodList.SALAMI);
    public static final RegistryObject<Item> HAM = registerFood("ham", FoodList.HAM);

    //FRUITS:
    public static final RegistryObject<Item> PEAR_FRUIT = registerFruit("pear_fruit");
    public static final RegistryObject<Item> CHERRY_FRUIT = registerFruit("cherry_fruit");
    public static final RegistryObject<Item> MANGO_FRUIT = registerFruit("mango_fruit");
    public static final RegistryObject<Item> BANANA_FRUIT = registerFruit("banana_fruit");

    //TOOLS:
    public static final RegistryObject<Item> HARVEST_TOOL = register("harvest_tool", () -> new ItemHarvesterTool(new Item.Properties().group(FoodPlusGroup.INSTANCE).maxStackSize(1)));
    public static final RegistryObject<Item> MJOLNIR = register("mjolnir", () -> new ItemMjolnir(new Item.Properties().group(FoodPlusGroup.INSTANCE).maxStackSize(1)));

    //BLOCK ITEMS
    public static final RegistryObject<Item> HOP_SEED = registerBlockItem("hop_seed", ModBlocks.HOP_CROP);

    //Kitchen Util:
    public static final RegistryObject<Item> SALT = registerItem("salt");
    public static final RegistryObject<Item> HOP = registerItem("hop");
    public static final RegistryObject<Item> MALT = registerItem("malt");

    private static RegistryObject<Item> register(String name, Supplier<? extends Item> supplier) {
        return ITEMS.register(name, supplier);
    }

    private static RegistryObject<Item> registerFood(String name, Food food) {
        return ITEMS.register(name, () -> new ItemBaseFood(new Item.Properties().group(FoodPlusGroup.INSTANCE), food));
    }

    private static RegistryObject<Item> registerFruit(String name) {
        return registerFood(name, FoodList.FRUIT_FOOD);
    }

    private static RegistryObject<Item> registerItem(String name, List<ITextComponent> description) {
        return ITEMS.register(name, () -> new ItemFoodPlus(new Item.Properties().group(FoodPlusGroup.INSTANCE), () -> description));
    }

    private static RegistryObject<Item> registerEffectItem(String name, Food food, EffectInstance effect) {
        return ITEMS.register(name, () -> new ItemApplyEffect(new Item.Properties().group(FoodPlusGroup.INSTANCE).maxStackSize(4).rarity(Rarity.EPIC), food, () -> effect));
    }

    private static RegistryObject<Item> registerBlockItem(String name, RegistryObject<Block> block) {
        return ITEMS.register(name, () -> new ItemBaseBlock(block.get(), new Item.Properties()));
    }

    private static RegistryObject<Item> registerItem(String name) {
        return registerItem(name, null);
    }

}
