package com.mcmoddev.ihas.proxy;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public class ProxyServer implements IProxy {

    @Override
    public void preInit () {
    }

    @Override
    public void init () {
    }

    @Override
    public void postInit () {
    }
}