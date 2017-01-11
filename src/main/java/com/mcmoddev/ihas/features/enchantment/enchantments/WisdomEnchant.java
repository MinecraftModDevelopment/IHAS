package com.mcmoddev.ihas.features.enchantment.enchantments;

import com.mcmoddev.ihas.IHAS;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class WisdomEnchant extends Enchantment {
    public static final WisdomEnchant ENCHANT = new WisdomEnchant();

    public WisdomEnchant () {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_HEAD, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD});
        setRegistryName(IHAS.MOD_ID, "wisdom");
        setName("wisdom");
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
