package com.sm;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.item.ItemGroups;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

public class ModItem {

    // First create the item
    public static final Item COPPER_SICKLE = new BlockBreakerItem(
            ToolMaterials.IRON,
            Blocks.TALL_GRASS,
            Blocks.SHORT_GRASS,
            new Item.Settings().maxDamage(200)
    );

    // Then register it
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Sicklemod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        // Register all items here
        registerItem("copper_sickle", COPPER_SICKLE);
        Sicklemod.LOGGER.info("Registering Mod Items for " + Sicklemod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.add(COPPER_SICKLE));
    }
}