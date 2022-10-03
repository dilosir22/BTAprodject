package com.example.examplemod.mobs.Moa;

import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelMoa extends ModelBase {
    public ModelRenderer moaHead;
    public ModelRenderer moaNeck;
    public ModelRenderer moaBody;
    public ModelRenderer moaLegR;
    public ModelRenderer moaLegL;

    public ModelMoa() {
        float f = 0.0F;
        int i = 15;
        this.moaHead = new ModelRenderer(32, 4);
        this.moaHead.addBox(-4.0F, -33.0F, -13.0F, 8, 9, 12, f);
        this.moaHead.setRotationPoint(0.0F, (float)(0 + i), -4.0F);
        this.moaNeck = new ModelRenderer(0, 0);
        this.moaNeck.addBox(-2.0F, -30.0F, -8.0F, 4, 12, 4, f);
        this.moaNeck.setRotationPoint(0.0F, (float)i, 0.0F);
        this.moaBody = new ModelRenderer(0, 12);
        this.moaBody.addBox(-8.0F, -20.0F, -16.0F, 16, 16, 16, f);
        this.moaBody.setRotationPoint(0.0F, (float)(0 + i), 9.0F);
        this.moaLegR = new ModelRenderer(18, 0);
        this.moaLegR.addBox(7.0f, -6.0F, 0.0F, 4, 16, 4, f);
        this.moaLegR.setRotationPoint(-4.0F, (float)(0 + i), 2.0F);
        this.moaLegL = new ModelRenderer(18, 0);
        this.moaLegL.addBox(-11.0F, -6.0F, 0.0F, 4, 16, 4, f);
        this.moaLegL.setRotationPoint(4.0F, (float)(0 + i), 2.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.moaHead.render(f5);
        this.moaNeck.render(f5);
        this.moaBody.render(f5);
        this.moaLegR.render(f5);
        this.moaLegL.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.moaHead.rotateAngleY = f3 / 60.0F;
        this.moaHead.rotateAngleX = f4 / 60.0F;
//        float f6 = 0.0f;
//        this.moaLegR.rotateAngleY = -f6;
//        this.moaLegL.rotateAngleY = f6;
        this.moaLegR.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.9F * f1;
        this.moaLegL.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.9F * f1;
    }
}