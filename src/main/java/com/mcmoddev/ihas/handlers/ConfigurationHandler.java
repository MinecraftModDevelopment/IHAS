package com.mcmoddev.ihas.handlers;

import java.io.File;

import com.mcmoddev.ihas.features.FeatureDeployment;
import com.mcmoddev.ihas.features.IHASFeature;
import com.mcmoddev.ihas.features.IIHASFeature;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

    private static Configuration config;

    public static Configuration initConfig (File file) {
        config = new Configuration(file);
        return config;
    }

    public static void syncConfigData () {
        config.setCategoryComment("_features", "Allows features to be completely disabled");
        for (final IIHASFeature feature : FeatureDeployment.getLoadedFeatures())
            feature.setupConfiguration(config);
        if (config.hasChanged())
            config.save();
    }

    public static boolean isFeatureEnabled (IIHASFeature feature, IHASFeature info) {
        if (!feature.canBeDisabled())
            return true;
        return config.getBoolean(info.name(), "_features", feature.enabledByDefault(), info.description());
    }
}