package com.viruss.backrooms;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BackroomsMod.MODID);

    public static final RegistryObject<Block> CARPET = BLOCKS.register("backrooms_carpet",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW).strength(0.8F).sound(SoundType.WOOL)));

    public static final RegistryObject<Block> WALLPAPER = BLOCKS.register("backrooms_wallpaper",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW).strength(1.0F).sound(SoundType.WOOL)));

    public static final RegistryObject<Block> CEILING = BLOCKS.register("backrooms_ceiling",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.QUARTZ).strength(1.5F).sound(SoundType.STONE)));

    public static final RegistryObject<Block> LAMP = BLOCKS.register("backrooms_lamp",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.QUARTZ).strength(1.0F).lightLevel(s -> 15).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> EXIT = BLOCKS.register("backrooms_exit",
            () -> new ExitBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK).strength(-1.0F, 3600000.0F).lightLevel(s -> 7)));
}
