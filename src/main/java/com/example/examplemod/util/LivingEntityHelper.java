package com.example.examplemod.util;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.BiomeGenForest;
import net.minecraft.src.SpawnListEntry;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.util.List;

public class LivingEntityHelper {

  //  public static List<SpawnListEntry> monsterList;
   static Field thefield;

    static {
        try{
            Field[] feilds = BiomeGenBase.forest.getClass().getDeclaredFields();
            for(Field feild : feilds) {
                    if(feild.toString().equals("spawnableMonsterList")) {
                        thefield = feild;
                    }
            }
            if(thefield == null) throw new RuntimeException("Could not find monsterlist feild!");
            thefield.setAccessible(true);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addMonster(SpawnListEntry monster){
        try {
            ((List<SpawnListEntry>) thefield.get(BiomeGenBase.seasonalForest.getClass())).add(monster);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
