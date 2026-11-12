package com.viruss.backrooms;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BackroomsMod.MOD_ID);

    public static final RegistryObject<Block> CARPET = BLOCKS.register("backrooms_carpet",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.4F)
                    .sound(SoundType.WOOL)));

    public static final RegistryObject<Block> WALLPAPER = BLOCKS.register("backrooms_wallpaper",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.8F)
                    .sound(SoundType.WOOL)));

    public static final RegistryObject<Block> CEILING = BLOCKS.register("backrooms_ceiling",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.0F)));

    public static final RegistryObject<Block> LAMP = BLOCKS.register("backrooms_lamp",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.0F)
                    .lightLevel(state -> 15)));

    public static final RegistryObject<Block> EXIT = BLOCKS.register("backrooms_exit",
            () -> new ExitBlock(BlockBehaviour.Properties.of()
                    .strength(-1.0F)
                    .noCollission()));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
