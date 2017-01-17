package com.mcmoddev.ihas.proxy;

import com.mcmoddev.ihas.features.FeatureDeployment;
import com.mcmoddev.ihas.features.IProxy;

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
