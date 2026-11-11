package com.viruss.backrooms.client;

import com.viruss.backrooms.BackroomsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;

public class ClientSetup {
    public static void registerEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(new ResourceLocation(BackroomsMod.MODID, "backrooms"), new BackroomsFog());
    }
}
