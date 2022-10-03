package com.example.examplemod.mobs.Moa;

import net.minecraft.src.*;

public class EntityMoa extends EntityAnimal {
    private boolean saddled = false;
    public EntityMoa(World world) {
        super(world);
        this.texture = "mobs/moa.png";
        this.setSize(0.9f, 2.5f);
        saddled = false;

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

    public void onUpdate(){
        super.onUpdate();
        if (this.riddenByEntity != null) {
            this.motionX += this.riddenByEntity.motionX * 0.4;
            this.motionZ += this.riddenByEntity.motionZ * 0.4;
        }
        if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
            this.riddenByEntity = null;
        }
        if (this.riddenByEntity != null) {
            this.motionX += this.riddenByEntity.motionX * 1.4;
            this.motionZ += this.riddenByEntity.motionZ * 0.4;
            if(this.riddenByEntity.motionZ > 0){
                this.rotationYaw += 12;
            } else if (this.riddenByEntity.motionZ < 0) {
                this.rotationYaw -= 12;
            }
        }
    }
    public double getMountedYOffset() {
        return (double)this.height - 0.7;
    }
    public void updateRiderPosition() {
        if (this.riddenByEntity != null) {
            double d = Math.cos((double)this.rotationYaw * Math.PI / 180.0) * 0.4;
            double d1 = Math.sin((double)this.rotationYaw * Math.PI / 180.0) * 0.4;
            this.riddenByEntity.setPosition(this.posX + d, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
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
