package com.colt.wardrobe.client.render.layers;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class LayersConfig {

    public static ForgeConfigSpec.BooleanValue DISPLAY_BETA;
    public static ForgeConfigSpec.EnumValue<LayersOptions> LAYER_CHOICE;

    public static ForgeConfigSpec.BooleanValue DISPLAY_DEV;


    public static void registerClient() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, new ForgeConfigSpec.Builder() {
            {
                comment("Settings for player layers").push("layers");
                LAYER_CHOICE =
                        comment("The beta layer to be displayed (if unlocked)")
                                .defineEnum("choice", LayersOptions.TOP_HAT);
                pop();
            }
        }.build());
    }
}

