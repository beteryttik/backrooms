package com.viruss.backrooms;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BackroomsMod.MODID);

    public static final RegistryObject<Item> CARPET_ITEM =
            ITEMS.register("backrooms_carpet", () -> new BlockItem(ModBlocks.CARPET.get(), new Item.Properties()));
    public static final RegistryObject<Item> WALLPAPER_ITEM =
            ITEMS.register("backrooms_wallpaper", () -> new BlockItem(ModBlocks.WALLPAPER.get(), new Item.Properties()));
    public static final RegistryObject<Item> CEILING_ITEM =
            ITEMS.register("backrooms_ceiling", () -> new BlockItem(ModBlocks.CEILING.get(), new Item.Properties()));
    public static final RegistryObject<Item> LAMP_ITEM =
            ITEMS.register("backrooms_lamp", () -> new BlockItem(ModBlocks.LAMP.get(), new Item.Properties()));
    public static final RegistryObject<Item> EXIT_ITEM =
            ITEMS.register("backrooms_exit", () -> new BlockItem(ModBlocks.EXIT.get(), new Item.Properties()));
}
