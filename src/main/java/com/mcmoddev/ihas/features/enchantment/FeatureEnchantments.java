package com.mcmoddev.ihas.features.enchantment;

import java.util.List;

import com.mcmoddev.ihas.features.IHASFeature;
import com.mcmoddev.ihas.features.IIHASFeature;
import com.mcmoddev.ihas.features.enchantment.enchantments.EnchantmentExplosive;
import com.mcmoddev.ihas.features.enchantment.enchantments.EnchantmentWisdom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@IHASFeature(featureID = "enchantment", name = "Enchantment", version = "1.0.0")
public class FeatureEnchantments implements IIHASFeature {

    @Override
    public void preInit () {
        GameRegistry.register(EnchantmentExplosive.ENCHANT);
        GameRegistry.register(EnchantmentWisdom.ENCHANT);
    }

    @Override
    public boolean hasEvents () {
        return true;
    }

    @SubscribeEvent
    public void onExperienceDrop (LivingExperienceDropEvent event) {
        event.setDroppedExperience((int) (event.getOriginalExperience() + event.getOriginalExperience() * 0.15 * EnchantmentHelper.getEnchantmentLevel(EnchantmentWisdom.ENCHANT, event.getAttackingPlayer().getHeldItemMainhand())));
    }

    @SubscribeEvent
    public void onEntityLivingFall (LivingFallEvent event) {
        final EntityLivingBase entity = event.getEntityLiving();
        final float distance = event.getDistance();
        if (entity instanceof EntityPlayer && distance > 5.0F && entity.isSneaking() && entity.getArmorInventoryList() instanceof List && !((List<ItemStack>) entity.getArmorInventoryList()).isEmpty())
            for (final ItemStack stack : entity.getArmorInventoryList()) {
                final int lvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentExplosive.ENCHANT, stack);
                if (stack != null && stack.getItem() instanceof ItemArmor && lvl > 0) {
                    final float h = entity.getHealth();
                    entity.getEntityWorld().createExplosion(entity, entity.posX, entity.posY, entity.posZ, Math.min(distance / (8F / lvl), 5.0F), true);
                    entity.setHealth(h);
                }
            }
    }
}