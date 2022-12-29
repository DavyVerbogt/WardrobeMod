package com.colt.wardrobe.client;

import com.colt.wardrobe.InputListener;
import com.colt.wardrobe.WardrobeKeyMapping;

import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent SetupEvent) {
        MinecraftForge.EVENT_BUS.register(new InputListener());

        //SetupEvent.enqueueWork(() -> {
        //    MenuScreens.register(RSContainerMenus.FILTER.get(), FilterScreen::new);
        //});
    }

    @SubscribeEvent
    public static void onRegisterKeymappings(RegisterKeyMappingsEvent registerKeyMappingsEvent) {
        registerKeyMappingsEvent.register(WardrobeKeyMapping.KeyWardrobe);
    }
}
