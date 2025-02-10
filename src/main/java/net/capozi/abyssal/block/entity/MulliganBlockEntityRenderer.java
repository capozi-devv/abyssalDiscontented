package net.capozi.abyssal.block.entity;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.World;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.BlockState;




@Environment(EnvType.CLIENT)
public class MulliganBlockEntityRenderer implements BlockEntityRenderer<MulliganBlockEntity> {
    public MulliganBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Environment(EnvType.CLIENT)
    public BlockState getAppearance(BlockState state, BlockRenderView level, BlockPos pos, Direction side,
                                    BlockState queryState, BlockPos queryPos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof MulliganBlockEntity mulliganEntity) {
            BlockState mimicState = mulliganEntity.getMimicState();
            return mimicState != null ? mimicState : state; // Prevent null return
        }
        return state;
        var renderLayer = RenderLayers.getBlockLayer(mimicState);
        if (!RenderLayers.canRenderInLayer(mimicState, renderLayer)) {
            System.out.println("Incompatible render layer for " + mimicState);
        }

    }

    @Override
    public void render(MulliganBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (entity == null || entity.getWorld() == null) return;

        BlockState mimicState = entity.getMimicState();
        System.out.println("Rendering mimic block: " + mimicState);

        if (mimicState == null || mimicState.getBlock() == Blocks.AIR) {
            System.out.println("Invalid mimic state, skipping render.");
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        BlockRenderManager blockRenderManager = client.getBlockRenderManager();

        matrices.push();

        var model = blockRenderManager.getModel(mimicState);
        var renderLayer = net.minecraft.client.render.RenderLayers.getBlockLayer(mimicState);
        var vertexConsumer = vertexConsumers.getBuffer(renderLayer);

        blockRenderManager.getModelRenderer().render(
                entity.getWorld(),
                model,
                mimicState,
                entity.getPos(),
                matrices,
                vertexConsumer,
                false,
                entity.getWorld().random,
                mimicState.getRenderingSeed(entity.getPos()),
                overlay
        );

        matrices.pop();
    }

    public void debugMimicState(World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof MulliganBlockEntity mulliganEntity) {
            BlockState mimicState = mulliganEntity.getMimicState();
            System.out.println("Mimic Block State: " + (mimicState != null ? mimicState : "null"));
        } else {
            System.out.println("Block entity at " + pos + " is not a MulliganBlockEntity.");
        }
    }
}



