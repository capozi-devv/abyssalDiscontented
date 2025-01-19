package net.capozi.abyssal.block;

import net.capozi.abyssal.AbyssalBootstrap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AbyssalBlocks {
    public static final Block UNDERGROWTH = registerBlock("undergrowth",
            new Block(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK)));



    private static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(AbyssalBootstrap.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(AbyssalBootstrap.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
    public static void registerModBlocks() {
        AbyssalBootstrap.LOGGER.info("Registering Blocks for" + AbyssalBootstrap.MOD_ID);
    }
}
