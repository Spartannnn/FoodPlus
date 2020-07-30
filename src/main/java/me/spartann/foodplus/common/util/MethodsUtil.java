package me.spartann.foodplus.common.util;

import net.minecraft.util.NonNullList;

import java.util.Arrays;

public class MethodsUtil {

    public static <T> NonNullList<T> createNNList(T... types) {
        NonNullList<T> list = NonNullList.create();
        list.addAll(Arrays.asList(types));
        return list;
    }

}
