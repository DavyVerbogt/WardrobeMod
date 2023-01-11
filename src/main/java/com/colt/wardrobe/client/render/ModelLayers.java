package com.colt.wardrobe.client.render;

import com.colt.wardrobe.Wardrobe;
import com.colt.wardrobe.client.render.models.*;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
public class ModelLayers {

    public static final ModelLayerLocation

            TOPHAT = createLocation("tophat", "main");


    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        
        event.registerLayerDefinition(TOPHAT, () -> TopHatModel.createArmorLayer(new CubeDeformation(0.5f)));

    }

    private static ModelLayerLocation createLocation(String model, String layer) {
        return new ModelLayerLocation(new ResourceLocation(Wardrobe.MOD_ID, model), layer);
    }
}