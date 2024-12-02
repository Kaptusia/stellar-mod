package com.kaptusia.stellar;

import com.kaptusia.stellar.common.block.ModBlockEntities;
import com.kaptusia.stellar.common.block.ModBlocks;
import com.kaptusia.stellar.common.item.ModItemGroups;
import com.kaptusia.stellar.common.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stellar implements ModInitializer {

    public static final String ID = "stellar";
    public static final Logger LOGGER = LoggerFactory.getLogger("stellar");

    public void onInitialize() {
        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();
    }
}