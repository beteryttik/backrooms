package com.viruss.backrooms;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ModGen {
    // ВПИШИ СЮДА СВОЙ MOD ID (см. ниже, как узнать)
    public static final String MOD_ID = "virussbackrooms";

    public static void register() {
        Registry.register(BuiltInRegistries.CHUNK_GENERATOR,
                new ResourceLocation(MOD_ID, "backrooms"),
                BackroomsGenerator.CODEC);
    }
}
