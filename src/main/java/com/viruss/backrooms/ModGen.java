package com.viruss.backrooms;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class ModGen {
    public static final String MOD_ID = "virussbackrooms";

    // Ключ измерения. Должен совпадать с путём JSON:
    // data/virussbackrooms/dimension/backrooms.json  ->  virussbackrooms:backrooms
    public static final ResourceKey<Level> BACKROOMS_LEVEL =
            ResourceKey.create(Registries.DIMENSION, new ResourceLocation(MOD_ID, "backrooms"));

    public static void register() {
        Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
                new ResourceLocation(MOD_ID, "backrooms"),
                BackroomsGenerator.CODEC);
    }
}
