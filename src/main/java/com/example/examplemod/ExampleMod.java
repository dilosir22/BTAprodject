package com.example.examplemod;

import bta.Mod;
import com.example.examplemod.mobs.RenderForestSpider;
import com.example.examplemod.util.LivingEntityHelper;
import net.minecraft.client.Minecraft;
import com.example.examplemod.mobs.EntityForestSpider;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.RenderSpider;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.helper.Textures;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExampleMod implements Mod {

    @Override
    public void init(Minecraft minecraft) {
        /*try {
            minecraft.renderEngine.allocateAndSetupTexture(Textures.readImage(new FileInputStream("java/com/example/examplemod/mobs/forestspider.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Texture not found");
            throw new RuntimeException(e);
        }*/
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
