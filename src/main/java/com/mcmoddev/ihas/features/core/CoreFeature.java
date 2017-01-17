package com.mcmoddev.ihas.features.core;

import com.google.common.collect.Sets;
import com.mcmoddev.ihas.features.BlankIHasFeature;
import com.mcmoddev.ihas.features.IHASFeature;
import com.mcmoddev.ihas.features.IProxy;

import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

@IHASFeature(featureID = "core", name = "Core", version = "1.0.0")
public class CoreFeature extends BlankIHasFeature {

    @SidedProxy(clientSide = "com.mcmoddev.ihas.features.core.CoreClientProxy", serverSide = "com.mcmoddev.ihas.features.core.CoreServerProxy")
    public static IProxy PROXY;

    @Override
    public Set<String> getDependIds () {
        return Sets.newHashSet();
    }

    @Override
    public boolean canBeDisabled () {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IProxy getProxy () {
        return PROXY;
    }
}