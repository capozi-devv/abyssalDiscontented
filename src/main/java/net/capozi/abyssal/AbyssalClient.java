package net.capozi.abyssal;

import net.capozi.abyssal.block.AbyssalBlockEntityRendererBootstrap;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class AbyssalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AbyssalBlockEntityRendererBootstrap.registerBlockEntityRenderers();
    }
}
