package net.capozi.abyssal.item;

import nazario.liby.registry.auto.LibyAutoRegister;
import nazario.liby.registry.helper.LibyItemRegister;

import net.capozi.abyssal.Abyssal;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;

import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

@LibyAutoRegister
public class AbyssalItemsBootstrap {
    public static final LibyItemRegister REGISTER = new LibyItemRegister(Abyssal.MOD_ID);

    public static final Item SLUMBERING_RELIC = registerItem("slumbering_relic",
            new SlumberingRelic(ToolMaterials.DIAMOND, 9, -2.4f, new FabricItemSettings()));

    public static void registerModItems() {
        Abyssal.LOGGER.info("Registering Mod Items for " + Abyssal.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.add(SLUMBERING_RELIC));
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Abyssal.MOD_ID, name), item);
    }
}
