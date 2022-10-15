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
    public ModelRenderer moaTail;

    public ModelMoa()
    {

        moaHead = new ModelRenderer(0, 0);
        moaHead.addBox(-3F, -9F, -13F, 6, 9, 13);
        moaHead.setRotationPoint(0F, -16F, -5F);
        moaHead.mirror = true;
        moaBody = new ModelRenderer( 0, 0);
        moaBody.addBox(-6F, -11F, -8F, 12, 12, 16);
        moaBody.setRotationPoint(0F, 4F, 2F);
        moaBody.mirror = true;
        moaNeck = new ModelRenderer( 0, 0);
        moaNeck.addBox(-2F, -16F, -3F, 4, 16, 4);
        moaNeck.setRotationPoint(0F, -4F, -5F);
        moaNeck.mirror = true;
        moaLegR = new ModelRenderer( 0, 0);
        moaLegR.addBox(-2F, 0F, -2F, 4, 20, 4);
        moaLegR.setRotationPoint(3F, 4F, 2F);
        moaLegR.mirror = true;
        moaLegL = new ModelRenderer( 0, 0);
        moaLegL.addBox(-2F, 0F, -2F, 4, 20, 4);
        moaLegL.setRotationPoint(-3F, 4F, 2F);
        moaLegL.mirror = true;
        moaTail = new ModelRenderer( 0, 0);
        moaTail.addBox(-5F, 0F, 0F, 10, 11, 0);
        moaTail.setRotationPoint(0F, -5F, 10F);
        moaTail.mirror = true;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.moaHead.render(f5);
        this.moaNeck.render(f5);
        this.moaBody.render(f5);
        this.moaLegR.render(f5);
        this.moaLegL.render(f5);
        this.moaTail.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {

        this.moaTail.rotateAngleX = -30.0f;
        this.moaHead.rotateAngleY = f3 / 70.0F;
        this.moaHead.rotateAngleX = f4 / 70.0F;
        this.moaLegR.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.9F * f1;
        this.moaLegL.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.9F * f1;
    }
}