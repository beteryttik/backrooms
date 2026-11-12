package com.viruss.backrooms;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Backrooms.MOD_ID)
public class Backrooms {
    public static final String MOD_ID = "virussbackrooms";

    public Backrooms() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.register(bus);
        ModGen.register();
    }
}
