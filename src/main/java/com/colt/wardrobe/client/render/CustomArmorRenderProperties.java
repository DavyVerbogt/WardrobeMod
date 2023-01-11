package com.colt.wardrobe.client.render;

import com.colt.wardrobe.client.render.models.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class CustomArmorRenderProperties {

    private static boolean init;

    public static TopHatModel TOPHAT;

    public static void initializeModels() {
        init = true;

        TOPHAT = new TopHatModel(bakeLayer(ModelLayers.TOPHAT));

    }

    public static ModelPart bakeLayer(ModelLayerLocation layerLocation) {
        return Minecraft.getInstance().getEntityModels().bakeLayer(layerLocation);
    }
}
