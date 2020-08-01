package me.spartann.foodplus.common.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MethodsUtil {

    public static <T> NonNullList<T> createNNList(T... types) {
        NonNullList<T> list = NonNullList.create();
        list.addAll(Arrays.asList(types));
        return list;
    }

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


    public static <T> void arrayBiConsumer(T[] array, BiConsumer<Integer, T> biConsumer) {
        for(int i = 0; i < array.length; i++)
            biConsumer.accept(i, array[i]);
    }

}
