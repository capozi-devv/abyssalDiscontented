package net.capozi.abyssal.block;

import net.capozi.abyssal.AbyssalBootstrap;
import net.capozi.abyssal.block.entity.MulliganBlockEntityRenderer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class AbyssalBlockEntityRendererBootstrap {
    public static void registerBlockEntityRenderers() {
        AbyssalBootstrap.LOGGER.info("Registering Block Entity Renderers for " + AbyssalBootstrap.MOD_ID);
        BlockEntityRendererFactories.register(
                AbyssalBlockEntityBootstrap.MULLIGAN_BLOCK_ENTITY,
                MulliganBlockEntityRenderer::new
        );
        BlockRenderLayerMap.INSTANCE.putBlock(AbyssalBlocksBootstrap.MULLIGAN_BLOCK, RenderLayer.getTranslucent());
    }
}
