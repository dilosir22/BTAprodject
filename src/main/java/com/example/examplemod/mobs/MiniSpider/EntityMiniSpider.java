package com.example.examplemod.mobs.MiniSpider;

import com.example.examplemod.mobs.ForestSpider.EntityForestSpider;
import com.example.examplemod.util.LivingEntityHelper;
import net.minecraft.src.*;
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
        this.attackStrength = 2;
    }
    public EntityMiniSpider(World world, EntityForestSpider mom) {
        this(world);
        Target = mom.getTarget();
        setTarget(Target);
        mother = mom;
    }

    @Override
    protected void attackEntity(Entity entity, float f) {

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

    public boolean shouldDie(){
        if(Target == null ||  mother == null){
            return true;
        }else if (Target.isDead || mother.isDead ){
            return true;
        }
        return false;
    }

    public void onLivingUpdate(){
        if(shouldDie()){
            super.attackEntityFrom((Entity)null, 100, (DamageType)null);
        }
        super.onLivingUpdate();
    }

    public void onDeath(Entity entity) {
        if(mother != null){
            mother.minions --;
        }
        super.onDeath(entity);
    }
    @Override
    protected int getDropItemId() {
        return 0;
    }
    public Entity locateNearestMother(double d3){
        double d4 = -1.0;
        Entity entity = null;

        for(int i = 0; i < this.worldObj.loadedEntityList.size(); ++i) {
            Entity mob1 = this.worldObj.loadedEntityList.get(i);
            double d5 = mob1.getDistanceSq(this.posX, this.posY, this.posZ);
            if ((d3 < 0.0 || d5 < d3 * d3) && (d4 == -1.0 || d5 < d4)) {
                d4 = d5;
                if(mob1 instanceof EntityForestSpider){
                    entity = mob1;
                }
            }
        }

        return entity;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        this.mother = (EntityForestSpider)locateNearestMother(-1);
        if(mother == null){
            super.attackEntityFrom((Entity)null, 100, (DamageType)null);
        }else{
            Target = mother.getTarget();
            setTarget(Target);
        }
    }
}
