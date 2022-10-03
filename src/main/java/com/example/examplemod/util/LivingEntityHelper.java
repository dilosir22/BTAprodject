package com.example.examplemod.util;

import net.minecraft.src.*;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
    public static void addCreature(SpawnListEntry creature, BiomeGenBase biome){
        biome.getSpawnableList(EnumCreatureType.creature).add(creature);
    }

    @SuppressWarnings("unchecked")
    public static void addEntityRenderMapping(Class entity, Object renderer){
        try {
            if(ERenderMap.get(RenderManager.instance) instanceof HashMap) {
                ((HashMap) ERenderMap.get(RenderManager.instance)).put(entity, renderer);


                Iterator iterator = ((HashMap) ERenderMap.get(RenderManager.instance)).values().iterator();
                while(iterator.hasNext()) {
                    Render render = (Render)iterator.next();
                    render.setRenderManager(RenderManager.instance);
                }
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
//
//    public static Entity getClosestEntityToEntity(World worldObj, Entity entity, double d, Class<? extends Entity> type){
//        return locateNearestEntity(worldObj, entity.posX, entity.posY, entity.posZ, d, type);
//    }
//
//    public static Entity locateNearestEntity(World worldObj, double d, double d1, double d2, double d3, Class<? extends Entity> typ){
//        double d4 = -1.0;
//        Entity entity = null;
//
//        for(int i = 0; i < worldObj.loadedEntityList.size(); ++i) {
//            Entity mob1 = worldObj.loadedEntityList.get(i);
//            double d5 = mob1.getDistanceSq(d, d1, d2);
//            if ((d3 < 0.0 || d5 < d3 * d3) && (d4 == -1.0 || d5 < d4)) {
//                d4 = d5;
//                if(mob1.getClass().isInstance(typ)){
//                    entity = mob1;
//                }
//            }
//        }
//
//        return entity;
//    }

}
