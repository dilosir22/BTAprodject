package com.example.examplemod.mobs.Moa;

import com.example.examplemod.util.LivingEntityHelper;
import net.minecraft.src.*;

public class EntityMoa extends EntityAnimal {
    private boolean saddled = false;
    public EntityMoa(World world) {
        super(world);
        this.texture = "mobs/moa.png";
        this.setSize(0.9f, 2.5f);
        saddled = false;
        this.moveSpeed = 1.5f;
        this.stepHeight = 1.0f;

    }

    public void entityInitOnSpawn() {
        super.entityInit();
        if (this.worldObj.difficultySetting != 0 && this.rand.nextInt(12 / this.worldObj.difficultySetting) == 0) {
            EntityArmouredZombie rider = new EntityArmouredZombie(this.worldObj);
            rider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            this.worldObj.entityJoinedWorld(rider);
            rider.mountEntity(this);
        }

    }

    public boolean interact(EntityPlayer entityplayer) {
        if (super.interact(entityplayer)) {
            return true;
        } else if (this.worldObj.isMultiplayerAndNotHost || this.riddenByEntity != null && this.riddenByEntity != entityplayer) {
            return false;
        } else {
            ItemStack item = entityplayer.inventory.getCurrentItem();
            if(!saddled && item!= null && item.itemID == Item.saddle.itemID){
                --item.stackSize;
                if (item.stackSize <= 0) {
                    entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, (ItemStack)null);
                }
                saddled = true;
               return true;
            }if(saddled){
                entityplayer.mountEntity(this);
                return true;
            }
            return false;
        }
    }
    public void stillUpdate(){
        super.onEntityUpdate();
        double d = this.posX - this.prevPosX;
        double d1 = this.posZ - this.prevPosZ;
        float f = MathHelper.sqrt_double(d * d + d1 * d1);
        float f1 = this.renderYawOffset;
        float f2 = 0.0F;
        this.field_9362_u = this.field_9361_v;
        float f3 = 0.0F;
        if (f > 0.05F) {
            f3 = 1.0F;
            f2 = f * 3.0F;
            f1 = (float) Math.atan2(d1, d) * 180.0F / 3.141593F - 90.0F;
        }

        if (this.swingProgress > 0.0F) {
            f1 = this.rotationYaw;
        }

        if (!this.onGround) {
            f3 = 0.0F;
        }

        this.field_9361_v += (f3 - this.field_9361_v) * 0.3F;

        float f4;
        for (f4 = f1 - this.renderYawOffset; f4 < -180.0F; f4 += 360.0F) {
        }

        while (f4 >= 180.0F) {
            f4 -= 360.0F;
        }

        this.renderYawOffset += f4 * 0.3F;

        float f5;
        for (f5 = this.rotationYaw - this.renderYawOffset; f5 < -180.0F; f5 += 360.0F) {
        }

        while (f5 >= 180.0F) {
            f5 -= 360.0F;
        }

        boolean flag = f5 < -90.0F || f5 >= 90.0F;
        if (f5 < -75.0F) {
            f5 = -75.0F;
        }

        if (f5 >= 75.0F) {
            f5 = 75.0F;
        }

        this.renderYawOffset = this.rotationYaw - f5;
        if (f5 * f5 > 2500.0F) {
            this.renderYawOffset += f5 * 0.2F;
        }

        if (flag) {
            f2 *= -1.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
        }

        while (this.renderYawOffset - this.prevRenderYawOffset < -180.0F) {
            this.prevRenderYawOffset -= 360.0F;
        }

        while (this.renderYawOffset - this.prevRenderYawOffset >= 180.0F) {
            this.prevRenderYawOffset += 360.0F;
        }

        while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
            this.prevRotationPitch -= 360.0F;
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
        }
        this.field_9360_w += f2;
    }
    public void onUpdate(){

        if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
            this.riddenByEntity = null;
        }

        if (this.riddenByEntity != null && !this.isDead) {
            stillUpdate();

            if(this.riddenByEntity instanceof EntityPlayer){
                this.moveForward  = LivingEntityHelper.getForward(this.riddenByEntity) * this.moveSpeed;
                rotationYaw += 9*(-1)*LivingEntityHelper.getStrafing(this.riddenByEntity);
                moveEntityWithHeading(this.moveStrafing, this.moveForward);
            } else{
                this.rotationYaw = this.riddenByEntity.rotationYaw;
                this.motionX = this.riddenByEntity.motionX * 1.4;
                this.motionZ = this.riddenByEntity.motionZ * 1.4;
            }
            if( LivingEntityHelper.getJumping(this.riddenByEntity) && onGround){
                this.jump();
            }
        }else{
            super.onUpdate();
        }
    }
    public double getMountedYOffset() {
        return (double)this.height - 0.7;
    }
    public void updateRiderPosition() {
        if (this.riddenByEntity != null) {
            this.riddenByEntity.setPosition(this.posX, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Saddled", (boolean) this.saddled);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        this.saddled = nbttagcompound.getBoolean("Saddled");
    }
}
