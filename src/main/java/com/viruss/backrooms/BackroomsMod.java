package com.viruss.backrooms;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BackroomsMod.MODID)
public class BackroomsMod {
    // Должно совпадать с modId в META-INF/mods.toml
    public static final String MODID = "backrooms";
    // Псевдоним для ModBlocks
    public static final String MOD_ID = MODID;

    public BackroomsMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.register(bus);
        ModItems.ITEMS.register(bus);
        ModGen.register();
    }
}
