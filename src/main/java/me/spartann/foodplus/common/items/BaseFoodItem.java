package me.spartann.foodplus.common.items;

import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;

public class BaseFoodItem extends Item {

    public static final Food FRUIT_FOOD = build(4, 1.2F);

    public BaseFoodItem(Properties properties, Food food) {
        super(properties.food(food));
    }

    public BaseFoodItem(Properties properties) {
        this(properties, Foods.COOKED_BEEF);
    }

    public static Food build(int hunger, float saturation, boolean meat, boolean alwaysEdible, boolean fastEat) {
        Food.Builder builder = new Food.Builder();
        builder.hunger(hunger);
        builder.saturation(saturation);
        if (meat)
            builder.meat();
        if (alwaysEdible)
            builder.setAlwaysEdible();
        if (fastEat)
            builder.fastToEat();
        return builder.build();
    }

    public static Food build(int hunger, float saturation) {
        return build(hunger, saturation, false, false, false);
    }


}
