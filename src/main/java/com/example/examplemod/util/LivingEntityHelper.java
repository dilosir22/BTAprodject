package com.example.examplemod.util;

import net.minecraft.src.*;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class LivingEntityHelper {

    static Field ERenderMap;
    static Method addMappingMethod;
    public static Field LEmoveStrafing;
    public static Field LEmoveForward;
    public static Field LElookYaw;
    public static Field LElookPitch;
    public static Field LEisJumping;


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
        try{
            Field[] feilds = EntityLiving.class.getDeclaredFields();
            for(Field feild : feilds) {
                if(feild.getName().equals("moveStrafing")) {
                    LEmoveStrafing = feild;
                }else if(feild.getName().equals("moveForward")) {
                    LEmoveForward = feild;
                }else if(feild.getName().equals("lookYaw")) {
                    LElookYaw = feild;
                }else if(feild.getName().equals("lookPitch")) {
                    LElookPitch = feild;
                }else if(feild.getName().equals("isJumping")) {
                    LEisJumping = feild;
                }

            }
            if(LEmoveStrafing == null) throw new RuntimeException("Could not find LEmoveStrafing feild!");
            LEmoveStrafing.setAccessible(true);
            if(LEmoveForward == null) throw new RuntimeException("Could not find LEmoveForward feild!");
            LEmoveForward.setAccessible(true);
            if(LElookYaw == null) throw new RuntimeException("Could not find LElookYaw feild!");
            LElookYaw.setAccessible(true);
            if(LElookPitch == null) throw new RuntimeException("Could not find LElookPitch feild!");
            LElookPitch.setAccessible(true);
            if(LEisJumping == null) throw new RuntimeException("Could not find LEisJumping feild!");
            LEisJumping.setAccessible(true);

        }catch(Exception e) {
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
    @SuppressWarnings("unchecked")
    public static float getForward(Entity entity){
        try{
            return (float) LEmoveForward.get(entity);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    public static float getStrafing(Entity entity){
        try{
            return (float) LEmoveStrafing.get(entity);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    public static boolean getJumping(Entity entity){
        try{
            return (boolean) LEisJumping.get(entity);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    public static float getLookYaw(Entity entity){
        try{
            return (float) LElookYaw.get(entity);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    public static float getLookPitch(Entity entity){
        try{
            return (float) LElookPitch.get(entity);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

