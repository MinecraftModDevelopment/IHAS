package com.mcmoddev.ihas.features.enchantment;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentExplosive extends Enchantment {

    public EnchantmentExplosive() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[] { EntityEquipmentSlot.FEET });
    }

    @Override
    public int getMaxLevel () {
        return 2;
    }
    
    @SubscribeEvent
    public void onEntityLivingFall (LivingFallEvent event) {
        final EntityLivingBase entity = event.getEntityLiving();
        final float distance = event.getDistance();
        if (entity instanceof EntityPlayer && distance > 5.0F && entity.isSneaking() && entity.getArmorInventoryList() instanceof List && !((List<ItemStack>) entity.getArmorInventoryList()).isEmpty())
            for (final ItemStack stack : entity.getArmorInventoryList()) {
                final int lvl = EnchantmentHelper.getEnchantmentLevel(FeatureEnchantments.explosive, stack);
                if (stack != null && stack.getItem() instanceof ItemArmor && lvl > 0) {
                    final float h = entity.getHealth();
                    entity.getEntityWorld().createExplosion(entity, entity.posX, entity.posY, entity.posZ, Math.min(distance / (8F / lvl), 5.0F), true);
                    entity.setHealth(h);
                }
            }
    }
}