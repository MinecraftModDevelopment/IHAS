package com.mcmoddev.ihas.features;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IIHASFeature {
	
    default boolean canBeDisabled () {
        return true;
    }

    default Set<String> getDependIds () {
        return Sets.newHashSet("core");
    }

    default void preInit () {
    	
    }

    default void init () {
    	
    }

    default void postInit () {
    	
    }
    
    @SideOnly(Side.CLIENT)
    default void clientPreInit () {
    	
    }

    @SideOnly(Side.CLIENT)
    default void clientInit () {
    	
    }

    @SideOnly(Side.CLIENT)
    default void clientPostInit () {
    	
    }
    
    default boolean hasEvents() {
    	
    	return false;
    }
}