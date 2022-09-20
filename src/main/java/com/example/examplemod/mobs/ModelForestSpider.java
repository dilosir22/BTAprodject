package com.example.examplemod.mobs;

import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelForestSpider extends ModelBase {
    public ModelRenderer spiderHead;
    public ModelRenderer spiderNeck;
    public ModelRenderer spiderBody;
    public ModelRenderer spiderLeg1;
    public ModelRenderer spiderLeg2;
    public ModelRenderer spiderLeg3;
    public ModelRenderer spiderLeg4;
    public ModelRenderer spiderLeg5;
    public ModelRenderer spiderLeg6;
    public ModelRenderer spiderLeg7;
    public ModelRenderer spiderLeg8;

    public ModelForestSpider() {
        float f = 0.0F;
        int i = 15;
        this.spiderHead = new ModelRenderer(32, 4);
        this.spiderHead.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, f);
        this.spiderHead.setRotationPoint(0.0F, (float)(0 + i), -3.0F);
        this.spiderNeck = new ModelRenderer(0, 0);
        this.spiderNeck.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, f);
        this.spiderNeck.setRotationPoint(0.0F, (float)i, 0.0F);
        this.spiderBody = new ModelRenderer(0, 12);
        this.spiderBody.addBox(-5.0F, -4.0F, -6.0F, 10, 24, 12, f);
        this.spiderBody.setRotationPoint(0.0F, (float)(0 + i), 9.0F);
        this.spiderLeg1 = new ModelRenderer(18, 0);
        this.spiderLeg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, f);
        this.spiderLeg1.setRotationPoint(-4.0F, (float)(0 + i), 2.0F);
        this.spiderLeg2 = new ModelRenderer(18, 0);
        this.spiderLeg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, f);
        this.spiderLeg2.setRotationPoint(4.0F, (float)(0 + i), 2.0F);
        this.spiderLeg3 = new ModelRenderer(18, 0);
        this.spiderLeg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, f);
        this.spiderLeg3.setRotationPoint(-4.0F, (float)(0 + i), 1.0F);
        this.spiderLeg4 = new ModelRenderer(18, 0);
        this.spiderLeg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, f);
        this.spiderLeg4.setRotationPoint(4.0F, (float)(0 + i), 1.0F);
        this.spiderLeg5 = new ModelRenderer(18, 0);
        this.spiderLeg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, f);
        this.spiderLeg5.setRotationPoint(-4.0F, (float)(0 + i), 0.0F);
        this.spiderLeg6 = new ModelRenderer(18, 0);
        this.spiderLeg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, f);
        this.spiderLeg6.setRotationPoint(4.0F, (float)(0 + i), 0.0F);
        this.spiderLeg7 = new ModelRenderer(18, 0);
        this.spiderLeg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, f);
        this.spiderLeg7.setRotationPoint(-4.0F, (float)(0 + i), -1.0F);
        this.spiderLeg8 = new ModelRenderer(18, 0);
        this.spiderLeg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, f);
        this.spiderLeg8.setRotationPoint(4.0F, (float)(0 + i), -1.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.spiderHead.render(f5);
        this.spiderNeck.render(f5);
        this.spiderBody.render(f5);
        this.spiderLeg1.render(f5);
        this.spiderLeg2.render(f5);
        this.spiderLeg3.render(f5);
        this.spiderLeg4.render(f5);
        this.spiderLeg5.render(f5);
        this.spiderLeg6.render(f5);
        this.spiderLeg7.render(f5);
        this.spiderLeg8.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.spiderHead.rotateAngleY = f3 / 57.29578F;
        this.spiderHead.rotateAngleX = f4 / 57.29578F;
        float f6 = 0.7853982F;
        this.spiderLeg1.rotateAngleZ = -f6;
        this.spiderLeg2.rotateAngleZ = f6;
        this.spiderLeg3.rotateAngleZ = -f6 * 0.74F;
        this.spiderLeg4.rotateAngleZ = f6 * 0.74F;
        this.spiderLeg5.rotateAngleZ = -f6 * 0.74F;
        this.spiderLeg6.rotateAngleZ = f6 * 0.74F;
        this.spiderLeg7.rotateAngleZ = -f6;
        this.spiderLeg8.rotateAngleZ = f6;
        float f7 = -0.0F;
        float f8 = 0.3926991F;
        this.spiderLeg1.rotateAngleY = f8 * 2.0F + f7;
        this.spiderLeg2.rotateAngleY = -f8 * 2.0F - f7;
        this.spiderLeg3.rotateAngleY = f8 * 1.0F + f7;
        this.spiderLeg4.rotateAngleY = -f8 * 1.0F - f7;
        this.spiderLeg5.rotateAngleY = -f8 * 1.0F + f7;
        this.spiderLeg6.rotateAngleY = f8 * 1.0F - f7;
        this.spiderLeg7.rotateAngleY = -f8 * 2.0F + f7;
        this.spiderLeg8.rotateAngleY = f8 * 2.0F - f7;
        float f9 = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
        float f10 = -(MathHelper.cos(f * 0.6662F * 2.0F + 3.141593F) * 0.4F) * f1;
        float f11 = -(MathHelper.cos(f * 0.6662F * 2.0F + 1.570796F) * 0.4F) * f1;
        float f12 = -(MathHelper.cos(f * 0.6662F * 2.0F + 4.712389F) * 0.4F) * f1;
        float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
        float f14 = Math.abs(MathHelper.sin(f * 0.6662F + 3.141593F) * 0.4F) * f1;
        float f15 = Math.abs(MathHelper.sin(f * 0.6662F + 1.570796F) * 0.4F) * f1;
        float f16 = Math.abs(MathHelper.sin(f * 0.6662F + 4.712389F) * 0.4F) * f1;
        ModelRenderer var10000 = this.spiderLeg1;
        var10000.rotateAngleY += f9;
        var10000 = this.spiderLeg2;
        var10000.rotateAngleY += -f9;
        var10000 = this.spiderLeg3;
        var10000.rotateAngleY += f10;
        var10000 = this.spiderLeg4;
        var10000.rotateAngleY += -f10;
        var10000 = this.spiderLeg5;
        var10000.rotateAngleY += f11;
        var10000 = this.spiderLeg6;
        var10000.rotateAngleY += -f11;
        var10000 = this.spiderLeg7;
        var10000.rotateAngleY += f12;
        var10000 = this.spiderLeg8;
        var10000.rotateAngleY += -f12;
        var10000 = this.spiderLeg1;
        var10000.rotateAngleZ += f13;
        var10000 = this.spiderLeg2;
        var10000.rotateAngleZ += -f13;
        var10000 = this.spiderLeg3;
        var10000.rotateAngleZ += f14;
        var10000 = this.spiderLeg4;
        var10000.rotateAngleZ += -f14;
        var10000 = this.spiderLeg5;
        var10000.rotateAngleZ += f15;
        var10000 = this.spiderLeg6;
        var10000.rotateAngleZ += -f15;
        var10000 = this.spiderLeg7;
        var10000.rotateAngleZ += f16;
        var10000 = this.spiderLeg8;
        var10000.rotateAngleZ += -f16;
    }
}
