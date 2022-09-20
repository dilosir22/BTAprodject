package com.example.examplemod;

import bta.Mod;
import com.example.examplemod.mobs.RenderForestSpider;
import com.example.examplemod.util.LivingEntityHelper;
import net.minecraft.client.Minecraft;
import com.example.examplemod.mobs.EntityForestSpider;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.RenderSpider;
import net.minecraft.src.SpawnListEntry;

public class ExampleMod implements Mod {

    @Override
    public void init(Minecraft minecraft) {

        LivingEntityHelper.addMonster( new SpawnListEntry(EntityForestSpider.class, 10), BiomeGenBase.seasonalForest);
        LivingEntityHelper.addEntityRenderMapping(EntityForestSpider.class, new RenderForestSpider());
        LivingEntityHelper.registerEntity(EntityForestSpider.class, "ForestSpider", 120);
        System.out.println("[Spiders] should be Initialized.");

    }

    @Override
    public void update() {

    }

    @Override
    public void tick() {

    }
}
