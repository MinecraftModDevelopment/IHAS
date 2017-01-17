package com.mcmoddev.ihas.proxy;

import com.mcmoddev.ihas.features.FeatureDeployment;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ProxyClient implements IProxy {

    @Override
    public void preInit () {
        FeatureDeployment.getLoadedFeatures().forEach(iihasFeature -> iihasFeature.clientPreInit());
    }

    @Override
    public void init () {
        FeatureDeployment.getLoadedFeatures().forEach(iihasFeature -> iihasFeature.clientInit());
    }

    @Override
    public void postInit () {
        FeatureDeployment.getLoadedFeatures().forEach(iihasFeature -> iihasFeature.clientPostInit());
    }
}
