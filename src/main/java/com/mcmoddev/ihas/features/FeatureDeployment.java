package com.mcmoddev.ihas.features;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.mcmoddev.ihas.IHAS;
import com.mcmoddev.lib.util.AnnotationChecker;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class FeatureDeployment {

    private enum Stage {
        PRE_SETUP,
        SETUP,
        PRE_INIT,
        INIT,
        POST_INIT,
        FINISHED;

        public boolean hasPassedStage (Stage stage) {
            return stage.ordinal() <= this.ordinal();
        }
    }

    private static Set<IIHASFeature> LOADED = Sets.newHashSet();

    private static Set<IIHASFeature> UNLOADED = Sets.newHashSet();

    private static Set<IIHASFeature> CONF_DISABLED = Sets.newHashSet();

    private static Logger LOG = LogManager.getLogger(IHAS.MOD_NAME + "|Feature Deployment");

    private static Stage STAGE = Stage.PRE_SETUP;

    private static File CONF_DIR;

    public static Set<IIHASFeature> getLoadedFeatures () {
        return ImmutableSet.copyOf(LOADED);
    }

    public static Set<IIHASFeature> getUnloadedFeatures () {
        return ImmutableSet.copyOf(UNLOADED);
    }

    public static Stage getStage () {
        return STAGE;
    }

    public static boolean isFeatureLoaded (String id) {
        for (final IIHASFeature feature : getLoadedFeatures())
            if (feature.getClass().getAnnotation(IHASFeature.class).featureID().equals(id))
                return true;
        return false;
    }

    public static void preInit (FMLPreInitializationEvent event) {
        if (getStage().hasPassedStage(Stage.SETUP))
            return;
        STAGE = Stage.SETUP;
        CONF_DIR = event.getModConfigurationDirectory();
        final List<IIHASFeature> features = AnnotationChecker.getInstances(event.getAsmData(), IHASFeature.class, IIHASFeature.class);
        if (features.isEmpty())
            throw new NullPointerException();
        configure(features);
        if (getStage().hasPassedStage(Stage.PRE_INIT))
            return;
        STAGE = Stage.PRE_INIT;
        LOADED.forEach(IIHASFeature::preInit);
        LOADED.forEach(feature -> {
            if (feature.hasEvents())
                MinecraftForge.EVENT_BUS.register(feature);
        });
    }

    public static void init () {
        if (getStage().hasPassedStage(Stage.INIT))
            return;
        STAGE = Stage.INIT;
        LOADED.forEach(IIHASFeature::init);
    }

    public static void postInit () {
        if (getStage().hasPassedStage(Stage.POST_INIT))
            return;
        STAGE = Stage.POST_INIT;
        LOADED.forEach(IIHASFeature::postInit);
        STAGE = Stage.FINISHED;
    }

    private static void configure (List<IIHASFeature> features) {
        final Configuration config = new Configuration(new File(IHAS.CONFIG_DIR, "features.cfg"));
        config.load();
        config.setCategoryComment("features", "Disable certain features here");
        final Set<String> to = new HashSet<>();
        final ImmutableList<IIHASFeature> allFeatures = ImmutableList.copyOf(features);
        Iterator<IIHASFeature> iterator = features.iterator();
        while (iterator.hasNext()) {
            final IIHASFeature feature = iterator.next();
            if (feature.canBeDisabled())
                if (!isEnabled(config, feature)) {
                    iterator.remove();
                    LOG.info("Feature disabled: {}", feature);
                    continue;
                }
            final IHASFeature annotation = feature.getClass().getAnnotation(IHASFeature.class);
            to.add(annotation.featureID());
        }
        boolean changed;
        do {
            changed = false;
            iterator = features.iterator();
            while (iterator.hasNext()) {
                final IIHASFeature feature = iterator.next();
                final Set<String> dependencies = feature.getDependIds();
                if (dependencies != null && !dependencies.isEmpty() && !to.containsAll(dependencies)) {
                    iterator.remove();
                    changed = true;
                    final IHASFeature annotation = feature.getClass().getAnnotation(IHASFeature.class);
                    final String featureId = annotation.featureID();
                    to.remove(featureId);
                    LOG.warn("Feature {} is missing dependencies: {}", featureId, dependencies);
                }
            }
        }
        while (changed);
        LOADED.addAll(features);
        LOADED.forEach(feature -> LOG.info("Feature active: {}", feature.getClass().getAnnotation(IHASFeature.class).featureID()));
        UNLOADED.addAll(allFeatures);
        UNLOADED.removeAll(features);
        if (config.hasChanged())
            config.save();
    }

    private static boolean isEnabled (Configuration config, IIHASFeature feature) {
        final IHASFeature annotation = feature.getClass().getAnnotation(IHASFeature.class);
        final boolean enabled = config.get("features", annotation.featureID(), true, annotation.description()).getBoolean();
        if (!enabled)
            CONF_DISABLED.add(feature);
        return enabled;
    }
}