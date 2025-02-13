package net.capozi.abyssal.block.entity;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.RandomSplitter;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.World;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.BlockState;

@Environment(EnvType.CLIENT)
public class MulliganBlockEntityRenderer implements BlockEntityRenderer<MulliganBlockEntity> {
    public MulliganBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    RenderLayer renderLayer = RenderLayer.getTranslucent();

    public BlockState getAppearance(BlockState state, BlockRenderView level, BlockPos pos, Direction side,
                                    BlockState queryState, BlockPos queryPos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof MulliganBlockEntity mulliganEntity) {
            BlockState mimicState = mulliganEntity.getMimicState();
            if (mimicState != null) {
                System.out.println("Warning: Invalid Mimic Block State: " + mimicState);
                RenderLayer renderLayer = RenderLayers.getBlockLayer(mimicState);
                if (renderLayer == null) {
                    System.out.println("Warning: Render layer is null for " + mimicState);
                }
                return mimicState;
            }
        }
        return state; // Return the default state if mimicState is null
    }

    @Override
    public void render(MulliganBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (entity == null || entity.getWorld() == null) return;

        BlockState mimicState = entity.getMimicState();
        if (mimicState == null || mimicState.getBlock() == Blocks.AIR) {
            System.out.println("Invalid mimic state, skipping render.");
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        BlockRenderManager blockRenderManager = client.getBlockRenderManager();

        matrices.push();

        // Force the block to render all faces
        matrices.translate(0, 0, 0);

        // Get the correct render layer (solid, cutout, or translucent) and properly handle translucency
        RenderLayer renderLayer = RenderLayers.getBlockLayer(mimicState);
        if (renderLayer == RenderLayer.getTranslucent()) {
            DiffuseLighting.disableGuiDepthLighting(); // Helps with transparency issues
        }
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(renderLayer);

        // Render the mimicked block
        blockRenderManager.getModelRenderer().render(
                entity.getWorld(),
                blockRenderManager.getModel(mimicState),
                mimicState,
                entity.getPos(),
                matrices,
                vertexConsumer,
                false,
                new Random() {
                    @Override
                    public Random split() {
                        return null;
                    }

                    @Override
                    public RandomSplitter nextSplitter() {
                        return null;
                    }

                    @Override
                    public void setSeed(long seed) {

                    }

                    @Override
                    public int nextInt() {
                        return 0;
                    }

                    @Override
                    public int nextInt(int bound) {
                        return 0;
                    }

                    @Override
                    public long nextLong() {
                        return 0;
                    }

                    @Override
                    public boolean nextBoolean() {
                        return false;
                    }

                    @Override
                    public float nextFloat() {
                        return 0;
                    }

                    @Override
                    public double nextDouble() {
                        return 0;
                    }

                    @Override
                    public double nextGaussian() {
                        return 0;
                    }
                },
                mimicState.getRenderingSeed(entity.getPos()),
                overlay
        );

        matrices.pop();
        if (renderLayer == RenderLayer.getTranslucent()) {
            DiffuseLighting.enableGuiDepthLighting();
        }
    }


    public void debugMimicState(World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof MulliganBlockEntity mulliganEntity) {
            BlockState mimicState = mulliganEntity.getMimicState();
            System.out.println("Mimic Block State: " + (mimicState != null ? mimicState : "null"));
        } else {
            System.out.println("Block entity at " + pos + " is not a Mulligan or is a Mulligan that has rendered with a Null value.");
        }
    }
}