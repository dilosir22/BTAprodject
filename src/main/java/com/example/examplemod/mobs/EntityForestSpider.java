package com.example.examplemod.mobs;

import net.minecraft.src.*;
import net.minecraft.src.helper.DamageType;

public class EntityForestSpider extends EntitySpider {
    private boolean hasjumped = false;
    public EntityForestSpider(World world) {
        super(world);
        this.moveSpeed = 0.6f;
        this.health = 35;
        this.texture = "/com/example/examplemod/mobs/textures/forestspider.png";
    }

    @Override
    public void entityInitOnSpawn() {
        if (this.worldObj.difficultySetting != 0 && this.rand.nextInt(60 / this.worldObj.difficultySetting) == 0) {
            EntityArmouredZombie entityzombie = new EntityArmouredZombie(this.worldObj);
            entityzombie.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            this.worldObj.entityJoinedWorld(entityzombie);
            entityzombie.mountEntity(this);
        }

    }

    @Override
    protected void attackEntity(Entity entity, float f) {
        if (f > 2.0F && f < 6.0F && this.rand.nextInt(30/ this.worldObj.difficultySetting) == 0) {
            if (this.onGround) {
                for(int i = 0; i < 3; i++){
                    EntitySpider minispider = new EntitySpider(this.worldObj);
                    minispider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                    this.worldObj.entityJoinedWorld(minispider);
                }

                double d = entity.posX - this.posX;
                double d1 = entity.posZ - this.posZ;
                float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
                if(hasjumped) {
                    this.motionX = -1.5 * (d / (double) f2 * 0.5 * 0.800000011920929 + this.motionX * 0.20000000298023224);
                    this.motionZ = -1.5 * (d1 / (double) f2 * 0.5 * 0.800000011920929 + this.motionZ * 0.20000000298023224);
                    this.motionY = 0.2000000059604645;
                    hasjumped = false;
                }else{
                    this.motionX = (d / (double)f2 * 0.5 * 0.800000011920929 + this.motionX * 0.20000000298023224);
                    this.motionZ = (d1 / (double)f2 * 0.5 * 0.800000011920929 + this.motionZ * 0.20000000298023224);
                    this.motionY = 0.4000000059604645;
                    hasjumped = true;
                }


            }
        } else {
            if (this.attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY) {
                this.attackTime = 20;
                entity.attackEntityFrom(this, this.attackStrength, DamageType.COMBAT);
            }
        }
    }

    @Override
    protected int getDropItemId() {
        return Item.stick.itemID;
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);
        if (this.worldObj.getSavedLightValue(EnumLightType.Block, i, j, k) > 4) {
            return false;
        } else if (this.worldObj.getSavedLightValue(EnumLightType.Sky, i, j, k) > this.rand.nextInt(32)) {
            return false;
        } else {
            int blockLight = this.worldObj.getBlockLightValue(i, j, k);
            if (this.worldObj.currentWeather != null && this.worldObj.currentWeather.doMobsSpawnInDaylight) {
                blockLight /= 2;
            }

            return blockLight <= 4 && super.getCanSpawnHere();
        }
    }
}
