package com.mcmoddev.ihas.features;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.mcmoddev.ihas.handlers.ConfigurationHandler;
import com.mcmoddev.lib.util.AnnotationChecker;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class FeatureDeployment {

    private static final Map<String, IIHASFeature> LOADED = Maps.newHashMap();

    private static Set<IIHASFeature> features;

    public static Set<IIHASFeature> getLoadedFeatures () {
        if (features == null)
            features = ImmutableSet.copyOf(LOADED.values());
        return features;
    }

    public static boolean isFeatureLoaded (String id) {
        return LOADED.containsKey(id);
    }

    public static void loadFeatures (FMLPreInitializationEvent event) {
        for (final IIHASFeature feature : AnnotationChecker.getInstances(event.getAsmData(), IHASFeature.class, IIHASFeature.class)) {
            final IHASFeature info = feature.getClass().getAnnotation(IHASFeature.class);
            if (ConfigurationHandler.isFeatureEnabled(feature, info)) {
                LOADED.put(info.featureID(), feature);
                if (feature.hasEvents())
                    MinecraftForge.EVENT_BUS.register(feature);
            }
        }
    }
}