package com.mcmoddev.ihas.features.enchantment;

import com.google.common.collect.Sets;
import com.mcmoddev.ihas.features.enchantment.enchantments.ExplosiveEnchant;
import com.mcmoddev.ihas.features.enchantment.enchantments.WisdomEnchant;
import com.mcmoddev.ihas.features.plugin.BlankIHasFeature;
import com.mcmoddev.ihas.features.plugin.IHASFeature;
import com.mcmoddev.ihas.features.plugin.IProxy;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

@IHASFeature(featureID = "enchantment", name = "Enchantment", version = "1.0.0")
public class Enchantment extends BlankIHasFeature {

    @SidedProxy(clientSide = "com.mcmoddev.ihas.features.enchantment.EnchantmentClientProxy", serverSide = "com.mcmoddev.ihas.features.enchantment.EnchantmentServerProxy")
    public static IProxy PROXY;

    @Override
    public Set<String> getDependIds () {
        return Sets.newHashSet("core");
    }

    @Override
    public boolean canBeDisabled () {
        return true;
    }

    @Override
    public void preInit () {
        ExplosiveEnchant.register();
        WisdomEnchant.register();
    }

    @Override
    public void init () {
        super.init();
    }

    @Override
    public void postInit () {
        super.postInit();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IProxy getProxy () {
        return PROXY;
    }

    @Override
    public Object getEventHandler () {
        return new EnchantmentEventHandler();
    }
}