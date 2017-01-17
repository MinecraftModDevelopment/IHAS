package com.mcmoddev.ihas.features.entities.release;

import com.mcmoddev.ihas.features.IHASFeature;
import com.mcmoddev.ihas.features.IIHASFeature;
import com.mcmoddev.lib.util.ItemStackUtils;

import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@IHASFeature(featureID = "release-pets", name = "Release Pets", version = "1.0.0")
public class FeatureReleasePets implements IIHASFeature {
    
    @Override
    public boolean hasEvents () {
        return true;
    }
    
    @SubscribeEvent
    public void onEntityInteract (EntityInteract event) {
        if (event.getEntityPlayer().isSneaking() && event.getTarget() instanceof EntityWolf && ItemStackUtils.isValidStack(event.getEntityPlayer().getHeldItemMainhand()) && event.getEntityPlayer().getHeldItemMainhand().getItem() instanceof ItemShears) {
            final EntityTameable wolf = (EntityTameable) event.getTarget();
            if (wolf.isOwner(event.getEntityPlayer())) {
                event.getEntityPlayer().playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1f, 1f);
                wolf.playSound(SoundEvents.ENTITY_WOLF_WHINE, 1f, 1f);
                wolf.setTamed(false);
                wolf.setOwnerId(null);
                if (wolf.hasCustomName()) {
                    final ItemStack tag = new ItemStack(Items.NAME_TAG, 1);
                    tag.setStackDisplayName(wolf.getCustomNameTag());
                    wolf.setCustomNameTag("");
                    if (!event.getEntity().world.isRemote)
                        wolf.entityDropItem(tag, 0.5f);
                }
            }
        }
    }
}