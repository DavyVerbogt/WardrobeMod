package com.colt.wardrobe;

import com.colt.wardrobe.client.ClientStuff;
import com.colt.wardrobe.client.render.layers.LayersConfig;
import com.colt.wardrobe.config.IConfigHelper;
import com.mojang.logging.LogUtils;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.ServiceLoader;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Wardrobe.MOD_ID)
public class Wardrobe {
    public static final String MOD_ID = "coltwardrobe";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final IConfigHelper CONFIG = new IConfigHelper() {
        @Override
        public void registerConfig(ModConfig.Type type, ForgeConfigSpec spec) {

        }
    };

    public Wardrobe() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(ClientStuff::onClientSetup);
        modEventBus.addListener(ClientStuff::onRegisterKeymappings);
        modEventBus.addListener(ClientStuff::onRegisterLayers);
        modEventBus.addListener(ClientStuff::onAddLayers);

        LayersConfig.registerClient();

        MinecraftForge.EVENT_BUS.register(this);
    }
}