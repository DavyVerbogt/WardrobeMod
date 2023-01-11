package com.colt.wardrobe.client;

import com.colt.wardrobe.InputListener;
import com.colt.wardrobe.WardrobeKeyMapping;
import com.colt.wardrobe.client.render.ModelLayers;
import com.colt.wardrobe.gui.WardrobeGui;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent SetupEvent) {
        MinecraftForge.EVENT_BUS.register(new InputListener());

    }

    @SubscribeEvent
    public static void onRegisterKeymappings(RegisterKeyMappingsEvent registerKeyMappingsEvent) {
        registerKeyMappingsEvent.register(WardrobeKeyMapping.KeyWardrobe);
        registerKeyMappingsEvent.register(WardrobeKeyMapping.Keyflip);
    }
}
