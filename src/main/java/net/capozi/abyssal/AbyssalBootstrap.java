package net.capozi.abyssal;

import net.capozi.abyssal.block.AbyssalBlockEntityRendererBootstrap;
import net.capozi.abyssal.block.AbyssalBlocksBootstrap;
import net.capozi.abyssal.block.AbyssalBlockEntityBootstrap;
import net.capozi.abyssal.item.AbyssalItemGroups;
import net.capozi.abyssal.item.AbyssalItemsBootstrap;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbyssalBootstrap implements ModInitializer {
	public static final String MOD_ID = "abyssal";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		AbyssalItemsBootstrap.registerModItems();
		AbyssalBlocksBootstrap.registerModBlocks();
		AbyssalItemGroups.registerItemGroups();
		AbyssalBlockEntityBootstrap.registerBlockEntities();
		AbyssalBlockEntityRendererBootstrap.registerBlockEntityRenderers();
	}
}