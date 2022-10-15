package com.example.examplemod.mobs.Moa;

import com.example.examplemod.util.LivingEntityHelper;
import net.minecraft.src.*;


public class EntityMoa extends EntityAnimal {
    private boolean saddled;
    private int ticksteps = 0;
    public float field_752_b = 0.0F;
    public float destPos = 0.0F;
    public float field_757_d;
    public float field_756_e;
    public float field_755_h = 1.0F;
    public EntityMoa(World world) {
        super(world);
        this.texture = "mob/moa.png";
        this.setSize(0.8f, 3f);
        saddled = false;
        this.moveSpeed = 2.3f;
        this.stepHeight = 1.0f;
        this.health = 24;
    }

    public void entityInitOnSpawn() {
        super.entityInit();
        if (this.worldObj.difficultySetting != 0 && this.rand.nextInt(12 / this.worldObj.difficultySetting) == 0) {
            EntityArmouredZombie rider = new EntityArmouredZombie(this.worldObj);
            rider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            this.worldObj.entityJoinedWorld(rider);
            rider.mountEntity(this);
        }
        for(int i = 0; i<3;i++){
            if (this.worldObj.difficultySetting != 0 && this.rand.nextInt(32 / this.worldObj.difficultySetting) == 0) {
                EntityArmouredZombie fighter = new EntityArmouredZombie(this.worldObj);
                fighter.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
                this.worldObj.entityJoinedWorld(fighter);
            }
        }

    }
    protected boolean canDespawn() {
        return (!saddled && super.canDespawn());
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
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.field_756_e = this.field_752_b;
        this.field_757_d = this.destPos;
        this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3);
        if (this.destPos < 0.0F) {
            this.destPos = 0.0F;
        }

        if (this.destPos > 1.0F) {
            this.destPos = 1.0F;
        }

        if (!this.onGround && this.field_755_h < 1.0F) {
            this.field_755_h = 1.0F;
        }

        this.field_755_h = (float)((double)this.field_755_h * 0.9);
        if (!this.onGround && this.motionY < 0.0) {
            this.motionY *= 0.8;
        }

    }
    public void stillUpdate(){
        super.onEntityUpdate();
        //render yaw
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


        this.renderYawOffset = this.rotationYaw;


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
        //water move
        flag = this.isInWater();
        boolean flag1 = this.handleLavaMovement();
        if (flag) {
            this.motionY += 0.04;
        } else if (flag1) {
            this.motionY += 0.04;
        }
    }
    public void onUpdate(){
// fix bounding box issues
        if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
            this.riddenByEntity = null;
        }

        if (this.riddenByEntity != null && !this.isDead) {
            if(height != 1.8){
                this.height =  1.8f;
                this.setPosition(this.posX, this.posY, this.posZ);
            }
            stillUpdate();
            if( LivingEntityHelper.getJumping(this.riddenByEntity) && onGround){
                this.jump();
            }
            if(this.riddenByEntity instanceof EntityPlayer){
                ignoreFrustumCheck = true;
                if (LivingEntityHelper.getForward(this.riddenByEntity)!= 0){
                    ticksteps = 30;
                }if(ticksteps != 0){
                    this.moveForward = Math.signum(LivingEntityHelper.getForward(this.riddenByEntity))*this.moveSpeed;
                    ticksteps --;
                }
                rotationYaw += 9*(-1)*LivingEntityHelper.getStrafing(this.riddenByEntity);
                this.moveStrafing = LivingEntityHelper.getStrafing(this.riddenByEntity)/6;
                moveEntityWithHeading(this.moveStrafing, this.moveForward);
            } else{
                this.rotationYaw = this.riddenByEntity.rotationYaw;
                this.motionX = this.riddenByEntity.motionX * 1.4;
                this.motionZ = this.riddenByEntity.motionZ * 1.4;
            }

        }else{
            if(height != 3){
                ignoreFrustumCheck =false;
                this.height = 3;
                this.setPosition(this.posX, this.posY, this.posZ);
            }
            super.onUpdate();
        }
    }
    public double getMountedYOffset() {
        return (double)this.height - 0.0;
    }
    public void updateRiderPosition() {
        if (this.riddenByEntity != null) {
            this.riddenByEntity.setPosition(this.posX, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ);
        }
    }
    protected void fall(float f) {
    }
//    @Override
//    public float getEyeHeight(){
//        return this.height + 0.8f;
//    }
    @Override
    protected void jump(){
        this.motionY = 0.5f;
        this.moveForward*= 1.25;
    }
    protected void dropFewItems() {
        if (saddled) {
            this.dropItem(Item.saddle.itemID, 1);
        }

        super.dropFewItems();
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
    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);
        return j<100 && this.getBlockPathWeight(i, j, k) >= 0.0F;
    }
    protected float getBlockPathWeight(int i, int j, int k) {
        int blockId = this.worldObj.getBlockId(i, j - 1, k);
        return blockId != Block.stone.blockID && blockId != Block.basalt.blockID ? 20 - this.worldObj.getLightBrightness(i, j, k) : 10.0F;
    }
}
