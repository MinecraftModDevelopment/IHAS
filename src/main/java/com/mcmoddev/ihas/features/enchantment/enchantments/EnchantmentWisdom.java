package com.mcmoddev.ihas.features.enchantment.enchantments;

import com.mcmoddev.ihas.IHAS;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class EnchantmentWisdom extends Enchantment {

    public static final EnchantmentWisdom ENCHANT = new EnchantmentWisdom();

    public EnchantmentWisdom() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[] { EntityEquipmentSlot.FEET });
        this.setRegistryName(IHAS.MOD_ID, "wisdom");
        this.setName("wisdom");
    }

    public static void register () {
        int i = Enchantment.REGISTRY.getKeys().size();
        Enchantment.REGISTRY.register(++i, new ResourceLocation(IHAS.MOD_ID, "wisdom"), ENCHANT);
    }

    @Override
    public int getMaxLevel () {
        return 4;
    }
}
