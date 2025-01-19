package net.capozi.abyssal;

import net.capozi.abyssal.block.AbyssalBlocks;
import net.capozi.abyssal.items.AbyssalCombatItems;
import net.capozi.abyssal.items.AbyssalFoods;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbyssalBootstrap implements ModInitializer {
	public static final String MOD_ID = "abyssal";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		AbyssalCombatItems.registerModItems();
		AbyssalBlocks.registerModBlocks();
	}
}