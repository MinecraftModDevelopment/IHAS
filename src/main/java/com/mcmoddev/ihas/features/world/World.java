package com.mcmoddev.ihas.features.world;

import com.google.common.collect.Sets;
import com.mcmoddev.ihas.features.BlankIHasFeature;
import com.mcmoddev.ihas.features.IHASFeature;

import java.util.Set;

@IHASFeature(featureID = "world", name = "World", version = "1.0.0")
public class World extends BlankIHasFeature {

    @Override
    public Set<String> getDependIds () {
        return Sets.newHashSet("core");
    }

    @Override
    public boolean canBeDisabled () {
        return true;
    }
}