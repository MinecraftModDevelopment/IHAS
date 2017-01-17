package com.mcmoddev.ihas;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Stopwatch;
import com.mcmoddev.ihas.features.FeatureDeployment;
import com.mcmoddev.ihas.proxy.IProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = IHAS.MOD_ID,
        name = IHAS.MOD_NAME,
        version = IHAS.VERSION
)
public class IHAS {

    public static final String MOD_ID = "ihas";
    public static final String MOD_NAME = "I Have A Suggestion";
    public static final String VERSION = "1.0.0";
    public static final Logger LOG = LogManager.getLogger(MOD_NAME + "|Main");

    @SidedProxy(clientSide = "com.mcmoddev.ihas.proxy.ProxyClient", serverSide = "com.mcmoddev.ihas.proxy.ProxyServer")
    public static IProxy proxy;
    
    public static File CONFIG_DIR;

    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        LOG.info("Pre Initialization (Started)");
        CONFIG_DIR = new File(event.getModConfigurationDirectory(), MOD_ID);
        FeatureDeployment.preInit(event);
        proxy.preInit();
        LOG.info("Pre Initialization (Ended after " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms)");
        stopwatch.stop();
    }

    @EventHandler
    public void init (FMLInitializationEvent event) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        LOG.info("Initialization (Started)");
        FeatureDeployment.init();
        proxy.init();
        LOG.info("Initialization (Ended after " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms)");
        stopwatch.stop();
    }

    @EventHandler
    public void init (FMLPostInitializationEvent event) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        LOG.info("Post Initialization (Started)");
        FeatureDeployment.postInit();
        proxy.postInit();
        LOG.info("Post Initialization (Ended after " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms)");
        stopwatch.stop();
    }
}