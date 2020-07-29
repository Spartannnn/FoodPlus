package me.spartann.foodplus.common.registries;

import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.group.FoodPlusGroup;
import me.spartann.foodplus.common.items.BaseBlockItem;
import me.spartann.foodplus.common.items.BaseFoodItem;
import me.spartann.foodplus.common.items.HarvesterToolItem;
import me.spartann.foodplus.common.items.SaltItem;
import me.spartann.foodplus.common.items.juicer.juice.JuiceBottleFlavour;
import me.spartann.foodplus.common.items.juicer.juice.JuiceBottleFullness;
import me.spartann.foodplus.common.items.juicer.juice.JuiceBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, FoodPlusMod.MOD_ID);

    //FOOD:
    /**
     * Do not use this variable. Use {@link ModItems#getJuiceItem(JuiceBottleFlavour)}
     */
    public static final RegistryObject<Item> JUICE = ITEMS.register("juice", () -> new JuiceBottleItem(new Item.Properties().group(FoodPlusGroup.INSTANCE)));


    //FRUITS:
    public static final RegistryObject<Item> PEAR_FRUIT = ITEMS.register("pear_fruit", () -> new BaseFoodItem(new Item.Properties().group(FoodPlusGroup.INSTANCE), BaseFoodItem.FRUIT_FOOD));
    public static final RegistryObject<Item> CHERRY_FRUIT = ITEMS.register("cherry_fruit", () -> new BaseFoodItem(new Item.Properties().group(FoodPlusGroup.INSTANCE), BaseFoodItem.FRUIT_FOOD));
    public static final RegistryObject<Item> MANGO_FRUIT = ITEMS.register("mango_fruit", () -> new BaseFoodItem(new Item.Properties().group(FoodPlusGroup.INSTANCE), BaseFoodItem.FRUIT_FOOD));

    //TOOLS:
    public static final RegistryObject<Item> HARVEST_TOOL = ITEMS.register("harvest_tool", () -> new HarvesterToolItem(new Item.Properties().group(FoodPlusGroup.INSTANCE).maxStackSize(1)));

    //BLOCK ITEMS
    public static final RegistryObject<Item> PEAR_LOG = ITEMS.register("pear_log", () -> new BaseBlockItem(ModBlocks.PEAR_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> PEAR_LEAVES = ITEMS.register("pear_leaves", () -> new BaseBlockItem(ModBlocks.PEAR_LEAVES.get(), new Item.Properties()));
    public static final RegistryObject<Item> PEAR_SAPLING = ITEMS.register("pear_sapling", () -> new BaseBlockItem(ModBlocks.PEAR_SAPLING.get(), new Item.Properties()));
    public static final RegistryObject<Item> SALT_ORE = ITEMS.register("salt_ore", () -> new BaseBlockItem(ModBlocks.SALT_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> HOP_SEED = ITEMS.register("hop_seed", () -> new BaseBlockItem(ModBlocks.HOP_CROP.get(), new Item.Properties()));

    //Kitchen Util:
    public static final RegistryObject<Item> JUICER = ITEMS.register("juicer", () -> new BaseBlockItem(ModBlocks.JUICER.get(), new Item.Properties()));
    public static final RegistryObject<Item> SALT = ITEMS.register("salt", () -> new SaltItem(new Item.Properties().group(FoodPlusGroup.INSTANCE)));

    public static ItemStack getJuiceItemStack(JuiceBottleFlavour color) {
        Item item = JUICE.get();
        ItemStack stack = new ItemStack(item);
        JuiceBottleItem.setFlavour(stack, color);
        JuiceBottleItem.setFullness(stack, JuiceBottleFullness.FULL);
        return stack;
    }

    public static Item getJuiceItem(JuiceBottleFlavour color) {
        return getJuiceItemStack(color).getItem();
    }

    public static NonNullList<Item> getFruits() {
        NonNullList<Item> fruits = NonNullList.withSize(3, Items.AIR);
        fruits.set(0, PEAR_FRUIT.get());
        fruits.set(1, CHERRY_FRUIT.get());
        fruits.set(2, MANGO_FRUIT.get());
        return fruits;
    }


}
