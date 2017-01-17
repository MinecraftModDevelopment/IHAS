package com.mcmoddev.ihas.features.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentWisdom extends Enchantment {

    public EnchantmentWisdom() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[] { EntityEquipmentSlot.FEET });
    }

    @Override
    public int getMaxLevel () {
        return 4;
    }
    

    @SubscribeEvent
    public void onExperienceDrop (LivingExperienceDropEvent event) {
        event.setDroppedExperience((int) (event.getOriginalExperience() + event.getOriginalExperience() * 0.15 * EnchantmentHelper.getEnchantmentLevel(FeatureEnchantments.wisdom, event.getAttackingPlayer().getHeldItemMainhand())));
    }
}
