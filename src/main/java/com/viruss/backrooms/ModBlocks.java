package com.viruss.backrooms;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Backrooms.MOD_ID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Backrooms.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Backrooms.MOD_ID);

    public static final RegistryObject<Block> WET_CARPET = BLOCKS.register("wet_carpet",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.4F)
                    .sound(SoundType.WOOL)));

    public static final RegistryObject<Block> YELLOW_WALLPAPER = BLOCKS.register("yellow_wallpaper",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8F)
                    .sound(SoundType.WOOL)));

    public static final RegistryObject<Block> CEILING_PANEL = BLOCKS.register("ceiling_panel",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.0F)));

    public static final RegistryObject<Block> LAMP = BLOCKS.register("lamp",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.0F)
                    .lightLevel(state -> 15)));

    public static final RegistryObject<Block> GLITCH = BLOCKS.register("glitch",
            () -> new ExitBlock(BlockBehaviour.Properties.of()
                    .strength(-1.0F)
                    .noCollission()));

    private static RegistryObject<Item> itemOf(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().getPath(),
                () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static final RegistryObject<Item> WET_CARPET_ITEM = itemOf(WET_CARPET);
    public static final RegistryObject<Item> YELLOW_WALLPAPER_ITEM = itemOf(YELLOW_WALLPAPER);
    public static final RegistryObject<Item> CEILING_PANEL_ITEM = itemOf(CEILING_PANEL);
    public static final RegistryObject<Item> LAMP_ITEM = itemOf(LAMP);
    public static final RegistryObject<Item> GLITCH_ITEM = itemOf(GLITCH);

    public static final RegistryObject<CreativeModeTab> BACKROOMS_TAB = TABS.register("backrooms",
            () -> CreativeModeTab.builder()
                    .title(Component.literal("The Backrooms"))
                    .icon(() -> new ItemStack(GLITCH_ITEM.get()))
                    .displayItems((params, output) -> {
                        output.accept(WET_CARPET_ITEM.get());
                        output.accept(YELLOW_WALLPAPER_ITEM.get());
                        output.accept(CEILING_PANEL_ITEM.get());
                        output.accept(LAMP_ITEM.get());
                        output.accept(GLITCH_ITEM.get());
                    })
                    .build());

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        TABS.register(bus);
    }
}
