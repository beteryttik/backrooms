package com.viruss.backrooms;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BackroomsMod.MOD_ID)
public class BackroomsMod {
    public static final String MOD_ID = "virussbackrooms";

    public BackroomsMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.register(bus);
        ModItems.ITEMS.register(bus);
        ModGen.register();
    }
}
