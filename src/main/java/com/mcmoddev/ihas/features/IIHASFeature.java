package com.mcmoddev.ihas.features;

import com.google.common.collect.Sets;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

public interface IIHASFeature {
	
    default boolean canBeDisabled () {
        return true;
    }

    default Set<String> getDependIds () {
        return Sets.newHashSet("core");
    }

    void preInit ();

    void init ();

    void postInit ();
    
    @SideOnly(Side.CLIENT)
    void clientPreInit ();

    @SideOnly(Side.CLIENT)
    void clientInit ();

    @SideOnly(Side.CLIENT)
    void clientPostInit ();
    
    boolean hasEvents();
}