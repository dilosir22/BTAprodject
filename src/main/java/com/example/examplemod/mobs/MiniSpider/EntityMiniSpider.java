package com.example.examplemod.mobs.MiniSpider;

import com.example.examplemod.mobs.ForestSpider.EntityForestSpider;
import net.minecraft.src.Entity;
import net.minecraft.src.EntitySpider;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import net.minecraft.src.helper.DamageType;

public class EntityMiniSpider extends EntitySpider {
    private Entity Target = null;
    private EntityForestSpider mother = null;
    public EntityMiniSpider(World world) {
        super(world);
        this.setSize(1.2F, 2.3F);//change later
        this.moveSpeed = 0.95f;
        this.health = 4;
        this.texture = "/mob/forestspider.png";
        this.attackStrength = 1;
    }
    public EntityMiniSpider(World world, EntityForestSpider mom) {
        this(world);
        Target = mom.getTarget();
        setTarget(Target);
        mother = mom;
    }

    @Override
    protected void attackEntity(Entity entity, float f) {
        float f1 = this.getEntityBrightness(1.0F);
        if (f1 > 0.5F && this.rand.nextInt(100) == 0) {
            this.entityToAttack = null;
        } else {
            if (f > 2.0F && f < 3.0F && this.rand.nextInt(10) == 0) {
                if (this.onGround) {
                    double d = entity.posX - this.posX;
                    double d1 = entity.posZ - this.posZ;
                    float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
                    this.motionX = (d / (double)f2 * 0.5 * 0.800000011920929 + this.motionX * 0.20000000298023224)/3;
                    this.motionZ = (d1 / (double)f2 * 0.5 * 0.800000011920929 + this.motionZ * 0.20000000298023224)/3;
                    this.motionY = 0.8000000059604645;
                }
            } else {
                if (this.attackTime <= 0 && f < 0.5F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY) {
                    this.attackTime = 20;
                    entity.attackEntityFrom(this, this.attackStrength, DamageType.COMBAT);
                    mother.health++;
                }
            }

        }
    }


    public void onLivingUpdate(){
        if(Target.isDead || Target == null || mother.isDead){
            super.attackEntityFrom((Entity)null, 100, (DamageType)null);
        }
        super.onLivingUpdate();
    }
}
