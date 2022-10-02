package com.example.examplemod.mobs.ForestSpider;

import com.example.examplemod.mobs.MiniSpider.EntityMiniSpider;
import net.minecraft.src.*;
import net.minecraft.src.helper.DamageType;

public class EntityForestSpider extends EntitySpider {
    private boolean hasjumped = false;
    public int minions = 0;
    public EntityForestSpider(World world) {
        super(world);
        this.setSize(1.2F, 2.3F);
        this.moveSpeed = 0.4f;
        this.health = 45;
        this.texture = "/mob/forestspider.png";
    }

    @Override
    public void entityInitOnSpawn() {}
//find some way to get attack many animals

    @Override
    protected Entity findPlayerToAttack() {
            double d = 16.0;
            EntityPlayer p = this.worldObj.getClosestPlayerToEntity(this, d);
            return p != null && !p.getGamemode().areMobsHostile ? null : p;
    }

    @Override
    protected void attackEntity(Entity entity, float f) {
        if (this.rand.nextInt(30/ this.worldObj.difficultySetting) == 0) {
            double d = entity.posX - this.posX;
            double d1 = entity.posZ - this.posZ;
            float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
            if(!hasjumped && this.onGround && f > 2.0F && f < 3.0F) {
                this.motionX = (d / (double)f2 * 0.5 * 0.800000011920929 + this.motionX * 0.20000000298023224);
                this.motionZ = (d1 / (double)f2 * 0.5 * 0.800000011920929 + this.motionZ * 0.20000000298023224);
                this.motionY = 0.4000000059604645;
                hasjumped = true;
            }else if(f<6 && this.rand.nextInt(10)==0){
                this.motionX = -1.5 * (d / (double) f2 * 0.5 * 0.800000011920929 + this.motionX * 0.20000000298023224);
                this.motionZ = -1.5 * (d1 / (double) f2 * 0.5 * 0.800000011920929 + this.motionZ * 0.20000000298023224);
                this.motionY = 0.1000000059604645;
                hasjumped = false;
            }
            if(this.rand.nextInt(9/this.worldObj.difficultySetting)==0){
                if(minions <= health/5){
                    EntityMiniSpider minispider = new EntityMiniSpider(this.worldObj, this);
                    minispider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                    this.worldObj.entityJoinedWorld(minispider);
                    minions++;
                }
            }
        }if (this.attackTime <= 0 && f < 1.5F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY) {
            this.attackTime = 20;
            entity.attackEntityFrom(this, this.attackStrength, DamageType.COMBAT);
        }

    }

    @Override
    protected int getDropItemId() {
        return Item.stick.itemID;
    }

    @Override
    public boolean isOnLadder() {
        return false;
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
            }if(this.worldObj.getWorldTime() < 2300 && this.worldObj.getWorldTime() > 2200){
                return blockLight <= 4 && super.getCanSpawnHere();
            }else{
                return false;
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setShort("Minions", (short)this.minions);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        this.minions = nbttagcompound.getShort("Minions");
    }
}