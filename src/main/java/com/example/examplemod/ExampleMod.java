package com.example.examplemod;

import bta.Mod;
import com.example.examplemod.items.ItemTieredBow;
import com.example.examplemod.mobs.ForestSpider.RenderForestSpider;
import com.example.examplemod.mobs.MiniSpider.EntityMiniSpider;
import com.example.examplemod.mobs.Moa.EntityMoa;
import com.example.examplemod.mobs.Moa.RenderMoa;
import com.example.examplemod.util.LivingEntityHelper;
import net.minecraft.client.Minecraft;
import com.example.examplemod.mobs.ForestSpider.EntityForestSpider;
import net.minecraft.src.*;

public class ExampleMod implements Mod {

    @Override
    public void init(Minecraft minecraft) {
        Item.toolBow = (new ItemTieredBow(5, 12, 20)).setIconCoord(0, 14).setItemName("tool.bow");

        LivingEntityHelper.addMonster( new SpawnListEntry(EntityForestSpider.class, 10), BiomeGenBase.seasonalForest);
        LivingEntityHelper.addEntityRenderMapping(EntityForestSpider.class, new RenderForestSpider());
        LivingEntityHelper.registerEntity(EntityForestSpider.class, "ForestSpider", 120);
        LivingEntityHelper.addEntityRenderMapping(EntityMiniSpider.class, new RenderSpider());
        LivingEntityHelper.registerEntity(EntityMiniSpider.class, "MiniSpider", 121);
        LivingEntityHelper.addToAllBiomes(new SpawnListEntry(EntityMoa.class, 10), EnumCreatureType.creature);
        LivingEntityHelper.addEntityRenderMapping(EntityMoa.class, new RenderMoa());
        LivingEntityHelper.registerEntity(EntityMoa.class, "Moa", 122);

        System.out.println("[Spiders] should be Initialized.");

    }

    @Override
    public void update() {

    }

    @Override
    public void tick() {

    }
}
