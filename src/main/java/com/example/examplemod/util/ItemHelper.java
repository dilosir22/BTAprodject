package com.example.examplemod.util;

import net.minecraft.src.EntityList;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.RenderManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ItemHelper {
    public static Method getInventorySlotContainItem;

    static {
        try {
            Method[] methods = InventoryPlayer.class.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals("getInventorySlotContainItem")) {
                    if (method.getParameterCount() == 1) {
                        getInventorySlotContainItem = method;
                    }
                }
            }
            if (getInventorySlotContainItem == null) throw new RuntimeException("Could not find (EntityList) addMapping method!");
            getInventorySlotContainItem.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
