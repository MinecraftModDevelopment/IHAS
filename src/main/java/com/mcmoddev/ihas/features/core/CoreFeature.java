package com.mcmoddev.ihas.features.core;

import com.google.common.collect.Sets;
import com.mcmoddev.ihas.features.BlankIHasFeature;
import com.mcmoddev.ihas.features.IHASFeature;

import java.util.Set;

@IHASFeature(featureID = "core", name = "Core", version = "1.0.0")
public class CoreFeature extends BlankIHasFeature {

    @Override
    public Set<String> getDependIds () {
        return Sets.newHashSet();
    }

    @Override
    public boolean canBeDisabled () {
        return false;
    }
}