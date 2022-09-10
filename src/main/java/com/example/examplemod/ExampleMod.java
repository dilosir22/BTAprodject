package com.example.examplemod;

import bta.Mod;
import com.example.examplemod.util.LivingEntityHelper;
import net.minecraft.client.Minecraft;
import com.example.examplemod.mobs.EntityForestSpider;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntitySpider;
import net.minecraft.src.SpawnListEntry;

public class ExampleMod implements Mod {

    @Override
    public void init(Minecraft minecraft) {
            LivingEntityHelper.addMonster( new SpawnListEntry(EntityForestSpider.class, 100), BiomeGenBase.seasonalForest.getClass());
            LivingEntityHelper.addMonster( new SpawnListEntry(EntitySpider.class, 100), BiomeGenBase.seasonalForest.getClass());
        System.out.println("[Spiders] should be Initialized.");
    }

    @Override
    public void update() {

    }

    @Override
    public void tick() {

    }
}
