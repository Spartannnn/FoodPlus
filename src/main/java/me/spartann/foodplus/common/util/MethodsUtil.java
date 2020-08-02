package me.spartann.foodplus.common.util;

import net.minecraft.item.ItemStack;

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

}
