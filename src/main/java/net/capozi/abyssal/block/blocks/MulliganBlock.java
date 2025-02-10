package net.capozi.abyssal.block.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.capozi.abyssal.block.entity.MulliganBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MulliganBlock extends Block implements BlockEntityProvider {
    public MulliganBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MulliganBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, @NotNull World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof MulliganBlockEntity mulliganEntity) {
                ItemStack heldItem = player.getStackInHand(hand);
                if (heldItem.getItem() instanceof BlockItem blockItem) {
                    BlockState newMimicState = blockItem.getBlock().getDefaultState();

                    mulliganEntity.setMimicState(newMimicState);
                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.PASS;
    }

    public void onPlaced(@NotNull World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (placer != null) {
            ItemStack offhandItem = placer.getStackInHand(Hand.OFF_HAND);
            if (offhandItem.getItem() instanceof BlockItem blockItem) {
                BlockState mimicState = blockItem.getBlock().getDefaultState();
                BlockEntity be = world.getBlockEntity(pos);
                if (be instanceof MulliganBlockEntity mulliganEntity) {
                    mulliganEntity.setMimicState(mimicState);

                    if (placer instanceof PlayerEntity player && !player.isCreative()) {
                        offhandItem.decrement(1);
                    }
                }
            }
        }
    }

    @Override
    public void onBreak(@NotNull World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof MulliganBlockEntity mulliganEntity) {
            ItemStack droppedItem = new ItemStack(mulliganEntity.getMimicState().getBlock());
            if (!player.isCreative()) {
                dropStack(world, pos, droppedItem);
            }
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, @NotNull BlockView world, BlockPos pos, ShapeContext context) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof MulliganBlockEntity mulliganEntity) {
            return mulliganEntity.getMimicState().getOutlineShape(world, pos, context);
        }
        return super.getOutlineShape(state, world, pos, context);
    }


    public float getHardness(BlockState state, @NotNull World world, BlockPos pos) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof MulliganBlockEntity mulliganEntity) {
            return mulliganEntity.getMimicState().getHardness(world, pos);
        }
        return super.getHardness();
    }

    @Override
    public BlockSoundGroup getSoundGroup(BlockState state) {
        return super.getSoundGroup(state);
    }

    @Override
    public int getOpacity(BlockState state, @NotNull BlockView world, BlockPos pos) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof MulliganBlockEntity mulliganEntity) {
            return mulliganEntity.getMimicState().getOpacity(world, pos);
        }
        return super.getOpacity(state, world, pos);
    }
}

