package com.example.examplemod.items;

import net.minecraft.src.*;

public class ItemTieredBow extends Item {
    private int maxReloadticks;
    private int ticksToFire = -1;
    private int iconX;
    private int iconY;
    public ItemTieredBow(int i, int ticksReload) {
        super(i);
        maxReloadticks = ticksReload;
    }

    public Item setIconCoord(int i, int j) {
        iconX = i;
        iconY = j;
        return super.setIconCoord(iconX, iconY);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if(ticksToFire == -1){
            ticksToFire = maxReloadticks;
        }
        return itemstack;
    }

    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
        if(ticksToFire != -1) {
            ticksToFire--;
            if(ticksToFire == maxReloadticks){
                super.setIconCoord(iconX +1, iconY);
            }else if(ticksToFire == (int)(2*maxReloadticks/3)){
                super.setIconCoord(iconX +2, iconY);
            }else if (ticksToFire == (int)(maxReloadticks/3)) {
                super.setIconCoord(iconX +3, iconY);
            }else if (ticksToFire == 0) {
                super.setIconCoord(iconX, iconY);
                EntityPlayer entityplayer = (EntityPlayer) entity;
                ItemStack quiverSlot = entityplayer.inventory.armorItemInSlot(2);
                if (quiverSlot != null && quiverSlot.itemID == Item.armorQuiver.itemID && quiverSlot.getMetadata() < quiverSlot.getMaxDamage()) {
                    entityplayer.inventory.armorItemInSlot(2).damageItem(1, entityplayer);
                    itemstack.damageItem(1, entityplayer);
                    world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    if (!world.isMultiplayerAndNotHost) {
                        world.entityJoinedWorld(new EntityArrow(world, entityplayer, true, 0));
                    }
                } else if (quiverSlot != null && quiverSlot.itemID == Item.armorQuiverGold.itemID) {
                    itemstack.damageItem(1, entityplayer);
                    world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    if (!world.isMultiplayerAndNotHost) {
                        world.entityJoinedWorld(new EntityArrowPurple(world, entityplayer, false));
                    }
                } else if (entityplayer.inventory.consumeInventoryItem(Item.ammoArrowGold.itemID)) {
                    itemstack.damageItem(1, entityplayer);
                    world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    if (!world.isMultiplayerAndNotHost) {
                        world.entityJoinedWorld(new EntityArrowGolden(world, entityplayer, true));
                    }
                } else if (entityplayer.inventory.consumeInventoryItem(Item.ammoArrow.itemID)) {
                    itemstack.damageItem(1, entityplayer);
                    world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    if (!world.isMultiplayerAndNotHost) {
                        world.entityJoinedWorld(new EntityArrow(world, entityplayer, true, 0));
                    }
                }
            }
        }
    }
}
