package com.example.examplemod.util;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.BiomeGenForest;
import net.minecraft.src.SpawnListEntry;

import java.lang.reflect.*;
import java.util.List;
import java.util.ArrayList;

public class LivingEntityHelper {

   static Field monsterList;

    static {
        try{
            Field[] feilds = BiomeGenBase.class.getDeclaredFields();
            for(Field feild : feilds) {
                    if(feild.getName().equals("spawnableMonsterList")) {
                        monsterList = feild;
                    }

            }
            if(monsterList == null) throw new RuntimeException("Could not find spawnableMonsterList feild!");
            monsterList.setAccessible(true);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    public static void addMonster(SpawnListEntry monster, BiomeGenBase biome){
        try {
                if(monsterList.get((biome)) instanceof ArrayList){
                    ((ArrayList) monsterList.get((biome))).add(monster);
                }else{
                    System.out.println(monster.entityClass.getName() + " could not be added to "+biome.biomeName);
                }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
