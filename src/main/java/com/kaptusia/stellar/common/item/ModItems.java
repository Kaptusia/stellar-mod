package com.kaptusia.stellar.common.item;

import com.kaptusia.stellar.Stellar;
import com.kaptusia.stellar.common.item.custom.StellarCrystalItem;
import com.kaptusia.stellar.common.item.custom.StellarPowderItem;
import com.kaptusia.stellar.common.item.custom.StellarGlassesItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item STELLAR_CRYSTAL = registerItem("stellar_crystal", new StellarCrystalItem(new Item.Settings()));

    public static final Item STELLAR_POWDER = registerItem("stellar_powder", new StellarPowderItem(new Item.Settings()));

    public static final Item STELLAR_LENS = registerItem("stellar_lens", new Item(new Item.Settings()));

    public static final Item STELLAR_GLASSES = registerItem("stellar_glasses", new StellarGlassesItem(new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item) {

        return Registry.register(Registries.ITEM, Identifier.of(Stellar.ID, name), item);
    }

    public static void registerModItems() {
        Stellar.LOGGER.info("Registering Mod Items for " + Stellar.ID);
    }
}