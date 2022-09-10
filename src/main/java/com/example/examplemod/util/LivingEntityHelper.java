package com.example.examplemod.util;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.BiomeGenForest;
import net.minecraft.src.SpawnListEntry;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.util.List;

public class LivingEntityHelper {

   static Field monsterList;

    static {
        try{
            Field[] feilds = BiomeGenBase.class.getDeclaredFields();
            for(Field feild : feilds) {
                    if(feild.toString().equals("spawnableMonsterList")) {
                        monsterList = feild;
                    }
            }
            if(monsterList == null) throw new RuntimeException("Could not find spawnableMonsterList feild!");
            monsterList.setAccessible(true);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addMonster(SpawnListEntry monster, Object biome){
        try {
            ((List<SpawnListEntry>) monsterList.get(biome)).add(monster);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
