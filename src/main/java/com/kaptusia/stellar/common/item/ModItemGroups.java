package com.kaptusia.stellar.common.item;

import com.kaptusia.stellar.Stellar;
import com.kaptusia.stellar.common.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup STELLAR_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Stellar.ID, "stellar"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.stellar"))
                    .icon(() -> new ItemStack(ModItems.STELLAR_CRYSTAL)).entries((displayContext, entries) -> {
                        entries.add(ModItems.STELLAR_CRYSTAL);
                        entries.add(ModItems.STAR_INFUSED_POWDER);
                        entries.add(ModItems.TWIG);
                        entries.add(ModItems.WAND);
                        entries.add(ModItems.STAR_CLEAR_LENS);
                        entries.add(ModItems.STAR_CLEAR_GLASSES);


                        entries.add(ModBlocks.HOOK);
                        entries.add(ModBlocks.STAR_INFUSED_LAMP);
                        entries.add(ModBlocks.STAR_CLEAR_GLASS);


                        entries.add(ModBlocks.KAPTUSIA_PLUSHIE);
                    }).build());

    public static void registerItemGroups() {
        Stellar.LOGGER.info("Registering Item Groups for " + Stellar.ID);
    }
}