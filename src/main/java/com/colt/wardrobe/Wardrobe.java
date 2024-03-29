package com.colt.wardrobe;

import com.colt.wardrobe.client.ClientStuff;
import com.colt.wardrobe.client.render.layers.LayersConfig;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Wardrobe.MOD_ID)
public class Wardrobe {
    public static final String MOD_ID = "coltwardrobe";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Wardrobe() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(ClientStuff::onClientSetup);
        modEventBus.addListener(ClientStuff::onRegisterKeymappings);
        modEventBus.addListener(ClientStuff::onAddLayers);
        modEventBus.addListener(ClientStuff::onRegisterLayers);

        LayersConfig.registerClient();

        MinecraftForge.EVENT_BUS.register(this);
    }
}