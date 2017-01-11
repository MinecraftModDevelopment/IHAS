package com.mcmoddev.ihas.features.enchantment;

import com.mcmoddev.ihas.features.enchantment.enchantments.ExplosiveEnchant;
import com.mcmoddev.ihas.features.enchantment.enchantments.WisdomEnchant;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class EnchantmentEventHandler {
    @SubscribeEvent
    public void onExperienceDrop (LivingExperienceDropEvent event) {
        event.setDroppedExperience((int) (event.getOriginalExperience() + event.getOriginalExperience() * 0.15 * EnchantmentHelper.getEnchantmentLevel(WisdomEnchant.ENCHANT, event.getAttackingPlayer().getHeldItemMainhand())));
    }

    @SubscribeEvent
    public void onEntityLivingFall (LivingFallEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        float distance = event.getDistance();
        if (entity instanceof EntityPlayer && distance > 5.0F && entity.isSneaking() && entity.getArmorInventoryList() instanceof List && !((List) entity.getArmorInventoryList()).isEmpty())
            for (ItemStack stack : entity.getArmorInventoryList()) {
                int lvl = EnchantmentHelper.getEnchantmentLevel(ExplosiveEnchant.ENCHANT, stack);
                if (stack != null && stack.getItem() instanceof ItemArmor && lvl > 0) {
                    float h = entity.getHealth();
                    entity.getEntityWorld().createExplosion(entity, entity.posX, entity.posY, entity.posZ, Math.min(distance / (8F / lvl), 5.0F), true);
                    entity.setHealth(h);
                }
            }
    }

}