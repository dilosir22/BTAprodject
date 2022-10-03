package com.example.examplemod.mobs.Moa;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.RenderLiving;

public class RenderMoa extends RenderLiving {
    public RenderMoa() {
        super(new ModelMoa(), 1.0F);
        this.setRenderPassModel(new ModelMoa());
    }

    protected float setMoaDeathMaxRotation(EntityMoa entityMoa) {
        return 90.0F;
    }



    protected float getDeathMaxRotation(EntityLiving entityliving) {
        return this.setMoaDeathMaxRotation((EntityMoa)entityliving);
    }

}