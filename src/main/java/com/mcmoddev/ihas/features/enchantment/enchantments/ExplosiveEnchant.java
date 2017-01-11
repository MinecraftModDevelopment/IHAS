package com.mcmoddev.ihas.features.enchantment.enchantments;

import com.mcmoddev.ihas.IHAS;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class ExplosiveEnchant extends Enchantment {
    public static final ExplosiveEnchant ENCHANT = new ExplosiveEnchant();

    public ExplosiveEnchant () {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET});
        setRegistryName(IHAS.MOD_ID, "explosive");
        setName("explosive");
    }

    public static void register () {
        int i = Enchantment.REGISTRY.getKeys().size();
        Enchantment.REGISTRY.register(++i, new ResourceLocation(IHAS.MOD_ID, "explosive"), ENCHANT);
    }

    @Override
    public int getMaxLevel () {
        return 2;
    }
}