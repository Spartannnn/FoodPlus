package com.spartann.foodplus.common.util;

import com.google.common.collect.Lists;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.Validate;

import java.util.List;

public class MethodsUtil {

    @SafeVarargs
    public static <T> boolean containsEmptyElement(T... array) {
        boolean flag;
        for (T t : array) {
            if (t instanceof ItemStack) {
                ItemStack stack = (ItemStack) t;
                flag = stack.isEmpty();
            } else {
                flag = t == null;
            }
            if (flag)
                return true;
        }
        return false;
    }

    public static <T> boolean containsInArray(T value, T... array) {
        for(T t : array) {
            if(t instanceof ItemStack) {
                ItemStack stack = (ItemStack) t;
                if(stack.isItemEqual((ItemStack) value))
                    return true;
            } else {
                if(t.equals(value))
                    return true;
            }
        }
        return false;
    }

    public static List<ItemStack> convertItemsIntoStacks(List<Item> items) {
        Validate.notNull(items, "Items are null");
        List<ItemStack> res = Lists.newArrayList();
        for(Item item : items)
            res.add(item.getDefaultInstance());
        return res;
    }

}
