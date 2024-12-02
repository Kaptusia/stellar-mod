package com.kaptusia.stellar.common.block;

import com.kaptusia.stellar.Stellar;
import com.kaptusia.stellar.common.block.custom.KaptusiaPlushieBlock;
import com.kaptusia.stellar.common.block.custom.StellarGlassBlock;
import com.kaptusia.stellar.common.block.custom.StellarLampBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
public class ModBlocks {

    public static final Block STELLAR_GLASS = registerBlock(
            "stellar_glass",
            new StellarGlassBlock(FabricBlockSettings.copyOf(Blocks.GLASS)
                    .instrument(Instrument.CHIME)
                    .strength(0.35F)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .allowsSpawning(Blocks::never)
                    .solidBlock(Blocks::never)
                    .suffocates(Blocks::never)
                    .blockVision(Blocks::never)
                    .luminance(8)
            ));

    public static final Block KAPTUSIA_PLUSHIE = registerBlock("kaptusia_plushie",
            new KaptusiaPlushieBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).nonOpaque()));

    public static final Block STELLAR_LAMP = registerBlock("stellar_lamp",
            new StellarLampBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).nonOpaque().luminance((state) -> 7).strength(0.5F)
            ));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Stellar.ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Stellar.ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Stellar.LOGGER.info("Registering Mod Blocks for " + Stellar.ID);
    }


}