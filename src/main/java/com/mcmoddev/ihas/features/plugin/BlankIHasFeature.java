package com.mcmoddev.ihas.features.plugin;

import com.google.common.collect.Sets;

import java.util.Set;

public abstract class BlankIHasFeature implements IIHASFeature {
    @Override
    public boolean canBeDisabled () {
        return true;
    }

    @Override
    public Set<String> getDependIds () {
        return Sets.newHashSet("core");
    }

    @Override
    public void preInit () {

    }

    @Override
    public void init () {

    }

    @Override
    public void postInit () {

    }

    @Override
    public abstract IProxy getProxy ();

    @Override
    public Object getEventHandler () {
        return null;
    }
}