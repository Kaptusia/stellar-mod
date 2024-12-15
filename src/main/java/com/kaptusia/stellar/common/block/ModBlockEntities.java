package com.kaptusia.stellar.common.block;

import com.kaptusia.stellar.Stellar;
import com.kaptusia.stellar.common.block.entity.HookBlockEntity;
import com.kaptusia.stellar.common.block.entity.StellarLampBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<StellarLampBlockEntity> STELLAR_LAMP_BLOCK_ENTITY_BLOCK_ENTITY_TYPE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(Stellar.ID, "stellar_lamp"),
            FabricBlockEntityTypeBuilder.create(StellarLampBlockEntity::new,
                    ModBlocks.STELLAR_LAMP).build());

    public static final BlockEntityType<HookBlockEntity> HOOK_BLOCK_ENTITY_BLOCK_ENTITY_TYPE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(Stellar.ID, "hook"),
            FabricBlockEntityTypeBuilder.create(HookBlockEntity::new,
                    ModBlocks.HOOK).build());

    public static void registerBlockEntities() {
        Stellar.LOGGER.info("Registering Block Entities for " + Stellar.ID);
    }

}