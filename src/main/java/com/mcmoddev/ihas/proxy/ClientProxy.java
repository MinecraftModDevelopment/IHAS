package com.mcmoddev.ihas.proxy;

import com.mcmoddev.ihas.features.plugin.FeatureDeployment;
import com.mcmoddev.ihas.features.plugin.IIHASFeature;
import com.mcmoddev.ihas.features.plugin.IProxy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy implements IProxy {
    @Override
    public void preInit () {
        FeatureDeployment.getLoadedFeatures().forEach(iihasFeature -> iihasFeature.getProxy().preInit());
    }

    @Override
    public void init () {
        FeatureDeployment.getLoadedFeatures().forEach(iihasFeature -> iihasFeature.getProxy().init());
    }

    @Override
    public void postInit () {
        FeatureDeployment.getLoadedFeatures().forEach(iihasFeature -> iihasFeature.getProxy().postInit());
    }
}
