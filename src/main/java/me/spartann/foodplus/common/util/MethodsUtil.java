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


}
