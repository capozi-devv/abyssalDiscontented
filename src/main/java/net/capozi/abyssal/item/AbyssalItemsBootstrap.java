package net.capozi.abyssal.item;

import net.capozi.abyssal.AbyssalBootstrap;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AbyssalItemsBootstrap {

    public static void registerModItems() {
        AbyssalBootstrap.LOGGER.info("Registering Mod Items for " + AbyssalBootstrap.MOD_ID);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AbyssalBootstrap.MOD_ID, name), item);
    }
}
