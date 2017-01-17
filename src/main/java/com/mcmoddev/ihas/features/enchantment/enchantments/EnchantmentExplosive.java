package com.mcmoddev.ihas.features.enchantment.enchantments;

import com.mcmoddev.ihas.IHAS;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentExplosive extends Enchantment {

    public static final EnchantmentExplosive ENCHANT = new EnchantmentExplosive();

    public EnchantmentExplosive() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[] { EntityEquipmentSlot.FEET });
        this.setRegistryName(IHAS.MOD_ID, "explosive");
        this.setName("explosive");
    }

    @Override
    public int getMaxLevel () {
        return 2;
    }
}