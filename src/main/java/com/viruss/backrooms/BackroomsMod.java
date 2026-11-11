package com.viruss.backrooms;

import com.viruss.backrooms.client.ClientSetup;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BackroomsMod.MODID)
public class BackroomsMod {
    public static final String MODID = "backrooms";

    public BackroomsMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        modBus.addListener(this::commonSetup);
        modBus.addListener(this::addCreative);
        if (FMLEnvironment.dist.isClient()) {
            modBus.addListener(ClientSetup::registerEffects);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ModGen::register);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModItems.CARPET_ITEM.get());
            event.accept(ModItems.WALLPAPER_ITEM.get());
            event.accept(ModItems.CEILING_ITEM.get());
            event.accept(ModItems.LAMP_ITEM.get());
            event.accept(ModItems.EXIT_ITEM.get());
        }
    }
}
