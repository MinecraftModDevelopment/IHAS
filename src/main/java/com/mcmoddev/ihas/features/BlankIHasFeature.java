package com.mcmoddev.ihas.features;

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
	public boolean hasEvents() {
		
		return false;
	}
	
    public void clientPreInit () {
    	
    }

    public void clientInit () {
    	
    }

    public void clientPostInit () {
    	
    }
}