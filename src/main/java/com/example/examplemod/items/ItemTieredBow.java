package com.example.examplemod.items;

import com.example.examplemod.util.ItemHelper;
import net.minecraft.src.*;

import java.lang.reflect.InvocationTargetException;

public class ItemTieredBow extends Item {
    private int maxReloadticks;
    private String ticksToFire = "ttf";
    private int iconX;
    private int iconY;
    public ItemTieredBow(int i, int ticksReload, int usages) {
        super(i);
        maxReloadticks = ticksReload;
        this.setMaxDamage((usages*4));
        this.maxStackSize = 1;
    }

    public boolean isFull3D() {
        return true;
    }

    public Item setIconCoord(int i, int j) {
        iconX = i;
        iconY = j;
        return super.setIconCoord(iconX, iconY);
    }

    public int getIconFromDamage(int i) {
        if(i%4==0) {
            return iconCoordToIndex(iconX, iconY);
        } else if (i%4==1) {
            return iconCoordToIndex(iconX+1, iconY);
        }else if (i%4==2) {
            return iconCoordToIndex(iconX+2, iconY);
        }else{
            return iconCoordToIndex(iconX+3, iconY);
        }
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        ItemStack quiverSlot = entityplayer.inventory.armorItemInSlot(2);
        try {
            if((itemstack.tag.getInteger(ticksToFire) == -1)&&
                    ((quiverSlot != null &&quiverSlot.getMetadata() < quiverSlot.getMaxDamage())||
                            ((int)(ItemHelper.getInventorySlotContainItem.invoke(entityplayer.inventory,Item.ammoArrowGold.itemID)) != -1)||
                            ((int)(ItemHelper.getInventorySlotContainItem.invoke(entityplayer.inventory,Item.ammoArrow.itemID)) != -1))){
                itemstack.tag.setInteger(ticksToFire, maxReloadticks);
                itemstack.damageItem(1, entityplayer);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return itemstack;
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
        EntityPlayer entityplayer = (EntityPlayer) entity;
        if(itemstack.tag.getInteger(ticksToFire) != -1) {
            if(itemstack.tag.getInteger(ticksToFire) == maxReloadticks){
                if(itemstack.getMetadata() % 4 != 1){itemstack.damageItem(1, entityplayer);}
            }else if(itemstack.tag.getInteger(ticksToFire) == (int)(2*maxReloadticks/3)){
                if(itemstack.getMetadata() % 4 != 2){itemstack.damageItem(1, entityplayer);}
            }else if (itemstack.tag.getInteger(ticksToFire) == (int)(maxReloadticks/3)) {
                if(itemstack.getMetadata() % 4 != 3){itemstack.damageItem(1, entityplayer);}
            }else if (itemstack.tag.getInteger(ticksToFire) == 0) {
                ItemStack quiverSlot = entityplayer.inventory.armorItemInSlot(2);
                if (quiverSlot != null && quiverSlot.itemID == Item.armorQuiver.itemID && quiverSlot.getMetadata() < quiverSlot.getMaxDamage()) {
                    entityplayer.inventory.armorItemInSlot(2).damageItem(1, entityplayer);
                    world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    if (!world.isMultiplayerAndNotHost) {
                        world.entityJoinedWorld(new EntityArrow(world, entityplayer, true, 0));
                    }
                } else if (quiverSlot != null && quiverSlot.itemID == Item.armorQuiverGold.itemID) {
                    world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    if (!world.isMultiplayerAndNotHost) {
                        world.entityJoinedWorld(new EntityArrowPurple(world, entityplayer, false));
                    }
                } else if (entityplayer.inventory.consumeInventoryItem(Item.ammoArrowGold.itemID)) {
                    world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    if (!world.isMultiplayerAndNotHost) {
                        world.entityJoinedWorld(new EntityArrowGolden(world, entityplayer, true));
                    }
                } else if (entityplayer.inventory.consumeInventoryItem(Item.ammoArrow.itemID)) {

                    world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    if (!world.isMultiplayerAndNotHost) {
                        world.entityJoinedWorld(new EntityArrow(world, entityplayer, true, 0));
                    }
                }

                if(itemstack.getMetadata() % 4 != 0){itemstack.damageItem(1, entityplayer);}
            }

            itemstack.tag.setInteger(ticksToFire,itemstack.tag.getInteger(ticksToFire) - 1);
        }
    }
}
