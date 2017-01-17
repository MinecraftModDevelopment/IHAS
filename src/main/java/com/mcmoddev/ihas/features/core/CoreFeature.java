package com.mcmoddev.ihas.features.core;

import java.util.Set;

import com.google.common.collect.Sets;
import com.mcmoddev.ihas.features.IHASFeature;
import com.mcmoddev.ihas.features.IIHASFeature;

@IHASFeature(featureID = "core", name = "Core", version = "1.0.0")
public class CoreFeature implements IIHASFeature {

    @Override
    public Set<String> getDependIds () {
        return Sets.newHashSet();
    }

    @Override
    public boolean canBeDisabled () {
        return false;
    }
}