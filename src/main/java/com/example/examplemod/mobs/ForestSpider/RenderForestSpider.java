package com.example.examplemod.mobs.ForestSpider;

import com.example.examplemod.mobs.ForestSpider.ModelForestSpider;
import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntitySpider;
import net.minecraft.src.RenderLiving;

public class RenderForestSpider extends RenderLiving {
    public RenderForestSpider() {
        super(new ModelForestSpider(), 1.0F);
        this.setRenderPassModel(new ModelForestSpider());
    }

    protected float setSpiderDeathMaxRotation(EntitySpider entityspider) {
        return 0.0F;
    }

    protected boolean setSpiderEyeBrightness(EntitySpider entityspider, int i, float f) {
        if (i != 0) {
            return false;
        } else if (i != 0) {
            return false;
        } else {
            this.loadTexture("/mob/spider_eyes.png");
            float brightness = entityspider.getEntityBrightness(1.0F);
            if (Minecraft.getMinecraft().fullbright) {
                brightness = 1.0F;
            }

            float f1 = (1.0F - brightness) * 0.5F;
            /*
            GL11.glEnable(3042);
            GL11.glDisable(3008);
            GL11.glBlendFunc(770, 771);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
            */
            return true;
        }
    }

    protected float getDeathMaxRotation(EntityLiving entityliving) {
        return this.setSpiderDeathMaxRotation((EntitySpider)entityliving);
    }

    protected boolean shouldRenderPass(EntityLiving entityliving, int i, float f) {
        return this.setSpiderEyeBrightness((EntitySpider)entityliving, i, f);
    }
}