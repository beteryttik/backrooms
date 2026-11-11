package com.viruss.backrooms;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.core.Registry;

public class ModGen {
    public static final ResourceKey<Level> BACKROOMS_LEVEL =
            ResourceKey.create(Registries.DIMENSION, new ResourceLocation(BackroomsMod.MODID, "backrooms"));
    public static final ResourceKey<Biome> BACKROOMS_BIOME =
            ResourceKey.create(Registries.BIOME, new ResourceLocation(BackroomsMod.MODID, "backrooms"));

    private ModGen() {
    }

    public static void register() {
        Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
                new ResourceLocation(BackroomsMod.MODID, "backrooms"), BackroomsGenerator.CODEC);
    }
}
