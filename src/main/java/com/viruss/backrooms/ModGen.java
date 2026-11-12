package com.viruss.backrooms;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModGen {
    public static final String MOD_ID = "backrooms";

    // Ключ измерения. Должен совпадать с путём JSON:
    // data/backrooms/dimension/backrooms.json  ->  backrooms:backrooms
    public static final ResourceKey<Level> BACKROOMS_LEVEL =
            ResourceKey.create(Registries.DIMENSION, new ResourceLocation(MOD_ID, "backrooms"));

    private static final DeferredRegister<Codec<? extends ChunkGenerator>> GENERATORS =
            DeferredRegister.create(Registries.CHUNK_GENERATOR, MOD_ID);

    public static final RegistryObject<Codec<? extends ChunkGenerator>> BACKROOMS_CODEC =
            GENERATORS.register("backrooms", () -> BackroomsGenerator.CODEC);

    public static void register(IEventBus bus) {
        GENERATORS.register(bus);
    }
}
