package net.capozi.abyssal.items;
import net.capozi.abyssal.AbyssalBootstrap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AbyssalCombatItems {


    private static void addItemsToCombatTabItemGroup(FabricItemGroupEntries entries) {

    }

    public static void registerModItems() {
        AbyssalBootstrap.LOGGER.info("Registering Mod Items for " + AbyssalBootstrap.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(AbyssalCombatItems::addItemsToCombatTabItemGroup);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AbyssalBootstrap.MOD_ID, name), item);
    }
}
