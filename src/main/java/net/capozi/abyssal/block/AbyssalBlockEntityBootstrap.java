package net.capozi.abyssal.block;

import net.capozi.abyssal.block.entity.MulliganBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;


public class AbyssalBlockEntityBootstrap {
    public static final BlockEntityType<MulliganBlockEntity> MULLIGAN_BLOCK_ENTITY =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    new Identifier("abyssal", "mulligan_block"),
                    FabricBlockEntityTypeBuilder.create(MulliganBlockEntity::new, AbyssalBlocksBootstrap.MULLIGAN_BLOCK).build()
            );

    public static void registerBlockEntities() {

    }

}
