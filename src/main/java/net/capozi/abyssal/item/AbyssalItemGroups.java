package net.capozi.abyssal.item;

import net.capozi.abyssal.Abyssal;
import net.capozi.abyssal.block.AbyssalBlocksBootstrap;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AbyssalItemGroups {
    public static final ItemGroup ABYSSAL_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Abyssal.MOD_ID, "abyssal"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.abyssal"))
                    .icon(() -> new ItemStack(AbyssalBlocksBootstrap.MULLIGAN_BLOCK)).entries((displayContext, entries) -> {
                        entries.add(AbyssalBlocksBootstrap.MULLIGAN_BLOCK);
                        entries.add(AbyssalItemsBootstrap.SLUMBERING_RELIC);
                    }).build());


    public static void registerItemGroups() {
        Abyssal.LOGGER.info("Registering Item Groups For" + Abyssal.MOD_ID);
    }
}
