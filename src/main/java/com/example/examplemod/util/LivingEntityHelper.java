package com.example.examplemod.util;

import net.minecraft.src.*;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;

public class LivingEntityHelper {

    static Field ERenderMap;
    static Method addMappingMethod;


    static {
        try{
            Field[] feilds = RenderManager.class.getDeclaredFields();
            for(Field feild : feilds) {
                if(feild.getName().equals("entityRenderMap")) {
                    ERenderMap = feild;
                }

            }
            if(ERenderMap == null) throw new RuntimeException("Could not find entityRenderMap feild!");
            ERenderMap.setAccessible(true);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            Method[] methods = EntityList.class.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals("addMapping")) {
                    if (method.getParameterCount() == 3) {
                        addMappingMethod = method;
                    }
                }
            }
            if (addMappingMethod == null) throw new RuntimeException("Could not find (EntityList) addMapping method!");
            addMappingMethod.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addMonster(SpawnListEntry monster, BiomeGenBase biome){
        biome.getSpawnableList(EnumCreatureType.monster).add(monster);
    }

    @SuppressWarnings("unchecked")
    public static void addEntityRenderMapping(Class entity, Object renderer){
        try {
            if(ERenderMap.get(RenderManager.instance) instanceof HashMap) {
                ((HashMap) ERenderMap.get(RenderManager.instance)).put(entity, renderer);
            }else{
                System.out.println(entity.getName() + "'s renderer could not be added to rendermanager");
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerEntity(Class<? extends Entity> class1, String s, int i) {
        try {
            addMappingMethod.invoke(null, class1, s, i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
