package com.colt.wardrobe.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;

import java.awt.*;

public class MenuHatLayer extends WardrobeLayers {

    public static String ModelName;
    public static int Layers;
    public static boolean IsColorible;
    public static boolean RenderYessPlease = false;

    public static int Layer0Color = Color.BLACK.hashCode();

    public MenuHatLayer(RenderLayerParent renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        LayerColor = Layer0Color;
        if (RenderYessPlease) {
            LayerRenderer(poseStack, buffer, packedLight, entity, partialTick,ModelName,Layers,IsColorible,LayerColor,true);
        }
    }

}