
package com.viruss.backrooms;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BackroomsMod.MODID)
public class BackroomsMod {
    // Оригинальное имя константы — на него ссылаются SandCrushHandler, ModItems и ClientSetup
    public static final String MODID = "virussbackrooms";
    // Псевдоним для ModBlocks (он использует MOD_ID)
    public static final String MOD_ID = MODID;

    public BackroomsMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.register(bus);
        ModItems.ITEMS.register(bus);
        ModGen.register();
    }
}
