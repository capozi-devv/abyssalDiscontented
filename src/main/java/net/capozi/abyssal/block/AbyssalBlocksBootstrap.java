package net.capozi.abyssal.block;

import nazario.liby.registry.auto.LibyAutoRegister;
import nazario.liby.registry.helper.LibyBlockRegister;

import net.capozi.abyssal.Abyssal;
import net.capozi.abyssal.block.blocks.MulliganBlock;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.block.AbstractBlock;

import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

@LibyAutoRegister
public class AbyssalBlocksBootstrap {
    public static final LibyBlockRegister REGISTER = new LibyBlockRegister(Abyssal.MOD_ID);

    public static final Block MULLIGAN_BLOCK = registerBlock("mulligan_block",
            new MulliganBlock(AbstractBlock.Settings.create()
                    .strength(2.0f)
                    .requiresTool()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Abyssal.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Abyssal.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Abyssal.LOGGER.info("Registering ModBlocks for " + Abyssal.MOD_ID);
    }

}
