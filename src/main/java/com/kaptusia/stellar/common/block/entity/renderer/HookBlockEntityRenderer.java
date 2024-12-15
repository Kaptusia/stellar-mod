package com.kaptusia.stellar.common.block.entity.renderer;

import com.kaptusia.stellar.common.block.entity.HookBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class HookBlockEntityRenderer implements BlockEntityRenderer<HookBlockEntity> {

    public HookBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(HookBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        int k = (int) entity.getPos().asLong();
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack itemStack = entity.getRenderStack();
        matrices.push();
        matrices.translate(0.5F, 0.44921875F, 0.5F);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(135.0F));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(0.0F));
        matrices.scale(0.45F, 0.45F, 0.45F);

        itemRenderer.renderItem(itemStack, ModelTransformationMode.FIXED, light, overlay, matrices, vertexConsumers, entity.getWorld(), k + light);
        matrices.pop();

    }
}
