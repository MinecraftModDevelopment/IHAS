package com.mcmoddev.ihas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mcmoddev.ihas.features.FeatureDeployment;
import com.mcmoddev.ihas.features.IIHASFeature;
import com.mcmoddev.ihas.handlers.ConfigurationHandler;
import com.mcmoddev.ihas.proxy.IProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = IHAS.MOD_ID, name = IHAS.MOD_NAME, version = IHAS.VERSION)
public class IHAS {

    public static final String MOD_ID = "ihas";

    public static final String MOD_NAME = "I Have A Suggestion";

    public static final String VERSION = "1.0.0";

    public static final Logger LOG = LogManager.getLogger(MOD_NAME);

    @SidedProxy(clientSide = "com.mcmoddev.ihas.proxy.ProxyClient", serverSide = "com.mcmoddev.ihas.proxy.ProxyServer")
    public static IProxy proxy;

    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        ConfigurationHandler.initConfig(event.getSuggestedConfigurationFile());
        FeatureDeployment.loadFeatures(event);
        ConfigurationHandler.syncConfigData();
        for (final IIHASFeature feature : FeatureDeployment.getLoadedFeatures())
            feature.preInit();
        for (final IIHASFeature feature : FeatureDeployment.getLoadedFeatures())
            feature.setupRecipes();
        proxy.preInit();
    }

    @EventHandler
    public void init (FMLInitializationEvent event) {
        for (final IIHASFeature feature : FeatureDeployment.getLoadedFeatures())
            feature.init();
        proxy.init();
    }

    @EventHandler
    public void postInit (FMLPostInitializationEvent event) {
        for (final IIHASFeature feature : FeatureDeployment.getLoadedFeatures())
            feature.postInit();
        proxy.postInit();
    }
}