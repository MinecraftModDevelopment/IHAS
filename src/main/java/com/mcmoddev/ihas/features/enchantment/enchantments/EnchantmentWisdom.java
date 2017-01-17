package com.mcmoddev.ihas.features.enchantment.enchantments;

import com.mcmoddev.ihas.IHAS;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentWisdom extends Enchantment {

    public static final EnchantmentWisdom ENCHANT = new EnchantmentWisdom();

    public EnchantmentWisdom() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[] { EntityEquipmentSlot.FEET });
        this.setRegistryName(IHAS.MOD_ID, "wisdom");
        this.setName("wisdom");
    }

    @Override
    public int getMaxLevel () {
        return 4;
    }
}
