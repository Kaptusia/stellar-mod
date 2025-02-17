package com.kaptusia.stellar;

import com.kaptusia.stellar.common.block.ModBlockEntities;
import com.kaptusia.stellar.common.block.ModBlocks;
import com.kaptusia.stellar.common.block.entity.renderer.HookBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class StellarClient implements ClientModInitializer {

    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STAR_CLEAR_GLASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.KAPTUSIA_PLUSHIE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STAR_INFUSED_LAMP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HOOK, RenderLayer.getCutout());

        BlockEntityRendererFactories.register(ModBlockEntities.HOOK_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, HookBlockEntityRenderer::new);
    }
}
