package com.mcmoddev.ihas.features.enchantment;

import com.mcmoddev.ihas.IHAS;
import com.mcmoddev.ihas.features.IHASFeature;
import com.mcmoddev.ihas.features.IIHASFeature;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

@IHASFeature(featureID = "enchantment", name = "Enchantment", version = "1.0.0")
public class FeatureEnchantments implements IIHASFeature {

    public static Enchantment explosive;
    public static Enchantment wisdom;
    
    @Override
    public void preInit () {
        
        explosive = registerEnchantment(new EnchantmentExplosive(), "explosive", true);
        wisdom = registerEnchantment(new EnchantmentWisdom(), "wisdom", true);
    }
    
    public Enchantment registerEnchantment(Enchantment enchantment, String name, boolean usesEvents) {
        
        enchantment.setRegistryName(IHAS.MOD_ID, name);
        enchantment.setName(IHAS.MOD_ID + "." + name);
        GameRegistry.register(enchantment);
        
        if (usesEvents)
            MinecraftForge.EVENT_BUS.register(enchantment);
        
        return enchantment;
    }
}