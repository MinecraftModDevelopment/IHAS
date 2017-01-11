package com.mcmoddev.ihas.features.world;

import com.google.common.collect.Sets;
import com.mcmoddev.ihas.features.plugin.BlankIHasFeature;
import com.mcmoddev.ihas.features.plugin.IHASFeature;
import com.mcmoddev.ihas.features.plugin.IProxy;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

@IHASFeature(featureID = "world", name = "World", version = "1.0.0")
public class World extends BlankIHasFeature {

    @SidedProxy(clientSide = "com.mcmoddev.ihas.features.world.WorldClientProxy", serverSide = "com.mcmoddev.ihas.features.world.WorldServerProxy")
    public static IProxy PROXY;

    @Override
    public Set<String> getDependIds () {
        return Sets.newHashSet("core");
    }

    @Override
    public boolean canBeDisabled () {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IProxy getProxy () {
        return PROXY;
    }

}