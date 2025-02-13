package net.capozi.abyssal.block.entity;

import net.capozi.abyssal.block.AbyssalBlockEntityBootstrap;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.registry.Registries;

public class MulliganBlockEntity extends BlockEntity {
    public BlockState mimicState;
    public ItemStack consumedItem;

    public MulliganBlockEntity(BlockPos pos, BlockState state) {
        super(AbyssalBlockEntityBootstrap.MULLIGAN_BLOCK_ENTITY, pos, state);
        this.mimicState = state; // Use the block entity's initial state instead
        this.consumedItem = ItemStack.EMPTY;
    }

    public BlockState getMimicState() {
        return mimicState != null ? mimicState : Blocks.AIR.getDefaultState();
    }

    public void setMimicState(BlockState newState) {
        if (newState != null && newState.getBlock() != this.getCachedState().getBlock()) {
            this.mimicState = newState;
            markDirty();
            if (world != null && !world.isClient) {
                world.updateListeners(pos, getCachedState(), getCachedState(), 3);
                world.updateNeighborsAlways(pos, this.getCachedState().getBlock()); // Forces light recalculation
            }
        }

        if (mimicState == newState) return;

        this.mimicState = newState;
        markDirty(); // Marks block entity as updated

        if (world != null && !world.isClient) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    public ItemStack getConsumedItem() {
        return consumedItem;
    }

    public void setConsumedItem(ItemStack stack) {
        consumedItem = stack.copy();
        consumedItem.setCount(1);
        markDirty();
    }

    //Write NBT Data
    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (mimicState != null) {
            nbt.put("MimicBlock", NbtHelper.fromBlockState(mimicState));
        }
    }

    //Read the NBT Data stored by the write Method
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("MimicBlock")) {
            this.mimicState = NbtHelper.toBlockState(Registries.BLOCK.getReadOnlyWrapper(), nbt.getCompound("MimicBlock"));
        }
    }

    // Call to update rendering data
    private void redraw() {
        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 16);
        }
    }

    // Updates block texture when right-clicked with a new block
    public void updateMimicTexture(ItemStack heldItem) {
        if (!(heldItem.getItem() instanceof BlockItem blockItem)) return;
        BlockState newMimicState = blockItem.getBlock().getDefaultState();
        setMimicState(newMimicState);
    }

    public BlockState getRenderAttachmentData() {
        return mimicState;
    }
}
